package com.neppplus.listviewpractice

import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.neppplus.listviewpractice.adapters.StudentAdapter
import com.neppplus.listviewpractice.datas.Student

class MainActivity : AppCompatActivity() {

    val mStudentList = ArrayList<Student>()

    lateinit var mAdapter : StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mStudentList.add(Student("지니", 1988))
        mStudentList.add(Student("김민상", 1995))
        mStudentList.add(Student("조상민", 1975))
        mStudentList.add(Student("이영희", 1996))
        mStudentList.add(Student("박철수", 2000))
        mStudentList.add(Student("정민규", 1984))
        mStudentList.add(Student("장소영", 1962))

        mAdapter = StudentAdapter(this, R.layout.student_list_item, mStudentList)


        val studentListView = findViewById<ListView>(R.id.studentListView)
        studentListView.adapter = mAdapter

        // 짧게 누르기
        studentListView.setOnItemClickListener{parent,view,position,id ->
            val clickedStudent = mStudentList[position]

            Toast.makeText(this, "${clickedStudent.name}이 클릭됨", Toast.LENGTH_SHORT).show()
        }

        // 길게 누르기
        studentListView.setOnItemLongClickListener{parent,view,position,id ->
//            val longClickedStudent = mStudentList[position]
//
//            Toast.makeText(this, "${longClickedStudent.name}길게 클릭됨", Toast.LENGTH_SHORT).show()

            // 목록에서 제거

            mStudentList.removeAt(position)

            // 삭제 반영
            mAdapter.notifyDataSetChanged()


            return@setOnItemLongClickListener true // true: long 클릭 전용, false : 짧게 누른것도
        }
    }
}