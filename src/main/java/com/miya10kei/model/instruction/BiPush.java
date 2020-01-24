package com.miya10kei.model.instruction;

import java.nio.ByteBuffer;
import java.util.Stack;

class BiPush {
  static void exec(final ByteBuffer data, final Stack<Object> stack) {
    stack.push(data.get());
  }
}
