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
public class LocalVariableTable extends Attribute {
  private final U2 localVariableTableLength;
  private final LocalVariable[] localVariableTable;

  public LocalVariableTable(
      final U2 attributeNameIndex, final U4 attributeLength, final InputStream data)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.localVariableTableLength = new U2(data.readNBytes(2));
    this.localVariableTable = new LocalVariable[this.localVariableTableLength.getUnsignedInt()];
    for (int i = 0; i < this.localVariableTableLength.getUnsignedInt(); i++) {
      this.localVariableTable[i] = new LocalVariable(data);
    }
  }

  @Value
  class LocalVariable {
    private final U2 startPc;
    private final U2 length;
    private final U2 nameIndex;
    private final U2 descriptorIndex;
    private final U2 index;

    public LocalVariable(final InputStream data) throws IOException {
      this.startPc = new U2(data.readNBytes(2));
      this.length = new U2(data.readNBytes(2));
      this.nameIndex = new U2(data.readNBytes(2));
      this.descriptorIndex = new U2(data.readNBytes(2));
      this.index = new U2(data.readNBytes(2));
    }
  }
}
