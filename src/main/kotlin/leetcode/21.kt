package leetcode

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    testFunOld21(testAskOld21)
}

//21. Merge Two Sorted Lists - Easy - https://leetcode.com/problems/merge-two-sorted-lists/description/

private val testAskOld21 = arrayOf(
    Triple(listNodeOfInt(1,2,4), listNodeOfInt(1,3,4), listNodeOfInt(1,1,2,3,4,4)),
    Triple(listNodeOfInt(0), listNodeOfInt(1,3,4), listNodeOfInt(0,1,3,4)),
    Triple(listNodeOfInt(1,3,6), listNodeOfInt(0), listNodeOfInt(0,1,3,6)),
    Triple(listNodeOfInt(-4,-2,0,1,4), listNodeOfInt(-9,-8,-6,-6,-5,-1,1,4,9), listNodeOfInt(-9,-8,-6,-6,-5,-4,-2,-1,0,1,1,4,4,9)),
    //Pair("([)]", false)
    //Pair(intArrayOf(), intArrayOf())
)
private fun testFunOld21(ask: Array<Triple<ListNode?, ListNode?, ListNode?>>) {
    for (i in ask) {
        val time = measureTimeMillis {
            val answer = mergeTwoLists21v2(i.first, i.second)//sortedSquares977(i.first)
            //println("${answer contentEquals i.second} :: in: ${i.first.joinToString()}, " +
            //        "correctAnswer: ${i.second.joinToString()}, ans: ${answer.joinToString()}") //contentEquals - Array
            //println("${answer == i.second} :: in: [${i.first.joinToString(",")}], target: ${i.second}, " +
            //       "correctAnswer: ${i.third}, ans: $answer") //contentEquals - Array
            //println("${answer == i.second} :: in: [${i.first}], correctAnswer: ${i.second}, ans: $answer")
            println("${listNodeToList(answer) == listNodeToList(i.third)} :: list1: ${listNodeToList(i.first)}, list2: ${listNodeToList(i.second)}, " +
                    "correctAnswer: ${listNodeToList(i.third)}, ans: ${listNodeToList(answer)}") //contentEquals - ListNode
        }
        //println("time: $time")
    }
}

private fun listNodeExample() : ListNode {
    val a1 = ListNode(1)
    val a2 = ListNode(2)
    val a3 = ListNode(4)
    a1.next = a2
    a2.next = a3

    return a1
}
private fun listNodeExample2() : ListNode {
    val a1 = ListNode(1)
    val a2 = ListNode(2)
    a1.next = a2
    a2.next = ListNode(4)

    return a1
}


//21 > 21:20 - 22:40 (закончил изучение ListNode), 10мин перерыв - 0:14 (~1ч20м) > OK (после отлова ошибки) (fast 40%, memory 5%)
private fun mergeTwoLists21(list1: ListNode?, list2: ListNode?): ListNode? {
    //println("----- Start")
    if (list1 == null && list2 == null) return null
    else if (list1 == null) return list2
    else if (list2 == null) return list1

    var check = true to true

    val answer: ListNode?

    var current1 = list1
    var current2 = list2

    if (current1.`val` <= current2.`val`) {
        answer = ListNode(current1.`val`)
        if (current1.next == null) check = false to check.second
        current1 = current1.next
    }
    else {
        answer = ListNode(current2.`val`)
        if (current2.next == null) check = check.first to false
        current2 = current2.next
    }

    var nextListNode = answer

    /*println("answer: ${listNodeToList(answer)}")
    println("nextListNode: ${listNodeToList(nextListNode)}")
    println("check: $check")
    println ("current1: $current1, current2: $current2")
    if (current1 != null) {
        println ("current1: ${current1.`val`}")
    }
    if (current2 != null) {
        println ("current2: ${current2.`val`}")
    }

    println("---- one")*/

    while (check.first || check.second) {
        if (current1 != null && current2 != null && nextListNode != null) {
            if (current1.`val` <= current2.`val`) {
                nextListNode.next = ListNode(current1.`val`)
                nextListNode = nextListNode.next
                current1 = current1.next
            }
            else {
                nextListNode.next = ListNode(current2.`val`)
                nextListNode = nextListNode.next
                current2 = current2.next
            }
        }
        else if (current1 == null && current2 != null && nextListNode != null) {
            nextListNode.next = ListNode(current2.`val`)
            nextListNode = nextListNode.next
            current2 = current2.next
            check = false to check.second
        }
        else if (current2 == null && current1 != null && nextListNode != null) {
            nextListNode.next = ListNode(current1.`val`)
            nextListNode = nextListNode.next
            current1 = current1.next
            check = check.first to false
        }
        else {
            /*println ("current1: $current1, current2: $current2")
            println("== End")*/
            check = false to false

        }

        /*println("answer: ${listNodeToList(answer)}")
        println("nextListNode: ${listNodeToList(nextListNode)}")
        println("check: $check")
        println ("current1: $current1, current2: $current2")
        if (current1 != null) {
            println ("current1: ${current1.`val`}")
        }
        if (current2 != null) {
            println ("current2: ${current2.`val`}")
        }*/
    }
    return answer
}

//?
private fun mergeTwoLists21v2(list1: ListNode?, list2: ListNode?): ListNode? {
    if (list1 == null && list2 == null) return null
    else if (list1 == null) return list2
    else if (list2 == null) return list1

    val answer = ListNode(0)

    var current1 = list1
    var current2 = list2
    var nextListNode = answer

    while (current1 != null && current2 != null) {
        if (current1.`val` <= current2.`val`) {
            nextListNode.next = current1

            current1 = current1.next
        }
        else {
            nextListNode.next = current2
            current2 = current2.next
        }

        nextListNode = nextListNode.next!!
    }

    nextListNode.next = current1 ?: current2
    return answer.next
}



