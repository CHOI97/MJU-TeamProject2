package com.example.mtmimyeon_gitmi.myclass

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mtmimyeon_gitmi.databinding.ActivityMyclassTimetableBinding
import com.github.tlaabs.timetableview.Schedule
import com.github.tlaabs.timetableview.Time

class MyClassTimeTableActivity: AppCompatActivity() {
    private lateinit var binding: ActivityMyclassTimetableBinding

    // This property is only valid between onCreateView and OnDestroyView
    private val schedules = ArrayList<Schedule>()

    companion object {
        fun getInstance(): MyClassTimeTableActivity {
            return MyClassTimeTableActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyclassTimetableBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initTimeTable()
    }


    private fun initTimeTable() {
        binding.timetable.setOnStickerSelectEventListener { idx, schedules ->
            binding.timetable.edit(idx, schedules)
        }

        // test code
        var schedule = Schedule()
        schedule.classTitle = "Data Structure"; // sets subject
        schedule.classPlace = "5401"; // sets place
        schedule.professorName = "류연승"; // sets professor
        schedule.startTime = Time(10, 0); // sets the beginning of class time (hour,minute)
        schedule.endTime = Time(13, 30); // sets the end of class time (hour,minute)
        schedules.add(schedule);

        schedule = Schedule()
        schedule.classTitle = "시스템클라우드보안"; // sets subject
        schedule.classPlace = "5407"; // sets place
        schedule.professorName = "조민경"; // sets professor
        schedule.day = 2
        schedule.startTime = Time(14, 0); // sets the beginning of class time (hour,minute)
        schedule.endTime = Time(16, 50); // sets the end of class time (hour,minute)
        schedules.add(schedule);

        schedule = Schedule()
        schedule.classTitle = "고급객체지향프로그래밍"; // sets subject
        schedule.classPlace = "5401"; // sets place
        schedule.professorName = "조세형"; // sets professor
        schedule.day = 3
        schedule.startTime = Time(9, 0); // sets the beginning of class time (hour,minute)
        schedule.endTime = Time(10, 50); // sets the end of class time (hour,minute)
        schedules.add(schedule);

        schedule = Schedule()
        schedule.classTitle = "Algorithm"; // sets subject
        schedule.classPlace = "5403"; // sets place
        schedule.professorName = "주우석"; // sets professor
        schedule.day = 4
        schedule.startTime = Time(11, 0); // sets the beginning of class time (hour,minute)
        schedule.endTime = Time(3, 50); // sets the end of class time (hour,minute)
        schedules.add(schedule);


        schedules.add(schedule);
        //.. add one or more schedules
        binding.timetable.add(schedules);
        binding.timetable.setHeaderHighlight(2)
    }
}