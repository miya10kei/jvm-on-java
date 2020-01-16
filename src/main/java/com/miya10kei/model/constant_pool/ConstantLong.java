package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ConstantLong extends ConstantPool {

    private final long highBytes;
    private final long lowBytes;

    public ConstantLong(short tag, DataInput raw) throws IOException {
        super(tag);
        this.highBytes = Integer.toUnsignedLong(raw.readInt());
        this.lowBytes = Integer.toUnsignedLong(raw.readInt());
    }
}
