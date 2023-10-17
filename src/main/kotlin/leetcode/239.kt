package leetcode

import java.util.ArrayDeque

fun main(args: Array<String>) {
    maxSlidingWindow(nums, k)
}

//IN WORK
//239. Sliding Window Maximum - Hard - https://leetcode.com/problems/sliding-window-maximum/description/
private val nums = intArrayOf(1,3,-1,-3,5,3,6,7)//(1,3,-1,-3,5,3,6,7)
private val k = 3
//Вывод: [3,3,5,5,6,7]
//

//239 - 4 часа > notOK
private fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    //239 - https://leetcode.com/problems/sliding-window-maximum/description/
    // 4 часа
    val result = arrayListOf<Int>()

    if (nums.size == 1) {
        //println("size = 1, ${nums[0]}, $nums")
        return nums
    }
    else if (k == 1) {
        println("k = 1, $nums")
        return nums
    }
    else {
        //массив видимых элементов (входящие в окно)
        val arrayWindow = Array(k) { 0 to 0 }//ArrayList<Pair<Int, Int>>()//mutableListOf<Pair<Int, Int>>()
        //println("${arrayWindow.size}, ${arrayWindow[0]}, ${arrayWindow[arrayWindow.size - 1]}")

        //в первый раз заполняем окно элементами из nums добавляя индексы (чтобы отсортированный массив знал положение
        //элемента в этом массиве)
        arrayWindow.forEachIndexed { index, pair ->
            arrayWindow[index] = nums[index] to index
        }


        //создаем отсортированный массив окна
        val arraySorted = arrayWindow.sortedWith(
            compareBy<Pair<Int?, Int>> { it.first == null }
                .thenBy{ it.first }
            //.thenBy{ it.second }
        ).toTypedArray()

        arrayWindow.forEach {
            println("window it: $it")
        }
        arraySorted.forEach {
            println("sort it: $it")
        }

        //в первый раз передаем в основной массив (окно) значения индексов из отсортированного
        //таким образом мы связываем оба массива
        arraySorted.forEachIndexed { index, pair ->
            //println("old window it: ${arrayWindow[pair.second]}")
            //println("sort pair: $pair, index: $index")
            arrayWindow[pair.second] = pair.first to index
            //println("new window it: ${arrayWindow[pair.second]}")
        }
        arrayWindow.forEach {
            println("window after sorted it: $it")
        }

        //первый вывод максимального значения видимого в окне
        result.add(arraySorted[k - 1].first)
        println("result: $result")

        var r = k - 1 + 1//arrayWindow.size - 1 + 1 //конец окна начинаем сразу с первого перемещения
        //var l = 0 // r - k //начало окна
        println("r: $r, nums.size: ${nums.size}")

        //двигаем окно, пока не выйдем за край массива nums
        // параллельно обновляем массивы и записываем результат
        while (r <= nums.size - 1) {
            //за край еще не вышли так что обновляем основной массив окна и отсортированный массив окна
            println("еще не вышли за пределы")
            println("r: $r = ${nums[r]}")

            //запоминаем элемент который удалим из отсортированного массива
            var deleteSorted = arrayWindow[0].second
            println("del: $deleteSorted, arrayWindow[l]: ${arrayWindow[0]}")

            //новый элемент из nums с последним индексом
            val newItem = nums[r] to k - 1
            println("new: $newItem")

            //обновляем основной массив окна и связанные элементы из отсортированного (включая удаленный)
            arrayWindow.forEachIndexed { index, pair ->
                if (index < arrayWindow.size - 1) {
                    //println("before W ${arrayWindow[index]}, ${arraySorted[arrayWindow[index].second]}")
                    arrayWindow[index] = arrayWindow[index + 1] //заменяем на следующий элемент по списку
                    //println("after W ${arrayWindow[index]}, ${arraySorted[arrayWindow[index].second]}")

                    //println("before S ${arraySorted[arrayWindow[index].second]}")
                    arraySorted[arrayWindow[index].second] = arraySorted[arrayWindow[index].second].first to index //обновляем index
                    //println("after S ${arraySorted[arrayWindow[index].second]}")
                }
                else if (index == arrayWindow.size - 1) {
                    //println("index == arrayWindow.size")
                    //println("before W - ${arrayWindow[index]}, ${arraySorted[arrayWindow[index].second]}")
                    arrayWindow[index] = nums[r] to index //добавляем новый элемент в основной массив//val newItem = nums[r] to k - 1
                    //arraySorted[deleteSorted] = newItem//добавляем новый элемент в отсортированный массив
                    //println("after W - ${arrayWindow[index]} , ${arraySorted[arrayWindow[index].second]}")
                }
                else println("ERRoR")
            }

            arraySorted[deleteSorted] = newItem
            //TEST >
            arrayWindow.forEach {
                println("while window it: $it")
            }

            arraySorted.forEach {
                println("while sort it: $it")
            }
            //TEST ^


            //если элемент ниже больше чем новый, то опускаем новый до тех пор пока не упремся
            if (deleteSorted != 0 && arraySorted[deleteSorted - 1].first > arraySorted[deleteSorted].first) {
                println("deleteSorted != 0: $deleteSorted")
                println("arraySorted[deleteSorted].first: ${arraySorted[deleteSorted].first}")
                println("arraySorted[deleteSorted - 1].first: ${arraySorted[deleteSorted - 1].first}")
                while (deleteSorted > 0 && arraySorted[deleteSorted].first <= arraySorted[deleteSorted - 1].first) {
                    println("deleteSorted >= 0")
                    if (arraySorted[deleteSorted].first <= arraySorted[deleteSorted - 1].first) {
                        println("true")
                        //если элемент меньше или равен то меняем местами
                        val tempItem = arraySorted[deleteSorted]
                        arraySorted[deleteSorted] = arraySorted[deleteSorted - 1]
                        arraySorted[deleteSorted - 1] = tempItem
                        deleteSorted--
                    }
                }
            }
            else { //иначе идем вверх пока не упремся
                println("else 2")
                println("arraySorted[deleteSorted].first: ${arraySorted[deleteSorted].first}")
                //println("arraySorted[deleteSorted - 1].first: ${arraySorted[deleteSorted + 1].first}")
                while (deleteSorted < arraySorted.size - 1 && arraySorted[deleteSorted].first >= arraySorted[deleteSorted + 1].first) {
                    println("deleteSorted < arraySorted.size - 1: $deleteSorted < ${arraySorted.size - 1}")
                    if (arraySorted[deleteSorted].first >= arraySorted[deleteSorted + 1].first) {
                        println("true 2")
                        //если элемент меньше или равен то меняем местами
                        val tempItem = arraySorted[deleteSorted]
                        arraySorted[deleteSorted] = arraySorted[deleteSorted + 1]
                        arraySorted[deleteSorted + 1] = tempItem
                        deleteSorted++
                    }
                }
            }
            //else {} //если

            arraySorted.forEachIndexed { index, pair ->
                arrayWindow[pair.second] = pair.first to index
            }

            //TEST >
            arrayWindow.forEach {
                println("while >> window it: $it")
            }

            arraySorted.forEach {
                println("while >> sort it: $it")
            }
            //TEST ^

            //добавляем вывод максимального значения видимого в окне
            result.add(arraySorted[k - 1].first)
            println("result: $result")

            r++
            //l++
        }

        /*tempArray.forEach {
            tempArray[it] = nums[it]
        }
        tempArray.sort()*/
    }
    println("result: $result")
    println("resultTo: ${result.toIntArray()}")
    return result.toIntArray()
}

//239 the right decision, but not mine
private fun maxSlidingWindowFast(nums: IntArray, k: Int): IntArray {
    val queue = ArrayDeque<Int>(k)
    println("queue: ${queue.toIntArray()}")

    val result = IntArray(nums.size - k + 1)
    result.forEach {
        println("it: $it")
    }
    var resultIndex = 0

    var index = 0
    while (index < nums.size) {
        val number = nums[index]
        println("while 1, number: $number")
        while (queue.isNotEmpty() && nums[queue.peekLast()] < number) {
            println("while 2, nums.peek: ${nums[queue.peekLast()]}")
            queue.removeLast()
            queue.forEach {
                println("queueWhile: $it")
            }
            println("while 2 end, queue: ${queue.size}")
        }
        queue.addLast(index)
        queue.forEach {
            println("queue.addLast: $it")
        }

        if (queue.peekFirst() < index-k+1) {
            queue.removeFirst()
            queue.forEach {
                println("queue.removeFirst: $it")
            }
        }

        if (index + 1 >= k) {
            result[resultIndex++] = nums[queue.peekFirst()]
            result.forEach {
                println("it IF: $it")
            }
        }
        index++
        println("index++: $index")
    }
    result.forEach {
        println("it: $it")
    }
    //println("resultTo: ${result}")
    return result
}