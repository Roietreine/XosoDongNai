package pak.boom.xsngnai.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pak.boom.xsngnai.R
import pak.boom.xsngnai.data.Data
import pak.boom.xsngnai.databinding.ItemBannerBinding

class BannerAdapter: RecyclerView.Adapter<BannerAdapter.Holder>() {

    val banners = Data.bannerCarousel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_banner, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){
            bannerImage.setBackgroundResource(banners[position])
        }
    }

    override fun getItemCount(): Int = banners.size

    class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding by lazy {
            ItemBannerBinding.bind(itemView)
        }
    }
}