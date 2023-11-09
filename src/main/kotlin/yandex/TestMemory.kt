package yandex

import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream
import java.io.Serializable

//@TargetApi (26)
object ObjectSizeCalculator {
    private const val STRING_JAVA_TYPE_NAME = "java.lang.String"

    private fun getFirstObjectReference(o: Any): Any? {
        val objectType = o.javaClass.typeName

        if (objectType.substring(objectType.length - 2) == "[]") {
            return try {
                when (objectType) {
                    "java.lang.Object[]" -> (o as Array<*>)[0]
                    "int[]" -> (o as IntArray)[0]
                    else -> throw RuntimeException("Not Implemented !")
                }
            } catch (e: IndexOutOfBoundsException) {
                null
            }

        }

        return o
    }

    fun getObjectSizeInBytes(o: Any?): Int {
        //val STRING_JAVA_TYPE_NAME = "java.lang.String"

        if (o == null)
            return 0

        val objectType = o.javaClass.typeName
        val isArray = objectType.substring(objectType.length - 2) == "[]"

        val objRef = getFirstObjectReference(o)
        if (objRef != null && objRef !is Serializable)
            throw RuntimeException("Object must be serializable for measuring it's memory footprint using this method !")

        try {
            val baos = ByteArrayOutputStream()
            val oos = ObjectOutputStream(baos)
            oos.writeObject(o)
            oos.close()
            val bytes = baos.toByteArray()
            var i = bytes.size - 1
            var j = 0
            while (i != 0) {
                if (objectType !== STRING_JAVA_TYPE_NAME) {
                    if (bytes[i].toInt() == 112)
                        return if (isArray)
                            j - 4
                        else
                            j
                } else {
                    if (bytes[i].toInt() == 0)
                        return j - 1
                }
                i--
                j++
            }
        } catch (e: Exception) {
            return -1
        }
        return -1
    }
}

/*object ObjectSizeCalculatorBackup {
    const val STRING_JAVA_TYPE_NAME = "java.lang.String"

    private fun getFirstObjectReference(o: Any): Any? {
        val objectType = o.javaClass.typeName

        if (objectType.substring(objectType.length - 2) == "[]") {
            try {
                return if (objectType == "java.lang.Object[]")
                    (o as Array<Any>)[0]
                else if (objectType == "int[]")
                    (o as IntArray)[0]
                else
                    throw RuntimeException("Not Implemented !")
            } catch (e: IndexOutOfBoundsException) {
                return null
            }

        }

        return o
    }

    fun getObjectSizeInBytes(o: Any?): Int {
        //val STRING_JAVA_TYPE_NAME = "java.lang.String"

        if (o == null)
            return 0

        val objectType = o.javaClass.typeName
        val isArray = objectType.substring(objectType.length - 2) == "[]"

        val objRef = getFirstObjectReference(o)
        if (objRef != null && objRef !is Serializable)
            throw RuntimeException("Object must be serializable for measuring it's memory footprint using this method !")

        try {
            val baos = ByteArrayOutputStream()
            val oos = ObjectOutputStream(baos)
            oos.writeObject(o)
            oos.close()
            val bytes = baos.toByteArray()
            var i = bytes.size - 1
            var j = 0
            while (i != 0) {
                if (objectType !== STRING_JAVA_TYPE_NAME) {
                    if (bytes[i].toInt() == 112)
                        return if (isArray)
                            j - 4
                        else
                            j
                } else {
                    if (bytes[i].toInt() == 0)
                        return j - 1
                }
                i--
                j++
            }
        } catch (e: Exception) {
            return -1
        }
        return -1
    }
}*/
