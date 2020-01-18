package com.miya10kei.model;

import com.miya10kei.model.attribute.Attribute;
import com.miya10kei.model.attribute.AttributeFactory;
import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantUtf8;
import java.io.DataInput;
import java.io.IOException;
import lombok.Value;

@Value
public class Method {
  private final int accessFlags;
  private final int nameIndex;
  private final int descriptorIndex;
  private final int attributesCount;
  private final Attribute[] attributes;

  private final String name;

  public Method(DataInput data, ConstantPool[] constantPools) throws IOException {
    this.accessFlags = data.readUnsignedShort();
    this.nameIndex = data.readUnsignedShort();
    this.descriptorIndex = data.readUnsignedShort();
    this.attributesCount = data.readUnsignedShort();
    this.attributes = new Attribute[this.attributesCount];
    for (int i = 0; i < this.attributesCount; i++) {
      this.attributes[i] = AttributeFactory.getInstance(data, constantPools);
    }

    var cp = constantPools[nameIndex - 1];
    if (!(cp instanceof ConstantUtf8)) {
      throw new RuntimeException(
          "Invalid constant pool structure: " + cp.getClass().getSimpleName());
    }
    this.name = ((ConstantUtf8) cp).getStringOfBytes();
  }
}
