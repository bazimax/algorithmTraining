package yandex.cup2023android

//не решено
fun main(args: Array<String>) {

    val a = AA()
    a.incre()
    //a.value = 2
    println(a.value)

}

class AA {
    //@Volatile
    var value = 0
        private set

    fun incre() {
        value++
    }
}