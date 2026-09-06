package com.m4ykey.schu_kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform