package yandex.algorithms4training.warmup

/**
 * I. Правильная скобочная последовательность
 *
 * Рассмотрим последовательность, состоящую из круглых, квадратных и фигурных скобок. Программа должна определить,
 * является ли данная скобочная последовательность правильной. Пустая последовательность является правильной.
 * Если A — правильная, то последовательности (A), [A], {A} — правильные. Если A и B — правильные последовательности,
 * то последовательность AB — правильная.
 */


import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter
import java.util.LinkedList

fun main(args: Array<String>) {
    theCorrectParentheticalSequence()
}

// 18:15 - 18:25 (10мин) > OK (254ms, 15.95Mb)
fun theCorrectParentheticalSequence(){
    val input = BufferedReader(FileReader("input.txt")).readLine() // <100000
    var answer = "yes"

    val deque = LinkedList<Char>()

    input.forEach {
        when (it) {
            '{','[','(' -> deque.addLast(it)
            '}' -> if (deque.pollLast() != '{') answer = "no"
            ']' -> if (deque.pollLast() != '[') answer = "no"
            ')' -> if (deque.pollLast() != '(') answer = "no"
        }
    }
    if(deque.isNotEmpty()) answer = "no"

    val output = BufferedWriter(FileWriter("output.txt"))
    output.write(answer)
    output.flush()
}
