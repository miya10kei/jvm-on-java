package com.miya10kei.model.constant_pool;

import com.miya10kei.typs.U1;
import com.miya10kei.typs.U4;
import java.io.IOException;
import java.io.InputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantInteger extends ConstantPool {
  private final U4 bytes;

  public ConstantInteger(final U1 tag, final InputStream data) throws IOException {
    super(tag);
    this.bytes = new U4(data.readNBytes(4));
  }
}
