package com.neppplus.daily10minutes_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Project
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ServerUtil

class MainActivity : BaseActivity() {

    val mProject = ArrayList<Project>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()

    }
    override fun setupEvents() {

    }

    override fun setValues() {

//        서버에서 => 보여줄만한 프로젝트 목록이 어떤 것들이 있는 지 받아서 Project() 형태로 변환해서 mProjects 에 하나하나 추가해주자
        getProjectListFromServer()


    }

    fun getProjectListFromServer() {
//        실제로 서버에서 받아오는 기능만 수행

//        ServerUtil.
    }


}