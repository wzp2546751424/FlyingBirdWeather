package com.zhipeng.flyingbirdweather.util

const val province = "省"
const val city = "市"
val districtList = listOf("区","县", "镇")

fun getReginAndQuery(address: String): Pair<String, String> {
    var result = -1 to -1
    result = getTargetStringRange(result, province, address)
    result = getTargetStringRange(result, city, address)
    val districtIndex = districtList.indexOfFirst { address.contains(it) }
    if (districtIndex > 0) {
        result = getTargetStringRange(result, districtList[districtIndex], address)
    }
    return if (result.first < result.second) {
        val region = address.substring(result.first, result.second + 1)
        var query = address.substring(result.second + 1, address.length)
        if (query.isEmpty()) {
            query = region
        }
        region to query
    } else {
        address to address
    }
}

fun getTargetStringRange(lastRange: Pair<Int, Int>, indexString: String, totalString: String): Pair<Int, Int> {
    val index = totalString.indexOf(indexString, lastRange.second + 1)
    return if (index > 0) {
        lastRange.second + 1 to index
    } else {
        lastRange
    }
}

fun main() {
   /* val test = "wang jia zhuang"
    val result = getReginAndQuery(test)
    println(result)*/
}