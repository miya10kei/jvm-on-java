package com.miya10kei.model.constant_pool;

import com.miya10kei.typs.U1;
import java.io.IOException;
import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantPoolFactory {

  public static ConstantPool getInstance(final InputStream data) throws IOException {
    var tag = new U1(data.readNBytes(1));
    switch (tag.getUnsignedInt()) {
      case 1:
        return new ConstantUtf8(tag, data);
      case 3:
        return new ConstantInteger(tag, data);
      case 4:
        return new ConstantFloat(tag, data);
      case 5:
        return new ConstantLong(tag, data);
      case 6:
        return new ConstantDouble(tag, data);
      case 7:
        return new ConstantClass(tag, data);
      case 8:
        return new ConstantString(tag, data);
      case 9:
        return new ConstantFieldRef(tag, data);
      case 10:
        return new ConstantMethodRef(tag, data);
      case 11:
        return new ConstantInterfaceMethodRef(tag, data);
      case 12:
        return new ConstantNameAndType(tag, data);
      case 15:
        return new ConstantMethodHandle(tag, data);
      case 16:
        return new ConstantMethodType(tag, data);
      case 18:
        return new ConstantInvokeDynamic(tag, data);
      default:
        throw new RuntimeException("Invalid tag: " + tag);
    }
  }
}
