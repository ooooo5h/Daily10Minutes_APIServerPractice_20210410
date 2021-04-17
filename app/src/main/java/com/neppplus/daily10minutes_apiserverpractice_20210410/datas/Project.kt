package com.neppplus.daily10minutes_apiserverpractice_20210410.datas

import org.json.JSONObject
import java.io.Serializable

class Project(
        var id : Int,
        var title : String,
        var imageUrl : String,
        var description : String,
        var onGoingUserCount : Int) : Serializable {

//    보조 생성자 추가 => 왜? Project() 만으로도 만들 수 있게 하기 위해서

    constructor() : this(0, "", "", "", 0)

//    기능을 추가 => JSON에 넣으면(input) : 알아서 Project로 변환(return)해주는 기능을 구현하자 => 단순 기능은 companion object를 이용하는게 편하다

    companion object {

        fun getProjectFromJson (jsonObj : JSONObject) : Project {
            val project = Project()

//            jsonObj에서 정보 추출 => projecet의 하위 항목 반영

            project.id = jsonObj.getInt("id")
            project.title = jsonObj.getString("title")
            project.imageUrl = jsonObj.getString("img_url")
            project.description = jsonObj.getString("description")

            project.onGoingUserCount = jsonObj.getInt("ongoing_users_count")

//            완성된 project가 결과로 나가도록
            return project

        }

    }

}