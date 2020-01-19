package com.miya10kei.model;

import com.miya10kei.model.attribute.Attribute;
import com.miya10kei.model.attribute.AttributeFactory;
import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantUtf8;
import com.miya10kei.typs.U2;
import java.io.IOException;
import java.io.InputStream;
import lombok.Value;

@Value
public class Method {
  private final U2 accessFlags;
  private final U2 nameIndex;
  private final U2 descriptorIndex;
  private final U2 attributesCount;
  private final Attribute[] attributes;

  private final String name;

  public Method(final InputStream data, final ConstantPool[] constantPools) throws IOException {
    this.accessFlags = new U2(data.readNBytes(2));
    this.nameIndex = new U2(data.readNBytes(2));
    this.descriptorIndex = new U2(data.readNBytes(2));
    this.attributesCount = new U2(data.readNBytes(2));
    this.attributes = new Attribute[this.attributesCount.getUnsignedInt()];
    for (int i = 0; i < this.attributesCount.getUnsignedInt(); i++) {
      this.attributes[i] = AttributeFactory.getInstance(data, constantPools);
    }

    var cp = constantPools[this.nameIndex.getUnsignedInt() - 1];
    if (!(cp instanceof ConstantUtf8)) {
      throw new RuntimeException(
          "Invalid constant pool structure: " + cp.getClass().getSimpleName());
    }
    this.name = ((ConstantUtf8) cp).getStringOfBytes();
  }
}
