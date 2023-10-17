package leetcode

import java.util.*

fun main(args: Array<String>) {
    println(addBinary67("1010", "1011"))
    println(addBinary67("11", "1"))
}

//67. Add Binary - Easy - https://leetcode.com/problems/add-binary/description/

//67 > 6:15 - 7:11 (~56мин) > OK (fast 54%, memory 6%)
private fun addBinary67(a: String, b: String): String {
    var sum = ""

    var memory = 0
    val numMax = Stack<Char>()
    val numMin = Stack<Char>()

    if (a.length > b.length) {
        a.forEach { numMax.push(it) }
        b.forEach { numMin.push(it) }
    }
    else {
        b.forEach { numMax.push(it) }
        a.forEach { numMin.push(it) }
    }

    while (numMin.isNotEmpty()) {
        val num1 = numMax.pop()
        val num2 = numMin.pop()


        if (num1 == '0' && num2 == '0') {
            if (memory == 0) {
                sum = "0$sum"
            }
            else {
                sum = "1$sum"
                memory--
            }
        }
        else if (num1 == '1' && num2 == '1') {
            if (memory == 0) {
                sum = "0$sum"
                memory++
            }
            else {
                sum = "1$sum"
            }
        }
        else { //else if ((num1 == '1' && num2 == '0') || (num1 == '0' && num2 == '1'))
            sum = if (memory == 0) "1$sum" else "0$sum"
        }
    }

    while (numMax.isNotEmpty()) {
        val num1 = numMax.pop()

        if (num1 == '0') {
            if (memory == 0) {
                sum = "0$sum"
            }
            else {
                sum = "1$sum"
                memory--
            }
        }
        else if (num1 == '1') {
            sum = if (memory == 0) "1$sum" else "0$sum"
        }
    }
    if (memory > 0) sum = "1$sum"

    return sum
}

/*
//the right decision, but not mine - (fast 75%, memory 70%)
private fun addBinary(a: String, b: String): String {
    return (a.toBigInteger(2) + b.toBigInteger(2)).toString(2)
}*/
