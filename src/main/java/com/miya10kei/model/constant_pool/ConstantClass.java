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
public class ConstantClass extends ConstantPool {
  private final U2 nameIndex;

  public ConstantClass(final U1 tag, final InputStream data) throws IOException {
    super(tag);
    this.nameIndex = new U2(data.readNBytes(2));
  }
}
