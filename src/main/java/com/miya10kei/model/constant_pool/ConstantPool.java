package com.miya10kei.model.constant_pool;

import com.miya10kei.type.U1;
import lombok.Value;
import lombok.experimental.NonFinal;

@NonFinal
@Value
public class ConstantPool {
  private final U1 tag;
}
