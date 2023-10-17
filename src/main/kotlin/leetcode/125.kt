package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFunOld125(testAskOld125)
}

//125. Valid Palindrome - Easy - https://leetcode.com/problems/valid-palindrome/description/
private val testAskOld125 = arrayOf(
    //Triple(listNodeOfInt(1,2,4), listNodeOfInt(1,3,4), listNodeOfInt(1,1,2,3,4,4)),
    Pair("Test1. ,replace", false),
    Pair("A man, a plan, a canal: Panama", true),
    Pair("race a car", false),
    Pair(" ", true),
    Pair("0P", false)
    //Pair(intArrayOf(), intArrayOf())
)
private fun testFunOld125(ask: Array<Pair<String, Boolean>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = isPalindrome125(i.first)//sortedSquares977(i.first)
            //println("${answer contentEquals i.second} :: in: ${i.first.joinToString()}, " +
            //        "correctAnswer: ${i.second.joinToString()}, ans: ${answer.joinToString()}") //contentEquals - Array
            //println("${answer == i.second} :: in: [${i.first.joinToString(",")}], target: ${i.second}, " +
            //       "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
            println("${answer == i.second} :: in: ${i.first}, correctAnswer: ${i.second}, ans: $answer")
            //println("${listNodeToList(answer) == listNodeToList(i.third)} :: list1: ${listNodeToList(i.first)}, list2: ${listNodeToList(i.second)}, " +
            //       "correctAnswer: ${listNodeToList(i.third)}, ans: ${listNodeToList(answer)}") //contentEquals - ListNode
        }
        //println("time: $time")
    }
}

//125 > 17:46 - 5мин перерыв - 18:25 (~35мин) > OK (fast 24%, memory 22%)
private fun isPalindrome125(s: String): Boolean {

    val stringCorrect = s.replace(("[^A-Za-z0-9]").toRegex(), "").lowercase()
    if (stringCorrect.isEmpty()) return true

    var l = 0
    var r = stringCorrect.count() - 1

    while (l < r) {
        if (stringCorrect[l] != stringCorrect[r]) return false
        l++
        r--
    }

    return true
}

//125v2 > последнее обновление через filter (fast 72%, memory 91%)
private fun isPalindrome125V2(s: String): Boolean {

    val stringCorrect = s.filter { it.isLetterOrDigit() }.lowercase()

    var l = 0
    var r = stringCorrect.count() - 1

    while (l < r) {
        if (stringCorrect[l] != stringCorrect[r]) return false
        l++
        r--
    }

    return true
}

/*
    //backup - REGEX
    //var answer = "Test1. ,replace"
    //val reg = Regex("[^A-Za-z0-9 ]") //оставляем буквы, цифры и пробел
    //Regex("[^A-Za-z0-9]") //оставляем буквы, цифры
    //Regex("[^A-Za-z]") //оставляем буквы
    //answer = reg.replace(answer, "") // works
    //answer = answer.replace(("[^\\w\\d]").toRegex(), "")//оставляем буквы, цифры
    //println(answer)
    //answer.filter { !it.isWhitespace() } //убираем пробелы
    */