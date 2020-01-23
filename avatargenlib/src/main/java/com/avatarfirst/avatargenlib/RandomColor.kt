package com.avatarfirst.avatargenlib

import java.util.Arrays
import java.util.Collections
import java.util.Stack

/**
 * Created by Korir on 1/23/20.
 */

internal class RandomColors {
  private val recycle: Stack<Int>
  private val colors: Stack<Int>
  fun getColor(): Int {
    if (colors.size === 0) {
      while (!recycle.isEmpty()) colors.push(recycle.pop())
      Collections.shuffle(colors)
    }
    val c: Int = colors.pop()
    recycle.push(c)
    return c
  }

  init {
    colors = Stack()
    recycle = Stack()
    recycle.addAll(
      Arrays.asList(
        -0xbbcca, -0x16e19d, -0x63d850, -0x98c549,
        -0xc0ae4b, -0xde690d, -0xfc560c, -0xff432c,
        -0xff6978, -0xb350b0, -0x743cb6, -0x3223c7,
        -0x14c5, -0x3ef9, -0x6800, -0xa8de,
        -0x86aab8, -0x616162, -0x9f8275, -0xcccccd
      )
    )
  }
}