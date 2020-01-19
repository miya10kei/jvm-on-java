package com.miya10kei.model.constant_pool;

import com.miya10kei.type.U1;
import com.miya10kei.type.U4;
import java.io.IOException;
import java.io.InputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantLong extends ConstantPool {
  private final U4 highBytes;
  private final U4 lowBytes;

  public ConstantLong(final U1 tag, final InputStream data) throws IOException {
    super(tag);
    this.highBytes = new U4(data.readNBytes(4));
    this.lowBytes = new U4(data.readNBytes(4));
  }
}
