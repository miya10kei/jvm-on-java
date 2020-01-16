package com.miya10kei.model.constant_pool;

import java.io.DataInput;
import java.io.IOException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantPoolFactory {

    public static ConstantPool getInstance(DataInput raw) throws IOException {
        var tag = (short) raw.readUnsignedByte();
        switch (tag) {
            case 1:
                return new ConstantUtf8(tag, raw);
            case 3:
                return new ConstantInteger(tag, raw);
            case 4:
                return new ConstantFloat(tag, raw);
            case 5:
                return new ConstantLong(tag, raw);
            case 6:
                return new ConstantDouble(tag, raw);
            case 7:
                return new ConstantClass(tag, raw);
            case 8:
                return new ConstantString(tag, raw);
            case 9:
                return new ConstantFieldRef(tag, raw);
            case 10:
            case 11:
            case 12:
            case 15:
            case 16:
            case 18:
            default:// todo エラーにする
                return null;
        }
    }
}
