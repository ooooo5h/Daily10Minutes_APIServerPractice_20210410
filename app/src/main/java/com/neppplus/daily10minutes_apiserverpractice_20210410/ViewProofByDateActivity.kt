package com.neppplus.daily10minutes_apiserverpractice_20210410

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import com.neppplus.daily10minutes_apiserverpractice_20210410.adapters.ProofAdapter
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Project
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Proof
import com.neppplus.daily10minutes_apiserverpractice_20210410.utils.ServerUtil
import kotlinx.android.synthetic.main.activity_view_proof_by_date.*
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*
import kotlin.collections.ArrayList

class ViewProofByDateActivity : BaseActivity() {

    lateinit var mProject : Project

    val mSelectedDate = Calendar.getInstance()

    val mProofList = ArrayList<Proof> ()

    lateinit var mProofAdapter : ProofAdapter

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
//                    2020년 5월 3일의 양식으로 출력
                    val simpleDateFormat = SimpleDateFormat("yyyy년 M월 d일")
                    dateTxt.text = simpleDateFormat.format(mSelectedDate.time)


//                    서버에서, 선택된 날짜에 해당하는 글을 불러오는 작업하자
                    getProofListByDate()


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

        mProject = intent.getSerializableExtra("project") as Project

        mProofAdapter = ProofAdapter(mContext, R.layout.proof_list_item, mProofList)
        proofListView.adapter = mProofAdapter

    }

//    서버에서 선택된 날짜의 글을 받아와주는 함수

    fun getProofListByDate() {

        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val dateStr = sdf.format(mSelectedDate.time)

        ServerUtil.getRequestProjectProofListByDate(mContext, mProject.id, dateStr, object : ServerUtil.JsonResponseHandler {
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val projectObj = dataObj.getJSONObject("project")
                val proofsArr = projectObj.getJSONArray("proofs")

                for (i in 0 until proofsArr.length()) {

//                    인증글 JSON -> Proof 객체로 변환 -> mProofList에 추가

                    mProofList.add(Proof.getProofFromJson(proofsArr.getJSONObject(i)))

                }

//                나중에 게시글 목록을 추가로 불러옴 -> 리스트뷰 내용 변경 -> 어댑터 새로고침
                runOnUiThread {
                    mProofAdapter.notifyDataSetChanged()
                }

            }


        })


    }



}