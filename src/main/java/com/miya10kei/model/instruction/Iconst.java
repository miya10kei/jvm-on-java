package com.miya10kei.model.instruction;

import java.util.Stack;

class Iconst {
  static void exec(final int i, final Stack<Object> stack) {
    stack.push(i);
  }
}
