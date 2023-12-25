package tinkoff.winter2024

import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    signboard()
}

/*
input
6
A
TINKOF
FFINTOK
TINKOFF
TINKOFFF
AAAA

output
No
No
Yes
Yes
No
No
*/

//16:20 - 16:50 (30мин) > ?
private fun signboard(){
    val scan = Scanner(System.`in`)
    val n = scan.nextLine().split("  ").map { it.toInt() }[0]

    val map: HashMap<Char, Int> = HashMap()
    map['T'] = 1
    map['I'] = 1
    map['N'] = 1
    map['K'] = 1
    map['O'] = 1
    map['F'] = 2

    repeat(n) {
        val letters = scan.nextLine()
        val currentMap = map.toMutableMap()
        var check = true

        letters.forEach { letter ->
            if (currentMap.contains(letter) && currentMap[letter]!! > 0) {
                currentMap[letter] = currentMap[letter]!! - 1
            }
            else check = false

            if (currentMap.contains(letter) && currentMap[letter] == 0) currentMap.remove(letter)
        }
        if (check && currentMap.isEmpty()) println("Yes") else println("No")
    }
}
