package yandex.algorithms4training.class4

/**
 * E. Генерация правильных скобочных последовательностей - 2 //generationOfCorrectBracketSequences
 *
 * По данному числу n выведите все правильные скобочные последовательности из круглых и квадратных скобок длины n
 * в лексикографическом порядке.
 */

import yandex.runTest
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.LinkedList

fun main(args: Array<String>) {
    //generationRecursionV3New()
    runTest("generationOfCorrectBracketSequences", examplesE, ::generationRecursionV3New, )
    //runOneTest("generationOfCorrectBracketSequences", ::generation, "y_4_4_E_9_input.txt", "y_4_4_E_9_correctAns.txt", true)
}

private val examplesE = arrayOf(

    Pair("4\n",
        "(())\n" +
                "([])\n" +
                "()()\n" +
                "()[]\n" +
                "[()]\n" +
                "[[]]\n" +
                "[]()\n" +
                "[][]"), //1
    Pair("8", "..."), //9
    Pair("12", "..."), //13
    Pair("16", "..."), //17 ~
    Pair("2",
        "()\n" +
                "[]"),
    Pair("8", "2"),
    //Pair("12", "2"),
)

// OK (0.706s, 36.12Mb)
private fun generationRecursionV3New(){
    val input = BufferedReader(FileReader("input.txt")) //brackets2.in
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 ≤ N ≤ 16

    val ans = mutableListOf<String>()
    val output = BufferedWriter(FileWriter("output.txt")) //brackets2.out

    if (n % 2 == 0) {
        genBracketsV3New(ans, "", 0, 0, n/ 2, "", output)
    }

    output.append(ans.joinToString("\n"))
    output.flush()
}
private fun genBracketsV3New(result: MutableList<String>, s: String, open: Int, close: Int, n: Int, brackets: String, output: BufferedWriter) {
    //модифицированная версия для круглых скобок
    if (open == n && close == n) {
        result.add(s)

        //если mutableList уже большой записываем данные в файл
        if (result.size >= 1000) {
            output.append(result.joinToString("\n") + "\n")
            output.flush()

            //очищаем список
            result.clear()
        }

        return
    }

    if (open < n) genBracketsV3New(result, "$s(", open + 1, close, n, "$brackets(", output)
    if (open < n) genBracketsV3New(result, "$s[", open + 1, close, n, "$brackets[", output)


    val bracket = if (brackets != "") brackets[brackets.lastIndex] else '#'
    val bracketsNew = if (brackets.isNotEmpty()) brackets.substring(0, brackets.length - 1) else ""
    if (close < open && brackets != "") {
        if (bracket == '(') { genBracketsV3New(result, "$s)", open, close + 1, n, bracketsNew, output) }
        if (bracket == '[') { genBracketsV3New(result, "$s]", open, close + 1, n, bracketsNew, output) }
    }
}

// OK (0.774s, 37.04Mb)
private fun generationRecursionV6(){
    val input = BufferedReader(FileReader("input.txt")) //brackets2.in
    val n = input.readLine().trim().split(" ").map { it.toInt() }[0] // 0 ≤ N ≤ 16

    val ans = mutableListOf<String>()
    val output = BufferedWriter(FileWriter("output.txt")) //brackets2.out

    if (n % 2 == 0) {

        val brackets = LinkedList<Char>()
        genBracketsV6(ans, "", 0, 0, n/ 2, brackets, output)
    }

    output.append(ans.joinToString("\n"))
    output.flush()
}
private fun genBracketsV6(result: MutableList<String>, s: String, open: Int, close: Int, n: Int, brackets: LinkedList<Char>, output: BufferedWriter) {
    //модифицированная версия для круглых скобок
    if (open == n && close == n) {
        result.add(s)

        //если mutableList уже большой записываем данные в файл
        if (result.size >= 1000) {
            output.append(result.joinToString("\n") + "\n")
            output.flush()

            //очищаем список
            result.clear()
        }
        return
    }

    if (open < n) {
        brackets.add('(')
        genBracketsV6(result, "$s(", open + 1, close, n, brackets, output)
        brackets.pollLast()
    }
    if (open < n) {
        brackets.add('[')
        genBracketsV6(result, "$s[", open + 1, close, n, brackets, output)
        brackets.pollLast()
    }

    if (close < open && brackets.isNotEmpty()) {
        if (brackets.pollLast() == '(') {
            genBracketsV6(result, "$s)", open, close + 1, n, brackets, output)
            brackets.add('(')
        }else brackets.add('[')

        if (brackets.pollLast() == '[') {
            genBracketsV6(result, "$s]", open, close + 1, n, brackets, output)
            brackets.add('[')
        }else brackets.add('(')
    }
}



/*
// только круглые скобки
fun generateParenthesisInputV1(n: Int): List<String> {
    // Resultant list
    val result: MutableList<String> = ArrayList()
    /// Recursively generate parentheses
    generateParenthesis(result, "", 0, 0, n)
    //generateParenthesisV2(result, "", 0, 0, n)
    return result
}
fun generateParenthesis(result: MutableList<String>, s: String, open: Int, close: Int, n: Int) {
    println(" in  " + "*".repeat(open) + " $s " + "-".repeat(close))
    // Base case
    if (open == n && close == n) {
        result.add(s)
        return
    }
    // If the number of open parentheses is less than the given n
    if (open < n) {
        generateParenthesis(result, "$s(", open + 1, close, n)
    }
    // If we need more close parentheses to balance
    if (close < open) {
        generateParenthesis(result, "$s)", open, close + 1, n)
    }
    println(" out " + "*".repeat(open) + " $s " + "-".repeat(close))
}
*/
