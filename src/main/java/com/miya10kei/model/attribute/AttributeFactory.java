package com.miya10kei.model.attribute;

import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantUtf8;
import com.miya10kei.typs.U2;
import com.miya10kei.typs.U4;
import java.io.IOException;
import java.io.InputStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AttributeFactory {
  public static Attribute getInstance(final InputStream data, final ConstantPool[] constantPools)
      throws IOException {
    var attributeNameIndex = new U2(data.readNBytes(2));
    var attributeLength = new U4(data.readNBytes(4));
    var cp = (ConstantUtf8) constantPools[attributeNameIndex.getUnsignedInt() - 1];
    var name = cp.getStringOfBytes();
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
