package com.miya10kei.model.instruction;

import com.miya10kei.model.attribute.Code;
import com.miya10kei.model.constant_pool.ConstantPool;
import java.util.Stack;

public class InstructionExecutor {
  public static void exec(final Code codeAttr, final ConstantPool[] constantPools)
      throws ReflectiveOperationException {
    var stack = new Stack<>();
    var code = codeAttr.getByteBufferOfCode();
    while (code.hasRemaining()) {
      var op = Byte.toUnsignedInt(code.get());
      switch (op) {
        case 0x2: // iconst_m1
          Iconst.exec(-1, stack);
          break;
        case 0x3: // iconst_0
          Iconst.exec(0, stack);
          break;
        case 0x4: // iconst_1
          Iconst.exec(1, stack);
          break;
        case 0x5: // iconst_2
          Iconst.exec(2, stack);
          break;
        case 0x6: // iconst_3
          Iconst.exec(3, stack);
          break;
        case 0x7: // iconst_4
          Iconst.exec(4, stack);
          break;
        case 0x8: // iconst_5
          Iconst.exec(5, stack);
          break;
        case 0x10: // bipush
          BiPush.exec(code, stack);
          break;
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
