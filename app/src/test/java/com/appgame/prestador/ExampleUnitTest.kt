package com.appgame.prestador

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        print(duplicateCount("dA" + "c".repeat(10) + "b".repeat(2) + "a".repeat(3)))
        assertEquals(4, 2 + 2)

    }
    @Test
    fun testFixed() {
        assertEquals("", toCamelCase(""))
        assertEquals("theStealthWarrior", toCamelCase("the stealth warrior"))
        assertEquals("IAmAProgrammer", toCamelCase("I-am-a-Programmer"))
        assertEquals("ABC", toCamelCase("A-B-C"))
    }

    @Test
    fun testNull(){
        var palabra: String? = null

        print(palabra?.length ?: 0)

    }

    fun duplicateCount(text: String): Int {
        // Write your code here
        var repeatTotal = 0

        var arraySorted = text.toUpperCase().toCharArray().sortedArray()
        var letterChecked= arrayListOf<Char>()
        print(arraySorted)
        for (i in arraySorted.indices) {

            if (!letterChecked.contains(arraySorted[i])){
                for ( j in  arraySorted.indices){
                    if (i != j){
                        if (arraySorted[i] == arraySorted[j]){
                            repeatTotal++
                            break
                        }
                    }
                }

                if (!letterChecked.contains(arraySorted[i])) letterChecked.add(arraySorted[i])
            }

        }



        return repeatTotal

    }

    fun toCamelCase(str:String):String{

        var newText = ""
        var count = 0
        if(str.isNotEmpty()){
            for (c in str){
                newText += if (count == 0){
                    c
                }else{
                    if (str[count -1] == '_' || str[count -1] == ' ' || str[count -1] == '_' ){
                        c.toUpperCase()
                    }else
                        c
                }
                count++
            }
        }


        return newText.replace(Regex("[_\\-\\s]"),"")
    }
}