package com.neppplus.daily10minutes_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Project
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ServerUtil
import org.json.JSONObject

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

        ServerUtil.getRequestProjectList(mContext, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val projectsArr = dataObj.getJSONArray("projects")

//                반복문 for 문으로 => projectsArr 내부를 하나씩 꺼내서 파싱

                for (i in 0 until projectsArr.length()) {

//                    [   ] 안에 있는 {  } 들을 하나 꺼내서 => 파싱 : Project 형태로 변환 => mProjects 에 추가

                    val projectObj = projectsArr.getJSONObject(i)

//                    Project 클래스에 보조 생성자 추가 => 재료 없이도 만들 수 있게
//                    Project 클래스에 함수(기능) 추가 => JSONObject를 넣으면 Project 형태로 변환해주는 기능
                    val project = Project.getProjectFromJson(projectObj)



                }




            }

        })
    }


}