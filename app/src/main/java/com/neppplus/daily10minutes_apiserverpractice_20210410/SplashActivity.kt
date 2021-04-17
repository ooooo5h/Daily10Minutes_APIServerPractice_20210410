package com.neppplus.daily10minutes_apiserverpractice_20210410

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ContextUtil

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            //        자동로그인을 해야하는 상황인지 검사(질문)

//        1. 사용자가 자동로그인 하겠다고 체크를 해또?
//        2. 저장된 토큰값이 이또? (=""이 아닌지)
//        둘 다 만족하면 바로 메인으로 꼬고

            val myIntent : Intent

            if (ContextUtil.getAutoLogin(mContext) && ContextUtil.getLoginToken(mContext) != "") {
//            자동로그인을 해도 된다 => MainActivity로 이동
                myIntent = Intent(mContext, MainActivity::class.java)
            }
            else {
//            로그인 화면에서 로그인 필요 = LoginActivitiy로 이동
                myIntent = Intent(mContext, LoginActivity::class.java)
            }

            startActivity(myIntent)
            finish()


        }, 2500)




    }


}