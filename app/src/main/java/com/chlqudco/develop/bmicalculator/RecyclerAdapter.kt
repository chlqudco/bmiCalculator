package com.chlqudco.develop.bmicalculator

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chlqudco.develop.bmicalculator.databinding.RecyclerItemBinding
import java.text.SimpleDateFormat

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var listData = mutableListOf<RoomRecord>()
    var helper: RoomHelper?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val record = listData.get(position)
        holder.setRecord(record)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class Holder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        var mRoomRecord: RoomRecord ?=null

        init {
            binding.deleteButton.setOnClickListener {
                helper?.roomRecordDao()?.delete(mRoomRecord!!)
                listData.remove(mRoomRecord)
                notifyDataSetChanged()
            }

        }

        fun setRecord(record: RoomRecord){
            this.mRoomRecord = record
            val resultText = when{
                record.result>35 -> "고도 비만"
                record.result>30 -> "중도 비만"
                record.result>25 -> "경도 비만"
                record.result>23 -> "과체중"
                record.result>18 -> "정상 체중"
                else -> "저체중"
            }
            binding.bmiresultTextView.text = "BMI = ${record.result} (${resultText})"
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.timeTextView.text = "${sdf.format(record.datetime)}"
        }
    }
}
