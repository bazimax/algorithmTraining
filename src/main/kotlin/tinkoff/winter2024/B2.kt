package tinkoff.winter2024

import java.util.*

fun main(args: Array<String>) {
    employeeSocialization()
}

/*
input
5
1
1000000000
2
1 1
3
1 1 1
4
1 1 2 2
4
1 1 1 3

output
Yes
Yes
No
Yes
Yes
*/

//16:50 - 18:10 (1ч20мин) > ?
private fun employeeSocialization(){
    val scan = Scanner(System.`in`)
    val t = scan.nextLine().split(" ").map { it.toInt() }[0] // 1 ≤ t ≤ 1000

    repeat(t) {
        //val n = scan.nextLine().split(" ").map { it.toInt() }[0] // 1 ≤ n ≤ 10^5
        scan.nextLine()

        val employeeSocialization = scan.nextLine().split(" ").map { it.toInt() } // 1 ≤ aI ≤ 10^9

        var employeesMinus = 0
        var employeesPlus: Long = 0L

        employeeSocialization.forEachIndexed { index, aI ->
            if (aI == 1 && index != employeeSocialization.lastIndex) employeesMinus++ else employeesPlus += aI
        }

        if (employeesPlus >= employeesMinus) println("Yes") else println("No")
    }
}

