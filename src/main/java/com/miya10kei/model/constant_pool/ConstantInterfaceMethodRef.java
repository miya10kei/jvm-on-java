package com.miya10kei.model.constant_pool;

import com.miya10kei.typs.U1;
import com.miya10kei.typs.U2;
import java.io.IOException;
import java.io.InputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantInterfaceMethodRef extends ConstantPool {
  private final U2 classIndex;
  private final U2 nameAndTypeIndex;

  public ConstantInterfaceMethodRef(final U1 tag, final InputStream data) throws IOException {
    super(tag);
    this.classIndex = new U2(data.readNBytes(2));
    this.nameAndTypeIndex = new U2(data.readNBytes(2));
  }
}
