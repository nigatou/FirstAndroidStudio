package com.example.myfirstapplication.service

object ConvertNumberService {
    fun convertNumber(num: Int): String? {
        val number = num.toString()
        when (num) {
            in 0..999 -> {
                return number
            }
            in 1000..1099 -> {
                return number[0] + "K"
            }
            in 1100..9999 -> {
                return number[0] + "." + number[1] + "K"
            }
            in 10000..999999 -> {
                return number[0] + "K"
            }
            in 1000000..1099999 -> {
                return number[0] + "M"
            }
            in 1100000..9999999 -> {
                return number[0] + "." + number[1] + "M"
            }
        }
        return null
    }
}