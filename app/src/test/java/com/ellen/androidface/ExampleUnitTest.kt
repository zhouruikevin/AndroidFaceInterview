package com.ellen.androidface

import org.junit.Assert
import org.junit.Test
import java.lang.StringBuilder
import com.ellen.androidface.math.Node
import com.ellen.androidface.math.reversalNode
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

}

