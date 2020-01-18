package com.miya10kei.model.instruction;

import com.miya10kei.model.constant_pool.ConstantMethodRef;
import com.miya10kei.model.constant_pool.ConstantNameAndType;
import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantUtf8;
import com.miya10kei.util.NameConverter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Stack;
import java.util.regex.Pattern;

public class InvokeVirtual {
  public static void exec(ByteBuffer data, ConstantPool[] constantPools, Stack<Object> stack)
      throws ReflectiveOperationException {
    var index = Short.toUnsignedInt(data.getShort());
    var cp = (ConstantMethodRef) constantPools[index - 1];

    // method name
    var nameAndType = (ConstantNameAndType) constantPools[cp.getNameAndTypeIndex() - 1];
    var methodName =
        ((ConstantUtf8) constantPools[nameAndType.getNameIndex() - 1]).getStringOfBytes();

    // argument
    var descriptor =
        ((ConstantUtf8) constantPools[nameAndType.getDescriptorIndex() - 1]).getStringOfBytes();
    var argTypes = parseArgumentType(descriptor);
    var argValues = new Object[argTypes.length];
    for (int i = 0; i < argTypes.length; i++) {
      argValues[i] = stack.pop();
    }

    // invoke
    var receiver = stack.pop();
    receiver.getClass().getMethod(methodName, argTypes).invoke(receiver, argValues);
  }

  // language = regex
  private static final Pattern PATTERN = Pattern.compile("\\(L([/\\w]+);\\)V");

  private static Class<?>[] parseArgumentType(String descriptor) throws ClassNotFoundException {
    var matcher = PATTERN.matcher(descriptor);
    var args = new ArrayList<Class<?>>();
    while (matcher.find()) {
      args.add(Class.forName(NameConverter.toFQCN(matcher.group(1))));
    }
    var ret = new Class[args.size()];
    return args.toArray(ret);
  }
}
