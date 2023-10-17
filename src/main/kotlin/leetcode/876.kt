package leetcode

fun main(args: Array<String>) {
    println(middleNode876v2(exampleListNode876case1())?.`val`)
    println(middleNode876v2(exampleListNode876case2())?.`val`)
    println(middleNode876v2(exampleListNode876case3())?.`val`)
}

//876. Middle of the Linked List - Easy - https://leetcode.com/problems/middle-of-the-linked-list/description/

private fun exampleListNode876case1(): ListNode {
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
private fun exampleListNode876case2(): ListNode {
    val a1 = ListNode(1)
    val a2 = ListNode(2)
    val a3 = ListNode(3)
    val a4 = ListNode(4)
    val a5 = ListNode(5)
    val a6 = ListNode(6)

    a1.next = a2
    a2.next = a3
    a3.next = a4
    a4.next = a5
    a5.next = a6

    return a1
}
private fun exampleListNode876case3(): ListNode {
    val a1 = ListNode(1)
    val a2 = ListNode(2)

    a1.next = a2

    return a1
}

//876 > 8:26 - 8:43 (~17мин) но есть баг - 8:56 (+ ~13мин) > OK (fast 29%, memory 24%)
private fun middleNode876(head: ListNode?): ListNode? {
    var next = head?.next
    val list = mutableListOf<ListNode?>()
    list.add(head)
    var count = 1

    while (next != null) {
        val temp = next
        next = temp.next
        count++
        list.add(temp)
    }
    return list[count / 2]
}

//876v2 > 8:56 - 9:06 (~10мин) > OK (fast 53%, memory 73%)
private fun middleNode876v2(head: ListNode?): ListNode? {
    var count = 1
    var current = 1
    var answer = head
    var next = head?.next

    while (next != null) {
        count++
        next = next.next
        if ((count / 2) + 1 > current) {
            current++
            answer = answer?.next
        }
    }
    return answer
}
