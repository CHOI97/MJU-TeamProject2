package com.example.mtmimyeon_gitmi.chatting

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mtmimyeon_gitmi.R
import com.example.mtmimyeon_gitmi.databinding.ActivityChattingRoomListBinding
import com.example.mtmimyeon_gitmi.databinding.ItemChattingRoomBinding
import com.example.mtmimyeon_gitmi.db.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import kotlin.collections.ArrayList

class ChattingRoomListActivity : AppCompatActivity(), ChattingRoomClickInterface {
    private lateinit var binding: ActivityChattingRoomListBinding
    private lateinit var chattingRoomListRecyclerAdapter: ChattingRoomListRecyclerAdapter
    private var myChattingRoomList = ArrayList<ChatListForm>()
    var database: DatabaseManager = DatabaseManager()
    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChattingRoomListBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun init() {
        this.myChattingRoomList.clear()
        database.loadChatList(auth.currentUser.uid, object : Callback<ArrayList<Chat>> {
            override fun onCallback(data: ArrayList<Chat>) {
                for (i in 0 until data.count()) {
                    database.loadLastChat(data[i].chatRoomId, object : Callback<ChatListForm> {
                        override fun onCallback(data: ChatListForm) {
                            myChattingRoomList.add(data)
                            Log.d("data", data.timeStamp)
                            binding.recyclerviewActivityChattingRoomListChatList.apply {
                                adapter = chattingRoomListRecyclerAdapter
                                layoutManager =
                                    LinearLayoutManager(
                                        this@ChattingRoomListActivity,
                                        LinearLayoutManager.VERTICAL,
                                        false
                                    )
                            }
                        }
                    })
                }
//                binding.recyclerviewActivityChattingRoomListChatList.apply {
//                    adapter = chattingRoomListRecyclerAdapter
//                    layoutManager =
//                        LinearLayoutManager(
//                            this@ChattingRoomListActivity,
//                            LinearLayoutManager.VERTICAL,
//                            false
//                        )
//                }
//                Log.d("??????",myChattingRoomList[0].timeStamp)


            }
        })

        chattingRoomListRecyclerAdapter =
            ChattingRoomListRecyclerAdapter(myChattingRoomList, this)
    }

    override fun onResume() {
        init()
        super.onResume()
    }

    // ?????? ???????????? ???????????? ???
    override fun chattingRoomClicked(
        chattingRoomIdx: String,
        imgUrl: String,
    ) { // ?????? ??? ?????? <- ????????? ????????? ??????(?????? ??? ?????? ????????? ????????? ???)
        Log.d("??????", "??????@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        Log.d("chattingRoomIdx ??????", chattingRoomIdx)
        Intent(this, ChattingRoomDetailsActivity::class.java).also {
            it.putExtra("chatId", chattingRoomIdx).putExtra("partnerImg", imgUrl)
            startActivity(it)
        }
        overridePendingTransition(R.anim.activity_slide_in, R.anim.activity_slide_out)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_slide_back_in, R.anim.activity_slide_back_out)
    }
}

//data class ItemChattingRoom(
//    var chattingRoomIdx: String = "",
//    val imgUrl: String,
//    val name: String,
//    val lastChat: String,
//    val timeStamp: String
//)

class ChattingRoomListRecyclerAdapter(
    private val itemChattingRoomList: ArrayList<ChatListForm>,
    private val chattingRoomClickInterface: ChattingRoomClickInterface,
) : RecyclerView.Adapter<ChattingRoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChattingRoomViewHolder {
        val binding =
            ItemChattingRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChattingRoomViewHolder(binding, this.chattingRoomClickInterface)
    }

    override fun onBindViewHolder(holder: ChattingRoomViewHolder, position: Int) {
        holder.bind(itemChattingRoomList[position])
    }

    override fun getItemCount(): Int {
        return itemChattingRoomList.size
    }


}

// ?????????????????? ?????????
class ChattingRoomViewHolder(
    private val item: ItemChattingRoomBinding,
    private val ChattingRoomClickInterface: ChattingRoomClickInterface,
) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(itemChattingRoom: ChatListForm) {
        // glide ?????? ??????????????? ???????????? ????????? ??????
        item.textViewItemChattingRoomUserName.text = itemChattingRoom.name
        item.textViewItemChattingRoomLastMessage.text = itemChattingRoom.lastChat
        item.textViewItemChattingRoomTimeStamp.text = itemChattingRoom.timeStamp
        FirebaseStorage.getInstance().reference.child(itemChattingRoom.imgUrl).downloadUrl.addOnSuccessListener { that ->
            Glide.with(itemView.context).load(that).circleCrop()
                .into(item.imageViewItemChattingRoomProfileImg)
        }

        item.root.setOnClickListener {
            Log.d("????????? ????????? ??????", itemChattingRoom.chatRoomId)
            this.ChattingRoomClickInterface.chattingRoomClicked(itemChattingRoom.chatRoomId,
                itemChattingRoom.imgUrl)
        }
    }
}

interface ChattingRoomClickInterface {
    fun chattingRoomClicked(chattingRoomIdx: String, imgurl: String)
}
