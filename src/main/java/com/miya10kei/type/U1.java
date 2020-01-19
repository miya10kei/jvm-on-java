package com.miya10kei.type;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class U1 {
  @Getter(AccessLevel.NONE)
  private final byte data;

  public U1(final byte[] data) {
    if (data == null || data.length != 1) {
      throw new RuntimeException("Invalid data" + Arrays.toString(data));
    }
    this.data = data[0];
  }

  public byte getRaw() {
    return this.data;
  }

  public int getUnsignedInt() {
    return Byte.toUnsignedInt(data);
  }

  public long getUnsignedLong() {
    return Byte.toUnsignedLong(data);
  }

  private String getHexString() {
    return Integer.toHexString(getUnsignedInt());
  }
}
