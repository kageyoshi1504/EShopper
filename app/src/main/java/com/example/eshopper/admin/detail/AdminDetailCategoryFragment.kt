package com.example.eshopper.admin.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentAdminDetailCategoryBinding
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import io.grpc.Context.Storage
import java.io.File
import java.util.UUID


class AdminDetailCategoryFragment : Fragment(R.layout.fragment_admin_detail_category) {

    private val binding :FragmentAdminDetailCategoryBinding by viewBinding(FragmentAdminDetailCategoryBinding::bind)
    private lateinit var fire : FirebaseFirestore
    private lateinit var image : Uri
    private  val args : AdminDetailCategoryFragmentArgs by navArgs()
    private lateinit var mContext :Context
    private lateinit var dialog : LoadingDialog
    private  var uid = ""
    private lateinit var storageRef : StorageReference

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()
        initAction()

    }

    private fun initView()
    {
        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().getReference("Category Pic")
    }

    private fun initAction()
    {
        binding.apply {
            imageBack.setOnClickListener{
                findNavController().popBackStack()
            }
            imagaCategory.setOnClickListener{
                checkPermission()
            }
            btnAddCategory.setOnClickListener {
                if (!validate())
                {
                    return@setOnClickListener
                }


                try {

                    upload(uid,args.categoryId)

                }
                catch(e:Exception)
                {
                    dialog.safeDismiss()
                    Log.e("Image","${e.message}")
                }

            }
        }
    }
    private fun validate() : Boolean
    {


        if (TextUtils.isEmpty(binding.categoryName.text.toString())){
            Common.toast(requireContext(),"Vui lòng nhập tên danh mục")
            return false
        }
        if (TextUtils.isEmpty(image.toString()))
        {
            Common.toast(mContext,"Vui lòng chọn ảnh")
            return false
        }
        return true
    }

    private fun pickImage()
    {
        ImagePicker.Companion.with(this).crop()
            .compress(1024)
            .maxResultSize(1080,1080)
            .start()
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK)
        {
            val uri : Uri = data?.data!!
            image = uri
            binding.imagaCategory.setImageURI(uri)

        }

        else if (requestCode == ImagePicker.RESULT_ERROR)
        {
            Common.toast(mContext, ImagePicker.getError(data))
        }
        else{
            Common.toast(mContext,"Task Cancelled")
        }

    }


    private fun checkPermission()
    {

        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {

                    pickImage()
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

                }

            })
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }

    private fun initData()
    {

        fire.collection("Category").whereEqualTo("categoryId",args.categoryId)
            .get()
            .addOnSuccessListener { querySnapshot->
                for (document in querySnapshot)
                {
                    uid = document.getString("uid").toString()
                    binding.categoryName.setText(document.getString("categoryName"))
                    Glide.with(mContext).load(document.getString("categoryImage")).into(binding.imagaCategory)
                    image = Uri.parse(document.getString("categoryImage"))
                }

            }
    }

    private fun upload(uid: String, categoryId: String) {

        val fileRef = storageRef.child("${categoryId}.jpg")
        val uploadTask = fileRef.putFile(image)

        dialog.show()

        // Tiếp tục vào các tác vụ sau khi tải lên thành công
        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let { throw it }
            }
            // Tiếp tục tạo URI download
            fileRef.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result.toString()
                  val updateCategory = HashMap<String,Any>()
                     updateCategory["categoryName"] = binding.categoryName.text.toString()
                 val update = HashMap<String,Any>()
                   update["categoryName"] = binding.categoryName.text.toString()
                if (downloadUri != "")
                {
                    update["categoryImage"] = downloadUri
                }
                fire.collection("Category").document(uid)
                    .update(update)
                    .addOnSuccessListener {
                        fire.collection("Product").whereEqualTo("categoryId", args.categoryId).get()
                            .addOnSuccessListener { querySnapshot ->
                                val uidProduct = ArrayList<String>()
                                for (document in querySnapshot) {
                                    val id = document.getString("uid").toString()
                                    uidProduct.add(id)
                                }
                                for (id in uidProduct) {
                                    fire.collection("Product").document(id).update(updateCategory)
                                        .addOnFailureListener { /* Xử lý lỗi nếu cần */ }
                                }
                            }
                        dialog.safeDismiss()
                        Common.toast(mContext, "Thành công !!")
                        findNavController().popBackStack()
                    }
                    .addOnFailureListener {
                        dialog.safeDismiss()
                        Common.toast(mContext, "Thất bại !!")
                    }
            } else {
                // Xử lý lỗi nếu không tải lên được hình ảnh
                dialog.safeDismiss()
                Common.toast(mContext, "Lỗi khi tải lên hình ảnh")
            }
        }
    }


}