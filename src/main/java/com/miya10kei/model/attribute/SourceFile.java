package com.miya10kei.model.attribute;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SourceFile extends Attribute {
  private final int sourceFileIndex;

  public SourceFile(int attributeNameIndex, long attributeLength, DataInput data)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.sourceFileIndex = data.readUnsignedShort();
  }
}
