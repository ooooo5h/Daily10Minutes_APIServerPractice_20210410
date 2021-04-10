package com.neppplus.daily10minutes_apiserverpractice_20210410.utils

import android.content.Context

class ContextUtil {

    companion object{

        private val prefName = "Daily10MinutesPref"

        private val AUTO_LOGIN = "AUTO_LOGIN"

//        자동 로그인 설정여부 저장(setter) 함수

        fun setAutoLogin(context : Context, autoLogin : Boolean) {

//            메모장을 열어서 => 변수에 담아두자
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

//            변수를 이용해서 => 실제 저장 여부 기록
            pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply()

        }

//        설정해둔 자동로그인 여부 확인(getter) 함수

        fun getAutoLogin(context: Context) : Boolean {

//            저장할때와 같은 메모장 활용
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
//            자동로그인 항목에 저장된 값을 리턴처리(=결과로 지정)
            return pref.getBoolean(AUTO_LOGIN, false)

        }




    }
}