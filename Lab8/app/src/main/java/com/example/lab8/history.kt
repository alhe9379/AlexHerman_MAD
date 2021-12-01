package com.example.lab8

data class history(var questions: ArrayList<String>, var answers: ArrayList<String>){
    fun addQuestion(question:String){
        questions.add(question)
    }

    fun addAnswer(answer:String){
        questions.add(answer)
    }

}