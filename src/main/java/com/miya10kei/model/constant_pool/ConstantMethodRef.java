package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantMethodRef extends ConstantPool {

  private final int classIndex;
  private final int nameAndTypeIndex;

  public ConstantMethodRef(short tag, DataInput raw) throws IOException {
    super(tag);
    this.classIndex = raw.readUnsignedShort();
    this.nameAndTypeIndex = raw.readUnsignedShort();
  }
}