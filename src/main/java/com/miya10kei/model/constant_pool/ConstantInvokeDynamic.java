package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantInvokeDynamic extends ConstantPool {

  private int bootstrapMethodAttrIndex;
  private int nameAndTypeIndex;

  public ConstantInvokeDynamic(short tag, DataInput raw) throws IOException {
    super(tag);
    this.bootstrapMethodAttrIndex = raw.readUnsignedShort();
    this.nameAndTypeIndex = raw.readUnsignedShort();
  }
}