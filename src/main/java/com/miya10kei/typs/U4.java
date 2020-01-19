package com.miya10kei.typs;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

@Value
public class U4 {
  @Getter(AccessLevel.NONE)
  private final byte[] data;

  public U4(final byte[] data) {
    if (data == null || data.length != 4) {
      throw new RuntimeException("Invalid data" + Arrays.toString(data));
    }
    this.data = data;
  }

  public byte[] getRaw() {
    return this.data;
  }

  public long getUnsignedLong() {
    return Integer.toUnsignedLong(((data[0] << 24) + (data[1] << 16) + (data[2] << 8) + (data[3])));
  }

  private String getHexString() {
    return Long.toHexString(getUnsignedLong());
  }
}
