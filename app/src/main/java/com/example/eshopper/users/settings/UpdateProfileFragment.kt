package com.example.eshopper.users.settings

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable

import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentUpdateProfileBinding
import com.example.eshopper.users.main.model.UserModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class UpdateProfileFragment : Fragment(R.layout.fragment_update_profile) {
    private val binding: FragmentUpdateProfileBinding by viewBinding(FragmentUpdateProfileBinding::bind)
    private lateinit var dbRef: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var storageRef : StorageReference
    private lateinit var dialog: LoadingDialog
    private lateinit var mContext: Context
    private  lateinit var image : Uri



    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()

    }

    private fun initView() {
        dialog = LoadingDialog(childFragmentManager)
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        mAuth = FirebaseAuth.getInstance()
        storageRef = FirebaseStorage.getInstance().getReference("Profile Pic")

    }

    private fun initAction() {
        binding.apply {
            imageViewBack.setOnClickListener {
                findNavController().popBackStack()
            }
            relativeChoose.setOnClickListener {
                checkPermission()

            }
            buttonSave.setOnClickListener {
                upload()
            }
        }
    }

    private fun pickImage() {
        ImagePicker.Companion.with(this).crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .start()
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            val uri: Uri? = data.data
            uri?.let {
                image = it
                binding.imageAvatar.setImageURI(image)
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Common.toast(mContext, ImagePicker.getError(data))
        } else {
            Common.toast(mContext, "Task Cancelled")
        }
        dialog.safeDismiss()
    }



    private fun checkPermission() {

        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {

                    pickImage()
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                }

            })
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .check()
    }

    private fun initData() {
        if (isAdded) {
            dbRef.child(mAuth.currentUser!!.uid).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        image =Uri.parse("${snapshot.child("avatar").value}")
                        binding.nameEt.setText("${snapshot.child("name").value}")
                        binding.phoneEt.setText("${snapshot.child("phone").value}")
                        Glide.with(mContext).load(image).into(binding.imageAvatar)
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    Common.toast(mContext, error.message)
                }

            })
        }
    }


    private fun upload() {
        if (image.toString() != "") {
            dialog.show()
            val fileRef = storageRef.child("${mAuth.currentUser?.uid}.jpg") // Kiểm tra xem currentUser có null hay không
            val uploadTask = fileRef.putFile(image)

            uploadTask.continueWithTask { task ->
                if (task.isSuccessful) {
                    task.exception?.let { throw it }
                }
                fileRef.downloadUrl
            }.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result.toString()
                    val update = HashMap<String, Any>()
                    update["name"] = binding.nameEt.text.toString()
                    update["phone"] = binding.phoneEt.text.toString()
                    update["avatar"] = downloadUri

                    // Cập nhật profile vào FirebaseAuthentication
                    val profileUpdates = userProfileChangeRequest {
                        displayName = binding.nameEt.text.toString()
                        photoUri = Uri.parse(downloadUri)
                    }

                    mAuth.currentUser!!.updateProfile(profileUpdates)
                        .addOnCompleteListener{
                            tasks ->
                            if(tasks.isSuccessful)
                            {

                                // Cập nhật profile vào Realtime Database
                                dbRef.child(mAuth.currentUser!!.uid).updateChildren(update)
                                    .addOnSuccessListener {
                                        dialog.safeDismiss()
                                        Common.toast(mContext, "Cập nhật thành công!")
                                    }
                                    .addOnFailureListener {
                                        dialog.safeDismiss()
                                        Common.toast(mContext, "Cập nhật thất bại!")
                                    }
                            }
                            else {
                                 dialog.safeDismiss()
                                Common.toast(mContext,"Cập nhật thất bại")
                            }
                        }

                } else {
                    dialog.safeDismiss()
                    Common.toast(mContext, "Lỗi tải lên ảnh!")
                }
            }
        } else {
            Common.toast(mContext, "Vui lòng chọn ảnh trước khi lưu!")
        }
    }




}
