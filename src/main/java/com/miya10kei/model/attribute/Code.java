package com.miya10kei.model.attribute;

import com.miya10kei.model.constant_pool.ConstantPool;
import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Code extends Attribute {
  private final int maxStack;
  private final int maxLocals;
  private final long codeLength;
  private final byte[] code;
  private final int exceptionTableLength;
  private final Exception[] exceptionTable;
  private final int attributesCount;
  private final Attribute[] attributes;

  public Code(
      int attributeNameIndex, long attributeLength, DataInput data, ConstantPool[] constantPools)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.maxStack = data.readUnsignedShort();
    this.maxLocals = data.readUnsignedShort();
    this.codeLength = data.readInt();
    this.code = new byte[(int) this.codeLength]; // todo long -> intはなおす
    for (int i = 0; i < this.codeLength; i++) {
      code[i] =  data.readByte();
    }
    this.exceptionTableLength = data.readUnsignedShort();
    this.exceptionTable = new Exception[this.exceptionTableLength];
    for (int i = 0; i < this.exceptionTableLength; i++) {
      this.exceptionTable[i] = new Exception(data);
    }
    this.attributesCount = data.readUnsignedShort();
    this.attributes = new Attribute[this.attributesCount];
    for (int i = 0; i < this.attributesCount; i++) {
      this.attributes[i] = AttributeFactory.getInstance(data, constantPools);
    }
  }

  @Value
  class Exception {
    private final int startPc;
    private final int endPc;
    private final int handlerPc;
    private final int catchType;

    public Exception(DataInput data) throws IOException {
      this.startPc = data.readUnsignedShort();
      this.endPc = data.readUnsignedShort();
      this.handlerPc = data.readUnsignedShort();
      this.catchType = data.readUnsignedShort();
    }
  }
}
