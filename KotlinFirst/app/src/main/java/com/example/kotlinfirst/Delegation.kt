package com.example.kotlinfirst

fun main(){
    val obj=TaskManager(TaskCreate(),TaskExecute())
    obj.Create()
    obj.ExecuteTask()
}
interface Task{
    fun Create()
}
interface ExecuteTask{
    fun ExecuteTask()
}
class TaskCreate : Task{
    override fun Create() {
        println("Task is created...")
    }
}

class TaskExecute:ExecuteTask{
    override fun ExecuteTask() {
        println("Task is executed successfully...")
    }
}

class TaskManager(val creator :Task, val executor:ExecuteTask):Task  by creator,ExecuteTask by executor{

}