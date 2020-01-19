package com.miya10kei.model.attribute;

import com.miya10kei.typs.U2;
import com.miya10kei.typs.U4;
import java.io.IOException;
import java.io.InputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LineNumberTable extends Attribute {
  private final U2 lineNumberTableLength;
  private final LineNumber[] lineNumberTable;

  public LineNumberTable(
      final U2 attributeNameIndex, final U4 attributeLength, final InputStream data)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.lineNumberTableLength = new U2(data.readNBytes(2));
    this.lineNumberTable = new LineNumber[this.lineNumberTableLength.getUnsignedInt()];
    for (int i = 0; i < this.lineNumberTableLength.getUnsignedInt(); i++) {
      this.lineNumberTable[i] = new LineNumber(data);
    }
  }

  @Value
  class LineNumber {
    private final U2 startPc;
    private final U2 lineNumber;

    public LineNumber(final InputStream data) throws IOException {
      this.startPc = new U2(data.readNBytes(2));
      this.lineNumber = new U2(data.readNBytes(2));
    }
  }
}
