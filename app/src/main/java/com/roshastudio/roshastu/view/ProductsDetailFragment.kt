package com.roshastudio.roshastu.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.adpters.ProductDetailAtributeAdp
import com.roshastudio.roshastu.adpters.ProductDetailCategoryAdp
import com.roshastudio.roshastu.adpters.ProductDetailImageSliderAdapter
import com.roshastudio.roshastu.adpters.ProductsCommentsAdp
import com.roshastudio.roshastu.databinding.FragmentProductsDetailBinding
import com.roshastudio.roshastu.enum.CommentStatus
import com.roshastudio.roshastu.model.*
import com.roshastudio.roshastu.utils.setOnDebouncedClickListener
import kotlinx.android.synthetic.main.fragment_products_detail.*


class ProductsDetailFragment : Fragment(), ProductDetailCategoryAdp.CategoryInteraction,
    ProductsCommentsAdp.CommentsInteraction {
    var binding: FragmentProductsDetailBinding? = null
    private val attributAdp = ProductDetailAtributeAdp()
    private val slideradapter = ProductDetailImageSliderAdapter()
    private val categoryAdapter = ProductDetailCategoryAdp(this)
    private val commentsAdapter = ProductsCommentsAdp(this)
    private var mActionBarBackgroundDrawable: Drawable? = null
    private var arrowDrawable: Drawable? = null

    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding ?: run {
            binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_products_detail,
                container,
                false
            )

        }

        return binding!!.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val id = arguments?.getString("id")
        val specDesc = Specification(1, "یک", "دارد")
        val specDesc2 = Specification(2, "دو", "دارد")
        val catChain = arrayListOf<Category>(
            Category(1, "دسته 1"),
            Category(2, "دسته 2"),
            Category(6, "دسته 6"),
            Category(8, "دسته 8"),
            Category(109, "دسته 109")
        )
        val commentLists = arrayListOf<Comments>(
            Comments(
                1,
                1,
                1,
                "محمد",
                "معرکست",
                getString(R.string.loremipsum_long),
                "1/1/1"
            ,CommentStatus.APPROVE),
            Comments(1, 1, 1, "محمد", "معرکست", getString(R.string.loremipsum_long), "1/1/1" ,CommentStatus.APPROVE),
            Comments(1, 1, 1, "پویا", "عالی", getString(R.string.loremipsum_long), "1/1/1" ,CommentStatus.APPROVE),
            Comments(1, 1, 1, "احمد", "بهترین", getString(R.string.loremipsum_long), "1/1/1" ,CommentStatus.APPROVE)
        )
        val productspec =
            ProductSpecification(1, "مشخصات 1", arrayListOf(specDesc, specDesc, specDesc2))
        val productspec2 = ProductSpecification(
            2,
            "مشخصات 2",
            arrayListOf(specDesc2, specDesc, specDesc, specDesc2)
        )
        val productspec3 = ProductSpecification(
            3,
            "مشخصات 3",
            arrayListOf(
                specDesc,
                specDesc2,
                specDesc,
                specDesc,
                specDesc2,
                specDesc,
                specDesc,
                specDesc2
            )
        )

        val img = arrayListOf<ProductSlideImages>(
            ProductSlideImages(1, null),
            ProductSlideImages(1, null),
            ProductSlideImages(1, null),
            ProductSlideImages(1, null)
        )
        val attr = arrayListOf(
            ProductAtributes(1, "اسم", "دارد"),
            ProductAtributes(2, "اسم", "دارد"),
            ProductAtributes(3, "اسم", "دارد"),
            ProductAtributes(4, "اسم", "دارد"),
            ProductAtributes(1, "اسم", "دارد")
        )
        val product = Product(
            "10",
            "نام کالا",
             R.drawable.two,
            "null",
            img,
            attr,
            "null",
            "null",
            arrayListOf(
                productspec,
                productspec2,
                productspec3,
                productspec,
                productspec2,
                productspec3,
                productspec
            ),
            catChain,
            commentLists
        )
        binding!!.apply {
            produtdetail = product
            productsLayoutIntroduction.apply {
                setOnDebouncedClickListener(1000) { openIntroDialog() }
            }
            productsLayoutSpecifications.apply {
                setOnDebouncedClickListener(1000) {
                    openSepcDialog(product.productSpecifications)
                }
            }
            productSubmitComments.apply {
                setOnDebouncedClickListener(1000){
                    openNewCommentDialog(product.id,"2")
                }
            }
            toolbarProductAppbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                toolbarFading(appBarLayout, verticalOffset)
            })
        }

        setPrice(product)
        setUpSliderImage(product.slideImages)
        setupAttributsRv(product.atributes)
        setCategory(product.productCategory)
        setComments(product.productComments)
    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as MainActivity?)!!.setToolbar(toolbarProduct)
        (activity as MainActivity?)!!.supportActionBar!!.setDisplayShowTitleEnabled(false)

    }

    override fun onDestroyView() {
        (activity as MainActivity?)!!.setToolbar(null)
        super.onDestroyView()
    }

    private fun setupAttributsRv(attrList: List<ProductAtributes>?) {
        binding?.apply {
            attributAdp.submitList(attrList!!)
            productsRvAttributes.layoutManager = LinearLayoutManager(context)
            productsRvAttributes.isNestedScrollingEnabled = true
            productsRvAttributes.adapter = attributAdp
        }
    }

    private fun setUpSliderImage(imgs: List<ProductSlideImages>?) {

        binding?.apply {
            slideradapter.getImgSliders(imgs!!)
            productSliderViewpager.isNestedScrollingEnabled = false

            productSliderViewpager.adapter = slideradapter

        }
    }

    private fun toolbarFading(appBarLayout: AppBarLayout, verticalOffset: Int) {

        mActionBarBackgroundDrawable = getDrawable(requireContext(), R.drawable.toolbar_product)
        mActionBarBackgroundDrawable!!.alpha = 0
        toolbarProduct.background = mActionBarBackgroundDrawable
        arrowDrawable = getDrawable(requireContext(), R.drawable.ic_baseline_arrow_forward_24)
        val colorComponent = Math.max(
            0.3f,
            verticalOffset.toFloat() / -appBarLayout.totalScrollRange
        )
        val headerHeight: Int =
            toolbarProductAppbar.height - toolbarProduct.height
        val ratio =
            kotlin.math.abs(verticalOffset).coerceAtLeast(0).coerceAtMost(headerHeight)
                .toFloat() / headerHeight
        val newAlpha = (ratio * 255).toInt()
        mActionBarBackgroundDrawable!!.alpha = newAlpha
        txt_view_title_prdt.setTextColor(Color.argb(newAlpha, 255, 250, 255));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            toolbarProduct.navigationIcon?.colorFilter =
                PorterDuffColorFilter(
                    Color.rgb(colorComponent, colorComponent, colorComponent),
                    PorterDuff.Mode.SRC_ATOP
                )
        } else {
            toolbarProduct.navigationIcon?.colorFilter =
                PorterDuffColorFilter(
                    Color.argb(
                        255,
                        colorComponent.toInt(),
                        colorComponent.toInt(),
                        colorComponent.toInt()
                    ),
                    PorterDuff.Mode.SRC_ATOP
                )
        }

    }
private fun openNewCommentDialog(productId:String?,userId:String?){
    val bundle4 = Bundle()
    if (productId != null && userId != null) {
        bundle4.putString("pid", productId.toString())
        bundle4.putString("uid", userId.toString())
    }
    Navigation.findNavController(parent_product_ditail)
        .navigate(R.id.action_productsDetailFragment_to_dialogNewComment, bundle4)
}
    private fun openSepcDialog(product: ArrayList<ProductSpecification>?) {
        val bundle2 = Bundle()
        if (product != null) {
            bundle2.putParcelableArrayList("list", product);
        }
        Navigation.findNavController(parent_product_ditail)
            .navigate(R.id.action_productsDetailFragment_to_specificationsDialogFragment, bundle2)
    }

    private fun openIntroDialog() {
        val bundle = Bundle()
        bundle.putString("id", "dd")
        Navigation.findNavController(requireView())
            .navigate(R.id.action_productsDetailFragment_to_introDialogFragment)
    }

    private fun setPrice(product: Product) {
        binding.apply {
            if (product.beforeDisCount != null) {
                product_befor_disscount_price.visibility = View.VISIBLE
                product_befor_disscount_price.text = "200,000 تومان"
                product_befor_disscount_price.paintFlags =
                    product_befor_disscount_price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                product_befor_disscount_price.visibility = View.GONE

            }

            product_price.text = "180,000 تومان"
        }
    }

    private fun setCategory(productCategory: List<Category>?) {
        binding!!.apply {
            if (productCategory != null) {
                categoryAdapter.submitList(productCategory)

            }
            productsRvCategory.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            productsRvCategory.adapter = categoryAdapter
        }
    }

    private fun setComments(Comments: List<Comments>?) {
        binding!!.apply {
            if (Comments != null) {
                commentsAdapter.submitList(Comments)
            }
            productRvComment.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            productRvComment.adapter = commentsAdapter
        }
    }

    override fun onCategorySelected(position: Int, item: Category) {
        Toast.makeText(context, item.name.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun showMore(position: Int, item: Int) {
        val bundle3 =Bundle()
        bundle3.putString("ProductId",item.toString())
        Navigation.findNavController(parent_product_ditail)
            .navigate(R.id.action_productsDetailFragment_to_dialogAllComments, bundle3)
    }
}