package com.ellen.androidface.math

fun stringReversal(input: String): String {
    val length = input.length
    if (length <= 1) {
        return input
    }
    val array = input.toCharArray();
    return array.apply {
        input.forEachIndexed { index, c ->
            if (index >= length / 2) {
                return@forEachIndexed
            }
            val cR = array[length - 1 - index]
            array[index] = cR;
            array[length - 1 - index] = c;
        }
    }.joinToString("");
}

fun reversalNode(node: Node): Node {
    var pre: Node? = null
    var cur: Node? = node
    var next: Node? = null
    while (cur != null) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
    return pre!!;
}


class Node(val value: Int, var next: Node?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value
    }

}
