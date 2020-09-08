/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.ellen.androidface

import java.util.*

class Node(val key: Int, var next: Node? = null)
class Tree(val key: Int, var left: Tree? = null, var right: Tree? = null)

// 字符串翻转
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

// 链表翻转
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

// 字符串第一次1次出现的字符
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

// 镜像二叉树
fun moThree(tree: Tree): Tree {
    tree.left?.let { moThree(it) }
    tree.right?.let { moThree(it) }
    if (tree.left == null && tree.right == null) {
        var tmp = tree.left
        tree.left = tree.right
        tree.right = tmp
    }
    return tree
}

// 集合的所有子集合
fun chilrenCollections(datas: Array<Int>): Array<Array<Int>> {
    val count = Math.pow(2.0, datas.size.toDouble()).toInt()
    val result = Array<Array<Int>>(size = count) { arrayOf() }
    for (i in 0 until count) {
        var index = i;
        var list = mutableListOf<Int>()
        for (j in 0 until datas.size) {
            if (index and 1 == 1) {
                list.add(j)
            }
            index = index / 2
        }
        result[i] = list.toIntArray().toTypedArray()
    }
    return result
}
// 链表大数相加