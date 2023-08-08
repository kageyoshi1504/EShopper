package com.example.eshopper.admin.add

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.update
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.eshopper.R
import com.example.eshopper.common.Common
import com.example.eshopper.common.LoadingDialog
import com.example.eshopper.common.viewBinding
import com.example.eshopper.databinding.FragmentAddProductBinding
import com.example.eshopper.users.main.model.CategoryModel
import com.example.eshopper.users.main.model.ProductModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import java.util.UUID


class AddProductFragment : Fragment(R.layout.fragment_add_product) {
    private val binding: FragmentAddProductBinding by viewBinding(FragmentAddProductBinding::bind)
    private lateinit var mContext: Context
    private lateinit var image : Uri
    private lateinit var fire: FirebaseFirestore
    private lateinit var dialog: LoadingDialog
    private var categoryName = ""
    private var categoryId =""
    private lateinit var storageRef : StorageReference
    private lateinit var categoryList : ArrayList<CategoryModel>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initAction()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    private fun initView() {
        categoryList = ArrayList()
        fire = FirebaseFirestore.getInstance()
        dialog = LoadingDialog(childFragmentManager)
        storageRef = FirebaseStorage.getInstance().getReference("Product Pic")
        fire.collection("Category").get()
            .addOnSuccessListener { querySnapshot->
                for (document in querySnapshot )
                {
                    val model = document.toObject(CategoryModel::class.java)
                    categoryList.add(model)
                }
            }


    }


    private fun initAction() {

        binding.apply {

            // Quay lại trang trước
            imageBack.setOnClickListener {
                findNavController().popBackStack()
            }

            // Thêm ảnh sản phẩm
            imageProduct.setOnClickListener {
                checkPermission()
            }

            // Thêm sản phẩm
            imageAdd.setOnClickListener {
                if (!validate()) {
                    return@setOnClickListener
                }
                try {

                    val id = UUID.randomUUID().toString()
                    upload(id,categoryId)
                } catch (e: Exception) {
                    dialog.safeDismiss()
                    e.message?.let { it1 -> Common.toast(mContext, it1) }
                    Log.e("Exception", "${e.message}")
                }


            }

            // Reset các giá trị
            imageLoad.setOnClickListener {
                nameProductEt.setText("")
                priceEt.setText("")
                typeProduct.setText("")
                btnCategory.text = "Danh mục"
                amountProduct.setText("")
                image = Uri.parse(" ")
                descProduct.setText("")

            }


            binding.btnCategory.setOnClickListener{
                categoryPickDiaLog()

            }

        }
    }

    // Validate
    private fun validate(): Boolean {
        if (TextUtils.isEmpty(binding.nameProductEt.text.toString())) {
            Common.toast(mContext, "Vui lòng nhập tên sản phẩm")
            return false
        }
        if (TextUtils.isEmpty(image.toString())) {
            Common.toast(mContext, "Vui lòng chọn hình ảnh")
            return false
        }

        if (TextUtils.isEmpty(binding.priceEt.text.toString())) {
            Common.toast(mContext, "Vui lòng nhập giá tiền")
            return false
        }
        if (TextUtils.isEmpty(binding.amountProduct.text.toString())) {
            Common.toast(mContext, "Vui lòng nhập số lượng")
            return false
        }
        if (TextUtils.isEmpty(binding.typeProduct.text.toString())) {
            Common.toast(mContext, "Vui lòng nhập type")
            return false
        }
        if (TextUtils.isEmpty(binding.descProduct.text.toString())) {
            Common.toast(mContext, "Vui lòng nhập mô tả")
            return false
        }
        return true
    }

    // Chọn ảnh
    private fun pickImage() {
        ImagePicker.Companion.with(this).crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .start()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val uri: Uri = data?.data!!
            image = uri
            binding.imageProduct.setImageURI(uri)


        } else if (requestCode == ImagePicker.RESULT_ERROR) {
            Common.toast(mContext, ImagePicker.getError(data))
        } else {
            Common.toast(mContext, "Task Cancelled")
        }

    }

    // Check quyền truy cập vào bộ nhớ trong
    private fun checkPermission() {

        TedPermission.create()
            .setPermissionListener(object : PermissionListener {
                override fun onPermissionGranted() {

                    pickImage()
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Common.toast(mContext, "Dcm cuc")
                }

            })
            .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
            .setPermissions(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            .check()
    }



    private fun categoryPickDiaLog()
    {

            val categoriesList = arrayOfNulls<String>(categoryList.size)
            for (i in categoryList.indices)
            {
                categoriesList[i] = categoryList[i].categoryName
            }
                    val builder = AlertDialog.Builder(mContext)
                    builder.setTitle("Chọn danh mục")
                    .setItems(categoriesList){ _,which ->
                     categoryId = categoryList[which].categoryId
                     categoryName = categoryList[which].categoryName
                        binding.btnCategory.text = categoryName
                }.show()

    }

    private fun upload(uid : String , productId: String)
    {
        dialog.show()

        val fileRef = storageRef.child("${productId}.jpg")
        val uploadTask = fileRef.putFile(image)

        uploadTask.continueWithTask{
            task->
            if(task.isSuccessful)
            {
                task.exception?.let { throw it }
            }
            fileRef.downloadUrl
        }
            .addOnCompleteListener{
                task->
               if (task.isSuccessful)
               {
                   val downloadUri = task.result.toString()
                       val products = ProductModel(
                               uid = uid,
                               productId = Common.getID().toString(),
                               categoryId = categoryId,
                               productName = binding.nameProductEt.text.toString(),
                               productAmount = binding.amountProduct.text.toString().toLong(),
                               productPrice = binding.priceEt.text.toString().toDouble(),
                               productImage = downloadUri,
                               categoryName = categoryName,
                               type = binding.typeProduct.text.toString(),
                               productDesc = binding.descProduct.text.toString()
                       )
                   fire.collection("Product").document(uid).set(products).addOnSuccessListener {
                       dialog.safeDismiss()
                       Common.toast(mContext, "Thành công !!")
                       findNavController().popBackStack()
                   }
                       .addOnFailureListener{
                           dialog.safeDismiss()
                           Common.toast(mContext, "Thất bại !!")
                       }
               }
               else {
                   dialog.safeDismiss()
                   Common.toast(mContext, "Lỗi khi tải lên hình ảnh")
               }


            }
    }

}

