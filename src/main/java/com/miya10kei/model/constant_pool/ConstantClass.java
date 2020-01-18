package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantClass extends ConstantPool {

  private final int nameIndex;

  public ConstantClass(short tag, DataInput data) throws IOException {
    super(tag);
    this.nameIndex = data.readShort();
  }
}
