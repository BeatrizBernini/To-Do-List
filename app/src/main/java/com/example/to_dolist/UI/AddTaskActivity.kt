package com.example.to_dolist.UI

import android.app.Activity
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.to_dolist.databinding.ActivityAddTaskBinding
import com.example.to_dolist.datasource.TaskDataSource
import com.example.to_dolist.extensions.format
import com.example.to_dolist.extensions.text
import com.example.to_dolist.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertlisteners()
    }

    private fun insertlisteners() {
            binding.tilDate.editText?.setOnClickListener() {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")}

            binding.tilHour.editText?.setOnClickListener() {
                val timePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build()
                timePicker.addOnPositiveButtonClickListener() {
                    val minute = if(timePicker.minute in 0..9) "0$(timePicker.minute)" else timePicker.minute
                    val hour = if(timePicker.hour in 0..9) "0$(timePicker.minute)" else timePicker.hour

                    binding.tilHour.text = "$hour:$minute"
                }
                timePicker.show(supportFragmentManager, "null")
            }

            binding.btnCancel.setOnClickListener() {
                finish()
            }

            binding.btnNewTask.setOnClickListener() {
                val task = Task(
                    title = binding.tilTile.text,
                    date = binding.tilDate.text,
                    hour = binding.tilHour.text
                )
                TaskDataSource.insertTask(task)

                setResult(Activity.RESULT_OK)
                finish()
            }
            TODO("Not yet implemented")
        }
    }
