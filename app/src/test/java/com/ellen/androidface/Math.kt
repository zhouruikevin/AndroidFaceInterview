/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.ellen.androidface

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