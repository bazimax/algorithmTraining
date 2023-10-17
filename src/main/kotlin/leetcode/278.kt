package leetcode

fun main(args: Array<String>) {
    println(firstBadVersion278(2126753390))
}

//278. First Bad Version - Easy - https://leetcode.com/problems/first-bad-version/description/

//278 > 9:40 - 10:17 (~37мин) > OK (fast 79%, memory 77%) некоторое время потерял на Long
private fun firstBadVersion278(n: Int) : Int {
    var l = 1
    var r = n

    while (l < r) {
        val mid: Long = (l.toLong() + r) / 2
        val answer = isBadVersion(mid.toInt())
        if (answer) r = mid.toInt() else l = mid.toInt() + 1
    }
    return l
}

private fun isBadVersion(version: Int) : Boolean {
    return version >= 1702766719
}