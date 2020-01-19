package com.miya10kei.model.attribute;

import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.type.U1;
import com.miya10kei.type.U2;
import com.miya10kei.type.U4;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Code extends Attribute {
  private final U2 maxStack;
  private final U2 maxLocals;
  private final U4 codeLength;
  private final U1[] code;
  private final U2 exceptionTableLength;
  private final Exception[] exceptionTable;
  private final U2 attributesCount;
  private final Attribute[] attributes;

  public Code(
      final U2 attributeNameIndex,
      final U4 attributeLength,
      final InputStream data,
      final ConstantPool[] constantPools)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.maxStack = new U2(data.readNBytes(2));
    this.maxLocals = new U2(data.readNBytes(2));
    this.codeLength = new U4(data.readNBytes(4));
    this.code = new U1[(int) this.codeLength.getUnsignedLong()]; // TODO Should fix
    for (int i = 0; i < (int) this.codeLength.getUnsignedLong(); i++) {
      code[i] = new U1(data.readNBytes(1));
    }
    this.exceptionTableLength = new U2(data.readNBytes(2));
    this.exceptionTable = new Exception[this.exceptionTableLength.getUnsignedInt()];
    for (int i = 0; i < this.exceptionTableLength.getUnsignedInt(); i++) {
      this.exceptionTable[i] = new Exception(data);
    }
    this.attributesCount = new U2(data.readNBytes(2));
    this.attributes = new Attribute[this.attributesCount.getUnsignedInt()];
    for (int i = 0; i < this.attributesCount.getUnsignedInt(); i++) {
      this.attributes[i] = AttributeFactory.getInstance(data, constantPools);
    }
  }

  public ByteBuffer getByteBufferOfCode() {
    var bytes = new byte[this.code.length];
    for (int i = 0; i < this.code.length; i++) {
      bytes[i] = this.code[i].getRaw();
    }
    return ByteBuffer.wrap(bytes);
  }

  @Value
  class Exception {
    private final U2 startPc;
    private final U2 endPc;
    private final U2 handlerPc;
    private final U2 catchType;

    public Exception(final InputStream data) throws IOException {
      this.startPc = new U2(data.readNBytes(2));
      this.endPc = new U2(data.readNBytes(2));
      this.handlerPc = new U2(data.readNBytes(2));
      this.catchType = new U2(data.readNBytes(2));
    }
  }
}
