package com.miya10kei.model.attribute;

import lombok.Value;
import lombok.experimental.NonFinal;

@NonFinal
@Value
public class Attribute {
  private final int attributeNameIndex;
  private final long attributeLength;
}
