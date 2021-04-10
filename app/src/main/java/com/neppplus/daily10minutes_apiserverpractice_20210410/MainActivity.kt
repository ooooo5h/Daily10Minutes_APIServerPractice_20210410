package com.neppplus.daily10minutes_apiserverpractice_20210410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

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

        }

    }

    override fun setValues() {

    }


}