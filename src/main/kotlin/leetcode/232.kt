package leetcode

import java.util.*

fun main(args: Array<String>) {
}

//232. Implement Queue using Stacks - Easy - https://leetcode.com/problems/implement-queue-using-stacks/description/

//232 > 9:20 - 9:33 (~13мин) > OK (fast 89%, memory 70%)
class MyQueue232() {

    private val list = LinkedList<Int>()

    fun push(x: Int) {
        list.add(x)
    }

    fun pop(): Int {
        return list.pollFirst()
    }

    fun peek(): Int {
        return list.peek()
    }

    fun empty(): Boolean {
        return list.isEmpty()
    }
}