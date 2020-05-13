/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.ellen.androidface.math

import java.util.*

class Node(val key: Int, var next: Node? = null)

fun stringReversal(input: String): String {
    val chars = input.toCharArray()
    val length = input.length;
    if (length in 0..1) {
        return input;
    }
    for (i in 0 until length / 2) {
        var c = chars[i]
        chars[i] = chars[length - 1 - i]
        chars[length - 1 - i] = c
    }
    return chars.joinToString("")
}

fun linkListReversal(node: Node): Node {
    var pre: Node? = null
    var cur: Node? = node
    var next = cur?.next
    while (cur != null) {
        cur.next = pre
        pre = cur
        cur = next
        next = next?.next
    }
    return pre!!
}

fun find1stCharInInputs(input: String): Char? {
    val linkedHashMap = linkedMapOf<Char, Int>()
    input.forEach {
        if (linkedHashMap.containsKey(it)) {
            linkedHashMap.put(it, (linkedHashMap[it] as Int) + 1)
        } else {
            linkedHashMap.put(it, 1)
        }
    }
    var result: Char? = null
    linkedHashMap.forEach { key, value ->
        if (value == 1) {
            result = key
            return@forEach
        }
    }
    return result
}

fun quicklySorthelp(array: Array<Int>, start: Int = 0, end: Int = array.size - 1): Int {
    var low = start
    var high = end
    var midValue = array[high]
    while (low < high) {
        while (low < high && array[low] < midValue) {
            low++
        }
        while (low < high && array[high] >= midValue) {
            high--
        }
        if (low < high) {
            var tmp = array[low]
            array[low] = array[high]
            array[high] = tmp
        }

    }
    array[end] = array[low]
    array[low] = midValue
    return low
}

fun quicklySort(array: Array<Int>, start: Int = 0, end: Int = array.size - 1): Array<Int> {
    if (start >= end) {
        return array
    }
    var stack = Stack<Int>()
    stack.push(start)
    stack.push(end)
    while (!stack.isEmpty()) {
        var high = stack.pop()
        var low = stack.pop()
        var index = quicklySorthelp(array, low, high)
        if (low < index - 1) {
            stack.push(low)
            stack.push(index - 1)
        }
        if (index + 1 < high) {
            stack.push(index + 1)
            stack.push(high)
        }
    }
    return array;
}