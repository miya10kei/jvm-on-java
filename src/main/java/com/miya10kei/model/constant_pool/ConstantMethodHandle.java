package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantMethodHandle extends ConstantPool {

  private final short referenceKind;

  private final int referenceIndex;

  public ConstantMethodHandle(short tag, DataInput raw) throws IOException {
    super(tag);
    this.referenceKind = (short) raw.readUnsignedByte();
    this.referenceIndex = raw.readUnsignedShort();
  }
}
