package com.quxianggif.Extensions

/**
 * @author : created by archerLj
 * date: 2019/3/1 15
 * usage:
 */

val Any.TAG: String
    get() = this::class.java.toString().split(".").last()