package leetcode

fun main(args: Array<String>) {
    println(hasCycle141v2(exampleListNode141case1()))
    println(hasCycle141v2(exampleListNode141case3()))
}

//141. Linked List Cycle - Easy - https://leetcode.com/problems/linked-list-cycle/description/

private fun exampleListNode141case1(): ListNode {
    val a1 = ListNode(3)

    val a2 = ListNode(2)
    val a3 = ListNode(0)
    val a4 = ListNode(-4)

    a1.next = a2
    a2.next = a3
    a3.next = a4
    a4.next = a2

    return a1
}
private fun exampleListNode141case3(): ListNode {
    val a1 = ListNode(1)
    return a1
}

//141 > 8:10 - 8:50 (~40мин) > OK (fast 37%, memory 57%)
private fun hasCycle141(head: ListNode?): Boolean {
    var step = head
    var stepFast = head

    while (stepFast != null) {

        stepFast = stepFast.next

        if (stepFast != null) {
            stepFast = stepFast.next
        }

        if (step != null) {
            step = step.next
        }
        if (stepFast == step && stepFast != null) return true
    }
    return false
}

//the right decision, but not mine
private fun hasCycle141v2(head: ListNode?): Boolean {
    var step = head
    var stepFast = head

    while (stepFast != null && stepFast.next?.next != null) {
        stepFast = stepFast.next?.next
        step = step?.next
        if (stepFast == step) return true
    }
    return false
}