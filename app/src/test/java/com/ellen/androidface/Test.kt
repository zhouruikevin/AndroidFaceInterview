package com.ellen.androidface


data class KParams(var test: Int = 1)

fun test() {
    var keyValue = mutableMapOf<Int, String>(1 to "one", 2 to "two");
    keyValue[3] = "three"
    var keyValue2 = mapOf(1 to "one", 2 to "two");
    val testValue = keyValue2[2];
}