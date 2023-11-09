package yandex.cup2023android



//решено = {b = 0, a = 1, }, но этот ответ не верный ?!

var a = 0
var b = 0

val firstThread = Thread{
    a = 1
    secondThread.start()
    print("b = $b, ")
}

val secondThread = Thread{
    b = 1
    print("a = $a, ")
}

fun main() {

    print("{")
    firstThread.start()
    firstThread.join()
    secondThread.join()
    print("}")

}


