package com.ellen.androidface

import com.ellen.androidface.math.*
import org.junit.Assert
import java.lang.StringBuilder
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    fun f1_isCorrect() {
        Assert.assertEquals("123", stringReversal("321"))
    }

    @Test
    fun f2_isCorrect() {
        var node: Node? = linkListReversal(Node(1, Node(2, Node(3))))
        val sb = StringBuilder()
        while (node != null) {
            sb.append(node.key)
            node = node.next
        }
        Assert.assertEquals("321", sb.toString())
    }

    @Test
    fun f3_isCorrect() {
        Assert.assertEquals(find1stCharInInputs("bcbcaddd"), 'a')
    }

    @Test
    fun f4_quickSort() {
        Assert.assertArrayEquals(quicklySort(arrayOf(3, 4, 2, 2, 1, 5)), arrayOf(1, 2, 2, 3, 4, 5))
}
    @Test
    fun f_tree() {
        val node = Tree(10, Tree(5, Tree(1)), Tree(2))
        moThree(node)
    }

}

