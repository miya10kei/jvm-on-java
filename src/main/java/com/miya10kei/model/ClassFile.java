package com.miya10kei.model;

import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantPoolFactory;
import java.io.DataInput;
import java.io.IOException;
import lombok.Value;

@Value
public class ClassFile {

    // u1: 8bit -> short
    // u2: 16bit -> int
    // u4: 32it -> long
    private final long magic;
    private final int minorVersion;
    private final int majorVersion;
    private final int constantPoolCount;
    private final ConstantPool[] constantPools;
//    private final int accessFlags;
//    private final int thisClass;
//    private final int superClass;
//    private final int interfacesCount;
//    private final int[] interfaces;
//    //
//    private final int methodsCount;
//    //
//    private final int attributesCount;

    public ClassFile(DataInput raw) throws IOException {
        this.magic = Integer.toUnsignedLong(raw.readInt());
        this.minorVersion = raw.readUnsignedShort();
        this.majorVersion = raw.readUnsignedShort();
        this.constantPoolCount = raw.readUnsignedShort();
        final int maxIndex = this.constantPoolCount - 1;
        this.constantPools = new ConstantPool[maxIndex];
        for (int i = 0; i < maxIndex; i++) {
            this.constantPools[i] = ConstantPoolFactory.getInstance(raw);
        }
    }
}
