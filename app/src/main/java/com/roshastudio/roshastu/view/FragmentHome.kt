package com.roshastudio.roshastu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.core.view.GravityCompat
import androidx.core.view.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.roshastudio.roshastu.R
import com.roshastudio.roshastu.adpters.HomeHrsRecyclerViewAdp
import com.roshastudio.roshastu.adpters.HomevertRecyclerViewAdp
import com.roshastudio.roshastu.databinding.HomeFragmentBinding
import com.roshastudio.roshastu.enum.CommentStatus
import com.roshastudio.roshastu.interfaces.DelegateAdapterItem
import com.roshastudio.roshastu.interfaces.EventObserver
import com.roshastudio.roshastu.model.*
import com.roshastudio.roshastu.viewModels.FragmentHomeVm
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

class FragmentHome() : Fragment(), HomevertRecyclerViewAdp.Interaction,
    HomeHrsRecyclerViewAdp.ClickHrsAdapter {
    private lateinit var vm: FragmentHomeVm

    val specDesc = Specification(1, "یک", "دارد")
    val specDesc2 = Specification(2, "دو", "دارد")
    val catChain = arrayListOf<Category>(
        Category(1, "دسته 1"),
        Category(2, "دسته 2"),
        Category(6, "دسته 6"),
        Category(8, "دسته 8"),
        Category(109, "دسته 109")
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

    val imgp = arrayListOf<ProductSlideImages>(
        ProductSlideImages(1, null),
        ProductSlideImages(1, null),
        ProductSlideImages(1, null),
        ProductSlideImages(1, null)
    )
    val arraySpec =arrayListOf(
        productspec,
        productspec2,
        productspec3,
        productspec,
        productspec2,
        productspec3,
        productspec
    )
    val prDesc ="R.string.loremipsum_long"
    val attr = arrayListOf(
        ProductAtributes(1, "اسم5", "دارد"),
        ProductAtributes(2, "اسم4", "دارد"),
        ProductAtributes(3, "اسم3", "دارد"),
        ProductAtributes(4, "اسم2", "دارد"),
        ProductAtributes(1, "اسم1", "دارد")
    )
    val pr = Products_list(
        2, "اولی", R.drawable.one, true, "#FF6200EE", arrayListOf(
            Product("1", "محصول 1", R.drawable.two, "23", imgp, attr, null, prDesc.toString(), arraySpec, null, null),
            Product("2", "محصول 2", R.drawable.two, "29", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("3", "محصول 3", R.drawable.two, "29", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("4", "محصول 4", R.drawable.two, "29", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("5", "محصول 5", R.drawable.two, "29", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("6", "محصول 6", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("7", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("8", "محصول 8", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("9", "محصول 9", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("10", "محصول 10", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(), arraySpec, null, null)
        )
    )


    val pr1 = Products_list(
        3, "دومین", R.drawable.one,true,"#E53935", arrayListOf(
            Product("11", "محصول 1",R.drawable.one, "23",null,null,null,null,null,null,null),
            Product("12", "محصول 2", R.drawable.one, "29",null,null,null,null,null,null,null),
            Product("23", "محصول 3", R.drawable.one, "23",null,null,null,null,null,null,null),
            Product("24", "محصول 4", R.drawable.one, "23",null,null,null,null,null,null,null),
            Product("25", "محصول 5", R.drawable.one, "23",null,null,null,null,null,null,null),
            Product("26", "محصول 6", R.drawable.one, "23",null,null,null,null,null,null,null),
        )
    )
    val pr4 = Products_list(
        55, "دوم", R.drawable.one, true, "#E53935", arrayListOf(
            Product("11", "محصول 0", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(), arraySpec, null, null),
            Product("12", "محصول 1", R.drawable.two, "29", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("23", "محصول 2", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 3", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 4", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 5", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 6", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),   Product("23", "2اولین", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("24", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("25", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
            Product("26", "محصول 7", R.drawable.two, "23", imgp, attr, null,  prDesc.toString(),arraySpec, null, null),
        )
    )
    val bl = Billbord(1, null, "afd")
    val img = SliderImage(1, null, "sdf")
    val slideshoe: Slideshow =
        Slideshow(1, arrayListOf(img, img, img, img, img, img, img, img, img))
    var delegat: List<HomeView> = arrayListOf(
        HomeView(1, slideshoe),
        HomeView(0, pr1),
        HomeView(0,pr),
        HomeView(0,pr4),
        HomeView(2, bl),
        HomeView(1, slideshoe),
        HomeView(2, bl),
        HomeView(0, pr4)
    )
    private lateinit var navController: NavController
    val bundle = Bundle()

    var binding: HomeFragmentBinding? = null
    lateinit var adp: HomevertRecyclerViewAdp
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            delay(300L)
            initRecyclerView()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm = ViewModelProvider(this).get(FragmentHomeVm::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)
        navController = Navigation.findNavController(requireActivity(), R.id.fragment)
        vm.navigateScreen.observe(requireActivity(), EventObserver {
            navController.navigate(it.hashCode(), bundle)
        })

        return binding!!.root
    }

    private suspend fun initRecyclerView() {
        binding!!.apply {

            adp = HomevertRecyclerViewAdp(this@FragmentHome, requireContext(), this@FragmentHome)
            homeVerticalRv.layoutManager = LinearLayoutManager(context)
            homeVerticalRv.isNestedScrollingEnabled = true
            homeVerticalRv.setItemViewCacheSize(15)
            homeVerticalRv.setHasFixedSize(true)
            withContext(Dispatchers.Default) {
                adp.list(delegat)
            }

            homeVerticalRv.adapter = adp

        }
    }


    override fun onItemSelected(position: Int, item: DelegateAdapterItem) {
        if (item is Products_list) {
            val x = item as Products_list
            Toast.makeText(requireContext(), x.titel, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onItemClick(item: Product) {
        bundle.putString("id", item.id)
        vm.clicitem()


    }

    override fun onActivityCreated(@Nullable savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity?)!!.setToolbar(toolbarMain)


    }

    override fun onDestroyView() {
        (activity as MainActivity?)!!.setToolbar(null)
        super.onDestroyView()
    }

}