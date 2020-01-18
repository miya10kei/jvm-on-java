package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantUtf8 extends ConstantPool {

  private final int length;
  private final byte[] bytes;

  public String getStringOfBytes() {
    return new String(this.bytes);
  }

  public ConstantUtf8(short tag, DataInput raw) throws IOException {
    super(tag);
    this.length = raw.readUnsignedShort();
    this.bytes = new byte[this.length];
    for (int i = 0; i < this.length; i++) {
      bytes[i] = raw.readByte();
    }
  }
}
