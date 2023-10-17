package leetcode

fun main(args: Array<String>) {
    maxProfit(nums)
}

//121. Best Time to Buy and Sell Stock - Easy - https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
private val nums = intArrayOf(7,1,5,3,6,4)
//Вывод: 5

//121 > 17:40 - 18:00 (~20мин) > OK (fast 7%, memory 42%)
private fun maxProfit(prices: IntArray): Int {


    var min = prices[0]
    var r = 1
    var result = 0

    while (r <= prices.size - 1) {
        if (prices[r] <= min) min = prices[r]
        else {
            val temp = prices[r] - min
            if (temp > result) result = temp
        }
        r++
    }

    println(result)
    return result
}