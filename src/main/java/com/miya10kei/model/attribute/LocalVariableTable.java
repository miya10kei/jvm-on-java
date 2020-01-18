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
public class LocalVariableTable extends Attribute {
  private final int localVariableTableLength;
  private final LocalVariable[] localVariableTable;

  public LocalVariableTable(int attributeNameIndex, long attributeLength, DataInput data)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.localVariableTableLength = data.readUnsignedShort();
    this.localVariableTable = new LocalVariable[this.localVariableTableLength];
    for (int i = 0; i < this.localVariableTableLength; i++) {
      this.localVariableTable[i] = new LocalVariable(data);
    }
  }

  @Value
  class LocalVariable {
    private final int startPc;
    private final int length;
    private final int nameIndex;
    private final int descriptorIndex;
    private final int index;

    public LocalVariable(DataInput data) throws IOException {
      this.startPc = data.readUnsignedShort();
      this.length = data.readUnsignedShort();
      this.nameIndex = data.readUnsignedShort();
      this.descriptorIndex = data.readUnsignedShort();
      this.index = data.readUnsignedShort();
    }
  }
}
