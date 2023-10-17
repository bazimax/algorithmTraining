package leetcode

import java.util.*

fun main(args: Array<String>) {
    reverseList206v2(exampleListNode206case1())
    reverseList206v2(exampleListNode206case2())
    println(reverseList206v2(exampleListNode206case3()))
}

//206. Reverse Linked List - Easy - https://leetcode.com/problems/reverse-linked-list/description/

private fun exampleListNode206case1(): ListNode {
    val a1 = ListNode(1)
    val a2 = ListNode(2)
    val a3 = ListNode(3)
    val a4 = ListNode(4)
    val a5 = ListNode(5)

    a1.next = a2
    a2.next = a3
    a3.next = a4
    a4.next = a5

    return a1
}
private fun exampleListNode206case2(): ListNode {
    val a1 = ListNode(1)
    val a2 = ListNode(2)

    a1.next = a2

    return a1
}
private fun exampleListNode206case3(): ListNode? {
    return null
}

//206 > 16:10 - 16:30 (~20мин) (без IDE. Не работает) - 16:43 (поправил) (~33мин) > OK (fast 44%, memory 27%)
private fun reverseList206(head: ListNode?): ListNode? {
    if (head == null) return null
    if (head.next == null) return head
    var prev = head
    var current = head.next
    var next = head.next?.next
    head.next = null
    //println("prev: ${prev.`val`}, current: ${current?.`val`}, ${current?.next?.`val`}, next: ${next?.`val`}")

    while (next != null) {
        //next = current?.next
        current!!.next = prev
        prev = current
        current = next
        next = current.next
        //println(" prev: ${prev.`val`}, current: ${current.`val`}, ${current.next?.`val`}, next: ${next?.`val`}")
    }
    current?.next = prev
    //println("${current?.`val`}, ${current?.next?.`val`}")
    return current
}

//206v2 > 16:44 - 16:56 (~12мин) > OK (fast 31%, memory 27%)
private fun reverseList206v2(head: ListNode?): ListNode? {
    if (head == null) return null
    if (head.next == null) return head

    var node = head

    val stack = Stack<ListNode>()

    //stack.add(head)
    while (node != null) {
        stack.push(node)
        node = node.next
    }
    val newHead = stack.peek()

    while (stack.isNotEmpty()) {
        node = stack.pop()
        if (stack.isNotEmpty()) node.next = stack.peek() else node.next = null
    }

    //println("${newHead?.`val`}, ${newHead?.next?.`val`}")
    return newHead
}
