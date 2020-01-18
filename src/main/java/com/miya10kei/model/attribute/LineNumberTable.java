package com.miya10kei.model.attribute;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LineNumberTable extends Attribute {
  private final int lineNumberTableLength;
  private final LineNumber[] lineNumberTable;

  public LineNumberTable(int attributeNameIndex, long attributeLength, DataInput data)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.lineNumberTableLength = data.readUnsignedShort();
    this.lineNumberTable = new LineNumber[this.lineNumberTableLength];
    for (int i = 0; i < this.lineNumberTableLength; i++) {
      this.lineNumberTable[i] = new LineNumber(data);
    }
  }

  @Value
  class LineNumber {
    private final int startPc;
    private final int lineNumber;

    public LineNumber(DataInput data) throws IOException {
      this.startPc = data.readUnsignedShort();
      this.lineNumber = data.readUnsignedShort();
    }
  }
}
