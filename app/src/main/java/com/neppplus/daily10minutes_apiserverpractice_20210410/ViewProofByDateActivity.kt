package com.neppplus.daily10minutes_apiserverpractice_20210410

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import kotlinx.android.synthetic.main.activity_view_proof_by_date.*
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class ViewProofByDateActivity : BaseActivity() {

    val mSelectedDate = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_proof_by_date)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        selectDateBtn.setOnClickListener {

//          #1  날짜가 선택되면 어떡할건지 먼저 변수에 저장

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    선택된 년/월/일이 변수에 담겨있다 이걸 텍스트뷰에 반영해주자

                    Log.d("선택된 날짜", "${year}년 ${month}월 ${dayOfMonth}일")

//                    선택일자를 멤버변수에 저장

                    mSelectedDate.set(year, month, dayOfMonth)

//                    SimpleDateFormat 이용, 날짜를 String 양식으로 가공
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
                    dateTxt.text = simpleDateFormat.format(mSelectedDate.time)


                }


            }


//          #2  실제로 달력을 띄우기 (AlertDialog 와 유사)

            val datePickerDialog = DatePickerDialog(mContext, dateSetListener,
                mSelectedDate.get(Calendar.YEAR),
                mSelectedDate.get(Calendar.MONTH),
                mSelectedDate.get(Calendar.DAY_OF_MONTH))
            datePickerDialog.show()


        }

    }

    override fun setValues() {

    }



}