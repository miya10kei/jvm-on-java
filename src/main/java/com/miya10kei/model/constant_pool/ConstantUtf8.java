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
public class ConstantUtf8 extends ConstantPool {
  private final U2 length;
  private final U1[] bytes;

  public String getStringOfBytes() {
    var bytes = new byte[this.bytes.length];
    for (int i = 0; i < this.bytes.length; i++) {
      bytes[i] = this.bytes[i].getRaw();
    }
    return new String(bytes);
  }

  public ConstantUtf8(final U1 tag, final InputStream data) throws IOException {
    super(tag);
    this.length = new U2(data.readNBytes(2));
    this.bytes = new U1[this.length.getUnsignedInt()];
    for (int i = 0; i < this.length.getUnsignedInt(); i++) {
      bytes[i] = new U1(data.readNBytes(1));
    }
  }
}
