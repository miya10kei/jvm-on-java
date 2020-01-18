package com.miya10kei.model.instruction;

import com.miya10kei.model.attribute.Code;
import com.miya10kei.model.constant_pool.ConstantPool;
import java.nio.ByteBuffer;
import java.util.Stack;

public class InstructionExecutor {
  public static void exec(Code codeAttr, ConstantPool[] constantPools)
      throws ReflectiveOperationException {
    var stack = new Stack<>();
    var code = ByteBuffer.wrap(codeAttr.getCode());
    while (code.hasRemaining()) {
      var op = Byte.toUnsignedInt(code.get());
      switch (op) {
        case 0xb2: // getstatic
          GetStatic.exec(code, constantPools, stack);
          break;
        case 0x12: // ldc
          Ldc.exec(code, constantPools, stack);
          break;
        case 0xb6: // invokevirtual
          InvokeVirtual.exec(code, constantPools, stack);
          break;
        case 0xb1: // return
          break;
        default:
          throw new RuntimeException("Invalid instruction: " + Integer.toHexString(op));
      }
    }
  }
}
