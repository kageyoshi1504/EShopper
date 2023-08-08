package com.example.eshopper.admin.detail

import android.app.Activity
import android.app.AlertDialog
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
import com.example.eshopper.databinding.FragmentAdminDetailProductBinding
import com.example.eshopper.users.main.model.CategoryModel
import com.example.eshopper.users.main.model.ProductModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission


class AdminDetailProductFragment : Fragment(R.layout.fragment_admin_detail_product) {
   private val binding: FragmentAdminDetailProductBinding by viewBinding(
      FragmentAdminDetailProductBinding::bind
   )
   private lateinit var mContext: Context
   private val args: AdminDetailProductFragmentArgs by navArgs()
   private lateinit var fire: FirebaseFirestore
   private lateinit var dialog: LoadingDialog
   private lateinit var categoryList: ArrayList<CategoryModel>
   private var categoryName = ""
   private var categoryId = ""
   private lateinit var image: Uri
   private var uid = ""
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


   private fun initView() {
      categoryList = ArrayList()
      dialog = LoadingDialog(childFragmentManager)
      fire = FirebaseFirestore.getInstance()
      storageRef = FirebaseStorage.getInstance().getReference("Product Pic")
      fire.collection("Category").get()
         .addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {
               val model = document.toObject(CategoryModel::class.java)
               categoryList.add(model)
            }
         }
   }

   private fun initAction() {
      binding.apply {
         imageBack.setOnClickListener {
            findNavController().popBackStack()
         }

         imageAdd.setOnClickListener {
            if (!validate()) {
               return@setOnClickListener
            }
            try {
                  upload(uid,args.productId)
            } catch (_: Exception) {

            }

         }
         imageProduct.setOnClickListener {
            checkPermission()
         }

         imageLoad.setOnClickListener {
            reset()
         }

         binding.btnCategory.setOnClickListener {
            categoryPickDiaLog()

         }

      }
   }

   private fun reset() {
      binding.apply {
         nameProductEt.setText("")
         amountProduct.setText("")
         btnCategory.text = getString(R.string.category)
         amountProduct.setText("")
         typeProduct.setText("")
         descProduct.setText("")
      }
   }


   private fun categoryPickDiaLog() {

      val categoriesList = arrayOfNulls<String>(categoryList.size)
      for (i in categoryList.indices) {
         categoriesList[i] = categoryList[i].categoryName
      }
      val builder = AlertDialog.Builder(mContext)
      builder.setTitle("Chọn danh mục")
         .setItems(categoriesList) { _, which ->
            categoryId = categoryList[which].categoryId
            categoryName = categoryList[which].categoryName
            binding.btnCategory.text = categoryName
         }.show()

   }

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

   private fun initData() {
      fire.collection("Product").whereEqualTo("productId",args.productId)
         .get()
         .addOnSuccessListener { querySnapshot ->
               for (document in querySnapshot)
               {
                  uid = document.getString("uid").toString()
                  binding.apply {
                     nameProductEt.setText(document.getString("productName"))
                     amountProduct.setText(document.getLong("productAmount").toString())
                     priceEt.setText(document.getDouble("productPrice").toString())
                     typeProduct.setText(document.getString("type"))
                     descProduct.setText(document.getString("productDesc"))
                     btnCategory.text = document.getString("categoryName")
                     Glide.with(mContext).load(document.getString("productImage"))
                        .into(imageProduct)
                     image = Uri.parse(document.getString("productImage").toString())

                  }
            }

         }
   }

   private fun upload(uid: String, productId: String)
   {
      val fileRef = storageRef.child("${productId}.jpg")
      val uploadTask = fileRef.putFile(image)

      dialog.show()
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
               val update = HashMap<String, Any>()
               update["productName"] = binding.nameProductEt.text.toString()
               update["productAmount"] = binding.amountProduct.text.toString().toLong()
               update["productPrice"] = binding.priceEt.text.toString().toDouble()


               if (categoryId != "") {
                  update["categoryName"] = categoryName
                  update["categoryId"] = categoryId
               }
               if (downloadUri != "") {
                  update["productImage"] = downloadUri
               }
               update["productDesc"] = binding.descProduct.text.toString()
               update["type"] = binding.typeProduct.text.toString()

               fire.collection("Product").document(uid).update(update).addOnSuccessListener {
                  dialog.safeDismiss()
                  Common.toast(mContext, "Cập nhật thành công !!")
                  findNavController().popBackStack()
               }
                  .addOnFailureListener{
                     dialog.safeDismiss()
                     Common.toast(mContext, "Cập nhật thất bại !!")
                  }
            }
            else {
               dialog.safeDismiss()
               Common.toast(mContext, "Lỗi khi tải lên hình ảnh")
            }


         }
   }

}