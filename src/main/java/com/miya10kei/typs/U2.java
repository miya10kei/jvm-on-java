package com.miya10kei.typs;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class U2 {
  @Getter(AccessLevel.NONE)
  private final byte[] data;

  public U2(final byte[] data) {
    if (data == null || data.length != 2) {
      throw new RuntimeException("Invalid data" + Arrays.toString(data));
    }
    this.data = data;
  }

  public byte[] getRaw() {
    return this.data;
  }

  public int getUnsignedInt() {
    return (data[0] << 8) + (data[1]);
  }

  public long getUnsignedLong() {
    return Integer.toUnsignedLong(getUnsignedInt());
  }

  private String getHexString() {
    return Integer.toHexString(getUnsignedInt());
  }
}
