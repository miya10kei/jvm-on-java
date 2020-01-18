package com.miya10kei.model.attribute;

import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantUtf8;
import java.io.DataInput;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttributeFactory {
  public static Attribute getInstance(DataInput data, ConstantPool[] constantPools)
      throws IOException {
    var attributeNameIndex = data.readUnsignedShort();
    var attributeLength = data.readInt();
    var cp = constantPools[attributeNameIndex - 1];
    if (!(cp instanceof ConstantUtf8)) {
      throw new RuntimeException(
          "Invalid constant pool structure: " + cp.getClass().getSimpleName());
    }
    var name = ((ConstantUtf8) cp).getStringOfBytes();
    // Todo implements remaining attributes
    switch (name) {
      case "Code":
        return new Code(attributeNameIndex, attributeLength, data, constantPools);
      case "LineNumberTable":
        return new LineNumberTable(attributeNameIndex, attributeLength, data);
      case "LocalVariableTable":
        return new LocalVariableTable(attributeNameIndex, attributeLength, data);
      case "SourceFile":
        return new SourceFile(attributeNameIndex, attributeLength, data);
      default:
        throw new RuntimeException("Invalid Attribute: " + name);
    }
  }
}
