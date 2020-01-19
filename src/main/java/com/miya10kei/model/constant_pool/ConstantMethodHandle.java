package com.miya10kei.model.constant_pool;

import com.miya10kei.type.U1;
import com.miya10kei.type.U2;
import java.io.IOException;
import java.io.InputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantMethodHandle extends ConstantPool {
  private final U1 referenceKind;
  private final U2 referenceIndex;

  public ConstantMethodHandle(final U1 tag, final InputStream data) throws IOException {
    super(tag);
    this.referenceKind = new U1(data.readNBytes(1));
    this.referenceIndex = new U2(data.readNBytes(2));
  }
}
