package com.chlqudco.develop.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.chlqudco.develop.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var helper: RoomHelper?=null

    val adapter = RecyclerAdapter()

    private val heightEditText: EditText by lazy {
        findViewById(R.id.heightEditText)
    }

    private val weightEditText: EditText by lazy {
        findViewById(R.id.weightEditText)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initCalculateButton()
        initRecordButton()

        helper = Room.databaseBuilder(this,RoomHelper::class.java, "room_memo")
            .allowMainThreadQueries()
            .build()

        adapter.helper = helper

        adapter.listData.addAll(helper?.roomRecordDao()?.getAll()?: listOf())

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }

    private fun initCalculateButton(){
        binding.calculateButton.setOnClickListener {
            if (heightEditText.text.isEmpty() || weightEditText.text.isEmpty()){
                Toast.makeText(this,"작성되지 않은 부분이 있습니다.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                val bmi = round(weightEditText.text.toString().toFloat() / (heightEditText.text.toString().toFloat()/100.0).pow(2.0)).toInt()
                val record = RoomRecord(bmi,System.currentTimeMillis())
                helper?.roomRecordDao()?.insert(record)
                adapter.listData.clear()
                adapter.listData.addAll(helper?.roomRecordDao()?.getAll()?: listOf())
                adapter.notifyDataSetChanged()

                val intent = Intent(this,ResultActivity::class.java)
                intent.putExtra("weight",weightEditText.text.toString().toInt())
                intent.putExtra("height",heightEditText.text.toString().toInt())

                binding.progressBar.isVisible = true
                binding.mainLayout.isVisible = false
                Thread {
                    runOnUiThread {
                        Thread.sleep(3000)
                        binding.progressBar.visibility = View.GONE
                        binding.mainLayout.isVisible = true
                        startActivity(intent)
                    }
                }.start()

            }
        }
    }

    private fun initRecordButton(){
        binding.recordButton.setOnClickListener {
            binding.mainLayout.visibility= View.GONE
            binding.recordLayout.visibility = View.VISIBLE
        }

        binding.closeButton.setOnClickListener {
            binding.mainLayout.visibility= View.VISIBLE
            binding.recordLayout.visibility = View.GONE
        }
    }
}