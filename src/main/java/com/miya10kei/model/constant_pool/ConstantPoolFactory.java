package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantPoolFactory {

  public static ConstantPool getInstance(DataInput raw) throws IOException {
    var tag = (short) raw.readUnsignedByte();
    switch (tag) {
      case 1:
        return new ConstantUtf8(tag, raw);
      case 3:
        return new ConstantInteger(tag, raw);
      case 4:
        return new ConstantFloat(tag, raw);
      case 5:
        return new ConstantLong(tag, raw);
      case 6:
        return new ConstantDouble(tag, raw);
      case 7:
        return new ConstantClass(tag, raw);
      case 8:
        return new ConstantString(tag, raw);
      case 9:
        return new ConstantFieldRef(tag, raw);
      case 10:
        return new ConstantMethodRef(tag, raw);
      case 11:
        return new ConstantInterfaceMethodRef(tag, raw);
      case 12:
        return new ConstantNameAndType(tag, raw);
      case 15:
        return new ConstantMethodHandle(tag, raw);
      case 16:
        return new ConstantMethodType(tag, raw);
      case 18:
        return new ConstantInvokeDynamic(tag, raw);
      default:
        throw new RuntimeException("Invalid tag: " + tag);
    }
  }
}
