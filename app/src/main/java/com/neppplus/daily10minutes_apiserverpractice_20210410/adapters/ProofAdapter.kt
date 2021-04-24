package com.neppplus.daily10minutes_apiserverpractice_20210410.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.neppplus.daily10minutes_apiserverpractice_20210410.R
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Project
import com.neppplus.daily10minutes_apiserverpractice_20210410.datas.Proof
import org.w3c.dom.Text
import java.text.SimpleDateFormat

class ProofAdapter(
        val mContext : Context,
        resId : Int,
        val mList : List<Proof>) : ArrayAdapter<Proof>(mContext, resId, mList) {

    val inflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = inflater.inflate(R.layout.proof_list_item, null)
        }

        val row = tempRow!!

        val proofDate = mList[position]

        val writerProfileImg = row.findViewById<ImageView>(R.id.writerProfileImg)
        val writerNicknameTxt = row.findViewById<TextView>(R.id.writerNicknameTxt)
        val proofTimeTxt = row.findViewById<TextView>(R.id.proofTimeTxt)
        val proofContentTxt = row.findViewById<TextView>(R.id.proofContentTxt)
        val proofImg = row.findViewById<ImageView>(R.id.proofImg)
        val likeBtn = row.findViewById<Button>(R.id.likeBtn)
        val replyBtn = row.findViewById<Button>(R.id.replyBtn)

        proofContentTxt.text = proofDate.content

//        인증글의 사진이 하나도 없다면 이미지뷰를 숨김처리하자
//        하나라도 있다면 맨 앞 (0번칸)의 이미지를 반영하자

        if (proofDate.imageUrls.size == 0) {

            proofImg.visibility = View.GONE
        }
        else {
            proofImg.visibility = View.VISIBLE

//            0번칸 이미지 반영

            Glide.with(mContext).load(proofDate.imageUrls[0]).into(proofImg)
        }

//        인증글에 달린 작성자 정보를 받아서 UI에 반영하자
        Glide.with(mContext).load(proofDate.writer.profileImgUrls[0]).into(writerProfileImg)
        writerNicknameTxt.text = proofDate.writer.nickName

//        인증글에 달린 작성 일시를 '오전 8시 5분' 형태로 가공해서 텍스트뷰에 반영

        val sdf = SimpleDateFormat("a H시 m분")

        proofTimeTxt.text = sdf.format(proofDate.proofDateTime.time)

        return row



    }

}