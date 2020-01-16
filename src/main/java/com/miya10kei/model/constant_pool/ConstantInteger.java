package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantInteger extends ConstantPool {

    private final long bytes;

    public ConstantInteger(short tag, DataInput raw) throws IOException {
        super(tag);
        this.bytes = Integer.toUnsignedLong(raw.readInt());
    }
}
