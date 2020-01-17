package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantNameAndType extends ConstantPool {

  private final int nameIndex;
  private final int descriptorIndex;

  public ConstantNameAndType(short tag, DataInput raw) throws IOException {
    super(tag);
    this.nameIndex = raw.readUnsignedShort();
    this.descriptorIndex = raw.readUnsignedShort();
  }
}
