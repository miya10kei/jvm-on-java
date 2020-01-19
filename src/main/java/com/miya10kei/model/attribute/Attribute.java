package com.miya10kei.model.attribute;

import com.miya10kei.typs.U2;
import com.miya10kei.typs.U4;
import lombok.Value;
import lombok.experimental.NonFinal;

@NonFinal
@Value
public class Attribute {
  private final U2 attributeNameIndex;
  private final U4 attributeLength;
}
