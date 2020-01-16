package com.miya10kei.model.constant_pool;

import lombok.Value;
import lombok.experimental.NonFinal;

@NonFinal
@Value
public class ConstantPool {

    private final short tag;
}
