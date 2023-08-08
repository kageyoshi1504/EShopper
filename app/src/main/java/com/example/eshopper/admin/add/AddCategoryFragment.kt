package com.example.eshopper.admin.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController

import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentAddCategoryBinding
import com.example.eshopper.users.main.model.CategoryModel
import com.example.eshopper.users.main.model.Event.LoadData
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import org.greenrobot.eventbus.EventBus
import java.util.UUID


class AddCategoryFragment : Fragment(R.layout.fragment_add_category) {

    private val binding: FragmentAddCategoryBinding by viewBinding(FragmentAddCategoryBinding::bind)
    private lateinit var dialog : LoadingDialog
    private lateinit var image : Uri
    private lateinit var fire : FirebaseFirestore
    private lateinit var mContext : Context
    private lateinit var storageRef : StorageReference


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAction()



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun initView(){
        dialog = LoadingDialog(childFragmentManager)
        fire = FirebaseFirestore.getInstance()
        storageRef = FirebaseStorage.getInstance().getReference("Category Pic")

    }
    private fun initAction()
    {
        binding.imageBack.setOnClickListener{
            findNavController().popBackStack()
        }


            binding.apply {
                imageBack.setOnClickListener{
                    findNavController().popBackStack()
                }

                imagaCategory.setOnClickListener{

                    checkPermission()
                }
                btnAddCategory.setOnClickListener{
                    if(!validate())
                    {
                        return@setOnClickListener
                    }
                   // dialog.show()
                    try {
                        val id =  UUID.randomUUID().toString()
                        upload(id,Common.getID().toString())


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
            Common.toast(mContext,"Vui lòng nhập tên danh mục")
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
            Common.toast(mContext,ImagePicker.getError(data))
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

    private fun upload(uid : String , categoryId : String ) {
       dialog.show()
        val fileRef = storageRef.child("$categoryId.jpg")
        val uploadTask = fileRef.putFile(image)

        uploadTask.addOnSuccessListener {
            fileRef.downloadUrl.addOnSuccessListener {
                val downloadUri = it.toString()
                val category = CategoryModel(
                    uid = uid,
                    categoryId = categoryId,
                    categoryName = binding.categoryName.text.toString(),
                    categoryImage = downloadUri
                )
                fire.collection("Category").document(uid).set(category)
                    .addOnSuccessListener {
                        dialog.safeDismiss()
                        Common.toast(mContext,"Thành Công !!")
                        findNavController().popBackStack()
                        EventBus.getDefault().postSticky(LoadData())
                    }
                    .addOnFailureListener{
                        dialog.safeDismiss()
                        Common.toast(mContext,"Thất bại !!")
                    }
            }
        }
    }





}