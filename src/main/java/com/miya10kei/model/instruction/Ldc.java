package com.miya10kei.model.instruction;

import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantString;
import com.miya10kei.model.constant_pool.ConstantUtf8;
import java.nio.ByteBuffer;
import java.util.Stack;

public class Ldc {

  public static void exec(ByteBuffer data, ConstantPool[] constantPools, Stack<Object> stack) {
    var index = Byte.toUnsignedInt(data.get());
    var cp = (ConstantString) constantPools[index - 1];
    stack.push((((ConstantUtf8) constantPools[cp.getStringIndex() - 1]).getStringOfBytes()));
  }
}
