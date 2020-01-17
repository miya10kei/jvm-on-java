package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.Value;

@Value
public class Field {
  private final int accessFlags;
  private final int nameIndex;
  private final int descriptorIndex;
  private final int attributesCount;
  //    private final Attribute[] attributes;

  public Field(DataInput raw) throws IOException {
    this.accessFlags = raw.readUnsignedShort();
    this.nameIndex = raw.readUnsignedShort();
    this.descriptorIndex = raw.readUnsignedShort();
    this.attributesCount = raw.readUnsignedShort();
    // todo
  }
}
