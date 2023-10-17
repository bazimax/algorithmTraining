package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFunOld242(testAskOld242)
}

//242. Valid Anagram - Easy - https://leetcode.com/problems/valid-anagram/description/
private val testAskOld242 = arrayOf(
    Triple("anagram", "nagaram", true),
    Triple("rat", "car", false),
    Triple("Довод", "довод", true),
    Triple("ab", "a", false),
    Triple("αβμΩ", "μΩαβ", true),
    Triple("αβμΩ", "υφΣ@", false)
)

private fun testFunOld242(ask: Array<Triple<String, String, Boolean>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = isAnagram242(i.first, i.second)
            println("${answer == i.third} :: s1: ${i.first}, s2: ${i.second}, correctAnswer: ${i.third}, ans: $answer")
        }
        //println("time: $time")
    }
}

//242 > 20:42 - 21:01 () не сделал проверку на размер строк - 21:06 (~24мин) поправил, работает > OK (fast 48%, memory 42%)
private fun isAnagram242(s: String, t: String): Boolean {
    val map = mutableMapOf<Char, Int>()

    s.forEach {
        val itLower = it.lowercaseChar()
        if (map[itLower] != null) {
            map[itLower] = map[itLower]!! + 1
        } else {
            map[itLower] = 1
        }
    }

    t.forEach {
        val itLower = it.lowercaseChar()
        if (map[itLower] != null) {
            map[itLower] = map[itLower]!! - 1
            if (map[itLower]!! <= 0) map.remove(itLower)
        }
        else return false
    }
    return map.isEmpty()
}