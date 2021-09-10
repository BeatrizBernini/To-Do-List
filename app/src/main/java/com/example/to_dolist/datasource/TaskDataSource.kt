package com.example.to_dolist.datasource

import com.example.to_dolist.model.Task

object TaskDataSource {
    private val list = arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {
        list.add(task.copy(id = list.size +1 ))
    }

}