package pak.boom.xsngnai.fragment

import android.os.Handler
import android.os.Looper
import androidx.viewpager2.widget.ViewPager2
import pak.boom.xsngnai.R
import pak.boom.xsngnai.base.BaseFragment
import pak.boom.xsngnai.binding.viewBinding
import pak.boom.xsngnai.data.Data
import pak.boom.xsngnai.databinding.FragmentMainBinding

class MainFragment(
    private val listener: Listener
): BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override val binding: FragmentMainBinding by viewBinding (FragmentMainBinding::bind)

    private val bannerAdapter by lazy {
        BannerAdapter()
    }

    val slideTimer = 3000L

    val sliderHandler = Handler(Looper.getMainLooper())

    val sliderRunnable by lazy {
        Runnable {
            try{
                val nextBanner = binding.bannerPager.currentItem + 1
                binding.bannerPager.setCurrentItem(
                    if (bannerAdapter.banners.size - 1 < nextBanner) 0 else nextBanner,
                    true
                )
            }catch (e: Exception) {}
        }
    }

    override fun setupViews() {
        with(binding){

            textView.text = Data.contents[0].title
            textView1.text = Data.contents[1].title
            textView2.text = Data.contents[2].title
            textView3.text = Data.contents[3].title

            textButton1.text = Data.contents[0].content
            textButton2.text = Data.contents[1].content
            textButton3.text = Data.contents[2].content
            textButton4.text = Data.contents[3].content

            cardButton1.setOnClickListener {
                listener.setFragmentPager(1)
            }
            cardButton2.setOnClickListener {
                listener.setFragmentPager(2)
            }
            cardButton3.setOnClickListener {
                listener.setFragmentPager(3)
            }
            cardButton4.setOnClickListener {
                listener.setFragmentPager(4)
            }

            bannerPager.adapter = bannerAdapter
            bannerPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    sliderHandler.removeCallbacks(sliderRunnable)
                    sliderHandler.postDelayed(sliderRunnable, slideTimer)
                }
            })
        }
    }

    override fun viewModelObservers() {

    }

    interface Listener {
        fun setFragmentPager(position: Int)
    }
}