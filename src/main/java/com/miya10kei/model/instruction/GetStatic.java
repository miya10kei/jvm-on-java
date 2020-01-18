package com.miya10kei.model.instruction;

import com.miya10kei.model.constant_pool.ConstantClass;
import com.miya10kei.model.constant_pool.ConstantFieldRef;
import com.miya10kei.model.constant_pool.ConstantNameAndType;
import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantUtf8;
import com.miya10kei.util.NameConverter;
import java.nio.ByteBuffer;
import java.util.Stack;

public class GetStatic {
  public static void exec(ByteBuffer data, ConstantPool[] constantPools, Stack<Object> stack)
      throws ReflectiveOperationException {
    var index = Short.toUnsignedInt(data.getShort());
    var cp = (ConstantFieldRef) constantPools[index - 1];

    var classCp = (ConstantClass) constantPools[cp.getClassIndex() - 1];
    var className = ((ConstantUtf8) constantPools[classCp.getNameIndex() - 1]).getStringOfBytes();

    var nameAndTypeCp = (ConstantNameAndType) constantPools[cp.getNameAndTypeIndex() - 1];
    var fieldName =
        ((ConstantUtf8) constantPools[nameAndTypeCp.getNameIndex() - 1]).getStringOfBytes();

    stack.push(Class.forName(NameConverter.toFQCN(className)).getField(fieldName).get(null));
  }
}
