package pak.boom.xsngnai.fragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import pak.boom.xsngnai.data.Data

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val navigator : MainFragment.Listener
    ) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return if(position == 0) MainFragment(navigator)
            else DetailsFragment(Data.contents[position-1])
    }

    override fun getItemCount(): Int {
        return Data.contents.size + 1
    }
}