package leetcode

import java.util.*
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFunOld20(testAskOld20)
}

//20. Valid Parentheses - Easy - https://leetcode.com/problems/valid-parentheses/description/

private val testAskOld20 = arrayOf(
    Pair("[](){}", true),
    Pair("()", true),
    Pair("[)", false),
    Pair("[()]{}", true),
    Pair("[]({}", false),
    Pair("", true),
    Pair("]{}", false),
    Pair("([)]", false)
)
private fun testFunOld20(ask: Array<Pair<String, Boolean>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = isValid20(i.first)//sortedSquares977(i.first)

            println("${answer == i.second} :: in: [${i.first}], correctAnswer: ${i.second}, ans: $answer")
        }
        //println("time: $time")
    }
}

//20 > 18:05 - 18:11(подготовка тестовых значений), 15 мин перерыв - 19:35 (~1ч15м) > OK (fast 79%, memory 51%)
private fun isValid20(s: String): Boolean {
    val charOpenA = '('
    val charCloseA = ')'

    val charOpenB = '['
    val charCloseB = ']'

    val charOpenC = '{'
    val charCloseC = '}'

    val charStack = mutableListOf<Char>()

    s.forEach {
        if (charStack.isEmpty() &&
            (it == charCloseA || it == charCloseB || it == charCloseC)
        ) return false
        else {
            if (it == charOpenA || it == charOpenB || it == charOpenC) {
                charStack.add(it)
            }

            else {
                if (
                    charStack.last() == charOpenA && it == charCloseA ||
                    charStack.last() == charOpenB && it == charCloseB ||
                    charStack.last() == charOpenC && it == charCloseC
                ) {
                    charStack.removeLast()
                }
                else return false
            }
        }
    }
    return charStack.isEmpty()
}

//20v2 > OK (fast 65%, memory 82%)
private fun isValidV2Deque(s: String): Boolean {
    val charStack = LinkedList<Char>()
    s.forEach {

        when (it) {
            '(', '{', '[' -> charStack.push(it)
            ')' -> if (charStack.poll() != '(') return false
            '}' -> if (charStack.poll() != '{') return false
            ']' -> if (charStack.poll() != '[') return false
        }
        //if (charStack.isNotEmpty()) println("end, charStack.last: ${charStack.last()}")
    }
    return charStack.isEmpty()
}