package com.example.mtmimyeon_gitmi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mtmimyeon_gitmi.databinding.FragmentHomeBinding
import com.example.mtmimyeon_gitmi.databinding.ItemMjuSiteBinding

class HomeFragment : Fragment(), MjuSiteClickedInterface {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 나중에 firebase에서 데이터를 가지고 올 것이므로 임시로 사용
        val mjuSiteImageList = requireContext().resources.obtainTypedArray(R.array.mjuSiteImageList)
        val mjuSiteTextList = requireContext().resources.getStringArray(R.array.mjuSiteTextList)
        val itemMjuSiteList = ArrayList<ItemMjuSite>()

        // 데이터 삽입
        for (i in mjuSiteTextList.indices) {
            itemMjuSiteList.add(
                ItemMjuSite(
                    mjuImage = mjuSiteImageList.getResourceId(i, -1),
                    mjuText = mjuSiteTextList[i]
                )
            )
        }

        val mjuSiteRecyclerAdapter = MjuSiteRecyclerAdapter(itemMjuSiteList, this)
        binding.recyclerviewMainUnvInfo.apply {
            adapter = mjuSiteRecyclerAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null // 메모리 릭 방지
    }

    override fun onItemClicked(item: String) {
        var url = ""

        when (item) {
            "phone" -> {
                Intent(requireContext(), CampusPhoneNumberActivity::class.java).also {
                    requireActivity().startActivity(it)
                }
                requireActivity().overridePendingTransition(
                    R.anim.activity_slide_in,
                    R.anim.activity_slide_out
                )
                return
            }
            "학교홈" -> url = "https://www.mju.ac.kr/mjukr/index.do"
            "학과홈" -> url = "https://cs.mju.ac.kr"
            "library" -> url = "https://lib.mju.ac.kr/index.ax"
            "eclass" -> url = "https://eclass.mju.ac.kr/user/index.action"
            "lms" -> url = "https://lms.mju.ac.kr/ilos/main/main_form.acl"
            "myiweb" -> url = "https://myiweb.mju.ac.kr/servlet/security/MySecurityStart"
            "myicap" -> url = "https://myicap.mju.ac.kr/"
            "ucheck" -> url =
                "https://ucheck.mju.ac.kr/;jsessionid=F35A5F3F48644210CEB08183C2E8D492#"
            "기숙사" -> url = "https://jw4.mju.ac.kr/user/dorm/index.action"
            "문진표" -> url = "http://www.mjuqr.kr/view/4b46ea5151336b2f7668b67551b1a511"
            "수강신청" -> url = "http://http://class.mju.ac.kr/"
            else -> {
            }
        }
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).also {
            startActivity(it)
            requireActivity().overridePendingTransition(
                R.anim.activity_slide_in,
                R.anim.activity_slide_out
            )
        }
    }
}


data class ItemMjuSite(val mjuImage: Int, val mjuText: String)

// 원래는 파일을 나누는 게 원칙이나 가시성을 위해서 일시적으로 한 파일에서 관리
// 리사이클러뷰 어댑터
class MjuSiteRecyclerAdapter(
    private val itemMjuSiteList: ArrayList<ItemMjuSite>,
    private val mjuSiteClickedInterface: MjuSiteClickedInterface
) : RecyclerView.Adapter<MjuSiteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MjuSiteViewHolder {
        val binding = ItemMjuSiteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MjuSiteViewHolder(binding, mjuSiteClickedInterface)
    }

    override fun onBindViewHolder(holder: MjuSiteViewHolder, position: Int) {
        holder.bind(itemMjuSiteList[position])
    }

    override fun getItemCount(): Int {
        return itemMjuSiteList.size
    }

}

// 리사이클러뷰 뷰홀더
class MjuSiteViewHolder(
    private val item: ItemMjuSiteBinding,
    private val mjuSiteClickedInterface: MjuSiteClickedInterface
) : RecyclerView.ViewHolder(item.root) {

    init {
        item.root.setOnClickListener {
            mjuSiteClickedInterface.onItemClicked(item.textViewItemMjuText.text.toString())
            Log.d("로그", item.textViewItemMjuText.text.toString())
        }
    }

    fun bind(itemMjuSite: ItemMjuSite) {
        item.imageViewItemMjuSiteImg.setImageResource(itemMjuSite.mjuImage)
        item.textViewItemMjuText.text = itemMjuSite.mjuText
    }
}

// 리사이클러뷰 내 아이템 클릭 이벤트
interface MjuSiteClickedInterface {
    fun onItemClicked(item: String)
}

