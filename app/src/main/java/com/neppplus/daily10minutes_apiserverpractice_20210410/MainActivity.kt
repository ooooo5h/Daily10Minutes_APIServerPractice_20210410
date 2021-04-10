package com.neppplus.daily10minutes_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        loginBtn.setOnClickListener {

//            입력한 이메일/비번 추출

            val inputEmail = emailEdt.text.toString()
            val inputPassword = passwordEdt.text.toString()


//            서버에 로그인 요청(배울 내용)

            ServerUtil.postRequestLogin(
                inputEmail,
                inputPassword,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

                        val codeNum = jsonObj.getInt("code")

                        if (codeNum == 200) {
//                        로그인 성공한 경우

                        } else {
//                        로그인 실패 - 토스트로 띄워보자

                            runOnUiThread {
                                Toast.makeText(mContext, "로그인 실패", Toast.LENGTH_SHORT).show()

                            }

                        }


                    }


                })

        }
    }

    override fun setValues() {

    }
}


