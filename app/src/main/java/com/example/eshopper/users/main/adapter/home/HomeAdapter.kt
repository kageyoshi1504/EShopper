package com.example.eshopper.users.main.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eshopper.R
import com.example.eshopper.databinding.HomeCategoryListBinding
import com.example.eshopper.databinding.HomeHotDealProductBinding
import com.example.eshopper.databinding.HomePopularProductBinding
import com.example.eshopper.databinding.HomeProductListBinding
import com.example.eshopper.users.main.model.ProductModel
import com.example.eshopper.users.main.utils.SampleData


class HomeAdapter(
    private val data : List<Int>,
    private val onItemClick : (String)->Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    inner class MainProductViewHolder(private val products : HomeProductListBinding ) : RecyclerView.ViewHolder(products.root)
    {

        fun bind()
        {
            products.tvProduct.text = "Danh sách sản phẩm"
            val productAdapter = ProductListAdapter(SampleData.product,onItemClick)
            products.rvProduct.adapter = productAdapter
        }
    }

    inner class MainCategoriesViewHolder(private val categories : HomeCategoryListBinding ) : RecyclerView.ViewHolder(categories.root)
    {
        fun bind(){

            categories.tvCategory.text = "Danh mục"
            val categoryAdapter = CategoryListAdapter(SampleData.category,onItemClick)
            categories.rvCategory.adapter = categoryAdapter

        }
    }

    inner class MainHotProductViewHolder(private val hotProduct : HomeHotDealProductBinding) : RecyclerView.ViewHolder(hotProduct.root)
    {
        fun bind()
        {
           hotProduct.tvHotDeal.text ="Deal sốc"
           val hotProductAdapter = HotProductAdapter(SampleData.hotProduct,onItemClick)
            hotProduct.rvHotDeal.adapter = hotProductAdapter
        }
    }

    inner class MainPopularProductViewHolder(private val popularProduct : HomePopularProductBinding) : RecyclerView.ViewHolder(popularProduct.root)
    {
        fun bin()
        {
            popularProduct.tvPopularProduct.text = "Bán chạy"
            val popularProductAdapter = PopularProductAdapter(SampleData.popularProduct,onItemClick)
            popularProduct.rvPopularProduct.adapter = popularProductAdapter
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            when (viewType)
           {
               VIEW_CATEGORY -> {
                   val view = HomeCategoryListBinding.inflate(LayoutInflater.from(parent.context),parent
                   ,false)
                   return MainCategoriesViewHolder(view)
               }

               VIEW_PRODUCT -> {
                   val view = HomeProductListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                   return MainProductViewHolder(view)
               }
               VIEW_HOT_PRODUCT -> {
                   val view = HomeHotDealProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                    return MainHotProductViewHolder(view)
               }
               VIEW_POPULAR_PRODUCT ->{
                   val view = HomePopularProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                   return MainPopularProductViewHolder(view)
               }
               else ->{
                   throw IllegalArgumentException("invalid item type")
               }

           }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return when(data[position])
        {
            VIEW_CATEGORY -> (holder as MainCategoriesViewHolder).bind()
            VIEW_PRODUCT -> (holder as MainProductViewHolder).bind()
            VIEW_HOT_PRODUCT ->(holder as MainHotProductViewHolder).bind()
            VIEW_POPULAR_PRODUCT ->(holder as MainPopularProductViewHolder).bin()

            else -> throw IllegalArgumentException("invalid item type")
        }
    }


    override fun getItemViewType(position: Int): Int {
        return data[position]
    }
    companion object{
        const val VIEW_CATEGORY = 1
        const val VIEW_PRODUCT = 2
        const val VIEW_HOT_PRODUCT = 3
        const val VIEW_POPULAR_PRODUCT = 4
    }




}