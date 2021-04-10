package com.neppplus.daily10minutes_apiserverpractice_20210410.utils

import android.util.Log
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.json.JSONObject
import java.io.IOException

class ServerUtil {

//    화면(액티비티)의 입장에서, 서버에 다녀오면 할 행동을 적는 가이드북
//    행동 지침을 기록하는 개념 : Interface

    interface JsonResponseHandler {
        fun onResponse(jsonObj : JSONObject)

    }


    companion object {

//        이 중괄호안에 적히는 변수나 함수는 ServerUtil. 변수 or 함수() 등으로 클래스 자체의 기능으로 활용 가능 => 변수로 안만들어도 된다는 뜻
//        JAVA : static 개념에 대응됨

//        서버의 호스트 주소 저장
        val HOST_URL = "http://15.164.153.174"

//        서버에 로그인을 요청하는 기능 => 함수로 만들고 사용하자
//        필요한 재료 : 이메일, 비번 + 로그인 결과에 대한 처리 방안(=가이드북)

        fun postRequestLogin(email : String, pw : String, handler : JsonResponseHandler?) {

//            어느 주소로 가야하나? 호스트주소/기능주소
//            ex. 로그인 => http://15.164.153.174  HOST/user => 최종 주소 완성

            val urlString = "${HOST_URL}/user"

//            갈때 어떤 파라미터를 가져가야하나? POST vs GET 에 따라 다르다.
//            POST - formData 에 데이터 첨부

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build()

//            모든 정보를 종합하고 갈때 어떤 메쏘드로 가느냐?

            val request = Request.Builder()
                .url(urlString)   // 어디로 가는지?
                .post(formData)   // POST 방식 - 필요 데이터 (formData) 들고 가도록
                .build()

//            정리된 정보를 들고 => 실제로 API 요청 진행

//            클라이언트로써 동작하는 코드를 쉽게 작성하도록 도와주는 라이브러리 : OkHttp
            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

//                서버에 연결 자체를 실패 (서버에 접근할 수 없는 상황)
//                    데이터 요금 소진, 서버가 터짐 등의 이유로 아예 연결 자체에 실패한것이지 아이디나 비밀번호틀린게 아님!!  물리적으로 차단인거.

//                    반대 <-> 로그인 비번 틀림, 회원가입 이메일 중복 등 로직 실패 => 연결은 성공, 결과만 실패
//                    여기에서 처리하지 않는다
//                    이런 경우 없다는 전제하에 코딩을 짜자

                }

                override fun onResponse(call: Call, response: Response) {

//                    서버의 응답을 받아내는데 성공한 경우
//                    응답 (response) > 내부의 본문 (body)만 활용할거야 > 이걸 우리가 알아보기 쉽게 String 의 형태로 저장해보자

//                    toString() x, string() 활용!!
                    val bodyString = response.body!!.string()

//                    bodyString은 인코딩 되어있는 상태라 => 사람이 읽기가 어렵다 = 한글이 깨진다
//                    bodyString을 => JsonObject로 변환시키면 => 읽을 수 있게 됨

                    val jsonObj = JSONObject(bodyString)

                    Log.d("서버응답", jsonObj.toString())

//                    받아낸 서버 응답 내용은 => 여기(ServerUtil)서 활용하는게 아니라
//                    화면에서 UI에 반영하기 위한 재료로 사용
//                    ex. code : 400 => 로그인 실패 토스트는 (메인)에서 띄우겠지

//                    완성한 jsonObj 변수를 액티비티에 넘겨주자 => 파싱 등 처리는 액티비티화면에서 작성하자
                    handler?.onResponse(jsonObj)

                }


            })




        }

//        서버의 회원가입 기능.

        fun putRequestSignUp(email : String, pw : String, nickname : String, handler: JsonResponseHandler?) {

            val urlString = "${HOST_URL}/user"

            val formData = FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickname)
                .build()

            val request = Request.Builder()
                .url(urlString)
                .put(formData)
                .build()

            val client = OkHttpClient()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val bodyString = response.body!!.string()
                    val jsonObj = JSONObject(bodyString)
                    Log.d("서버응답", jsonObj.toString())
                    handler?.onResponse(jsonObj)

                }


            })

        }

//        이메일 중복 체크 기능.

        fun getRequestEmailCheck(email : String, handler: JsonResponseHandler?) {

//            어디로? + 어떤데이터? => URL을 만들 때 한꺼번에 전부 적어야한다
//            주소를 적는게 복잡해짐 => 복잡한 가공을 도와주는 (OkHttp 라이브러리 제공)클래스 활용 => URLBuilder

            val urlBuilder = "${HOST_URL}/email_check".toHttpUrlOrNull()!!.newBuilder()

//            만들어진 기초 URL에 필요한 파라미터들을 붙여주자
            urlBuilder.addEncodedQueryParameter("email", email)

//            붙일 정보가 다 붙었으면 최종적으로 String형태로 변환
            val urlString = urlBuilder.build().toString()

            Log.d("가공된 url", urlString)

        }


    }
}