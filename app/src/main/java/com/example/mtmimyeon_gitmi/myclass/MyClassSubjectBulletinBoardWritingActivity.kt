package com.example.mtmimyeon_gitmi.myClass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.mtmimyeon_gitmi.R
import com.example.mtmimyeon_gitmi.databinding.ActivityMyClassSubjectBulletinBoardWritingBinding

class MyClassSubjectBulletinBoardWritingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyClassSubjectBulletinBoardWritingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyClassSubjectBulletinBoardWritingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
//        setSupportActionBar(binding.bottomAppBarMyClassSubjectBulletinBoardBottomAppBar)
        setSupportActionBar(binding.bottomAppBarMyClassSubjectBulletinBoardBottomAppBar)

        // 네비게이션 아이콘 클릭 -> 글 쓰기 취소
        binding.toolbarMyClassSubjectBulletinBoardToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { // toolbar inflate
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.bottomappbar_my_class_subject_bulletin_board_writing, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            else -> { }
        }
        return true
    }
}