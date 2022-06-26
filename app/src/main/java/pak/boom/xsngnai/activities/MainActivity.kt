package pak.boom.xsngnai.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import pak.boom.xsngnai.R
import pak.boom.xsngnai.binding.viewBinding
import pak.boom.xsngnai.databinding.ActivityMainBinding
import pak.boom.xsngnai.fragment.MainFragment
import pak.boom.xsngnai.fragment.ViewPagerAdapter

class MainActivity : AppCompatActivity(),
MainFragment.Listener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val adapter by lazy {
        ViewPagerAdapter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding){
            fragmentViewPager.adapter = adapter
            fragmentViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setFragmentPager(position)
                }
            })

            bottomNavigationView.setOnItemSelectedListener {menu ->
                when(menu.itemId){
                    R.id.main -> setCurrentItem(0)
                    R.id.about -> setCurrentItem(1)
                    R.id.money -> setCurrentItem(2)
                    R.id.strat -> setCurrentItem(3)
                    R.id.tips -> setCurrentItem(4)
                    else -> {}
                }
                true
            }
        }
    }

    override fun setFragmentPager(position: Int) {
        when(position){
            0 -> setSelectedNav(R.id.main)
            1 -> setSelectedNav(R.id.about)
            2 -> setSelectedNav(R.id.money)
            3 -> setSelectedNav(R.id.strat)
            4 -> setSelectedNav(R.id.tips)
        }
    }

    fun setSelectedNav(id: Int){
        binding.bottomNavigationView.selectedItemId = id
    }

    private fun setCurrentItem(position: Int){
        binding.fragmentViewPager.setCurrentItem(position, true)
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit Application?")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Ok"){ _,_ -> super.onBackPressed() }
            .setNegativeButton("Cancel"){ d, _ -> d.dismiss()}
            .show()
    }
}