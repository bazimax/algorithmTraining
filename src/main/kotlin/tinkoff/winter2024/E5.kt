package tinkoff.winter2024

import java.util.*

fun main(args: Array<String>) {
    manulas()
}

//20:07 - 20:48 (40мин) > ?
private fun manulas(){
    val scan = Scanner(System.`in`)
    val (_, m, q) = scan.nextLine().split(" ").map { it.toInt() }

    val childList = mutableListOf<Child>()
    childList.add(Child(0))
    val kids = scan.nextLine().split(" ").map { it.toLong() }

    kids.forEach { childList.add(Child(it)) }

    // записываем друзей каждому ребенку
    repeat(m) {
        val (friend1, friend2) = scan.nextLine().split(" ").map { it.toInt() }
        childList[friend1].friends.add(friend2)
        childList[friend2].friends.add(friend1)
    }

    //обрабатываем события
    repeat(q) {
        val event = scan.nextLine().split(" ")
        val childName = event[1].toInt()

        if (event[0] == "?") {
            // event "?"
            println(childList[childName].manul)
        }
        else {
            // event "+"
            val x = event[2].toLong()

            childList[childName].friends.forEach { friend ->
                childList[friend].manul = childList[friend].manul + x
            }
        }
    }
}

class Child(var manul: Long = 0L, val friends: MutableList<Int> = mutableListOf())