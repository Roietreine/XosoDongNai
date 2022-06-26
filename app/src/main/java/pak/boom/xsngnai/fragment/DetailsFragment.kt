package pak.boom.xsngnai.fragment

import amp.er.kiemtientainha.data.DataModel
import pak.boom.xsngnai.R
import pak.boom.xsngnai.base.BaseFragment
import pak.boom.xsngnai.binding.viewBinding
import pak.boom.xsngnai.databinding.FragmentDetailsBinding

class DetailsFragment(
    private val content: DataModel
): BaseFragment<FragmentDetailsBinding>(R.layout.fragment_details) {

    override val binding: FragmentDetailsBinding by viewBinding(FragmentDetailsBinding::bind)

    override fun setupViews() {
        with(binding){
            title.text = content.title
            description.text = content.content
            banner.setBackgroundResource(content.resource)
        }
    }

    override fun viewModelObservers() {
    }

}