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

class InvokeVirtual {
  static void exec(
      final ByteBuffer data, final ConstantPool[] constantPools, final Stack<Object> stack)
      throws ReflectiveOperationException {
    var index = Short.toUnsignedInt(data.getShort());
    var cp = (ConstantMethodRef) constantPools[index - 1];

    // method name
    var nameAndType =
        (ConstantNameAndType) constantPools[cp.getNameAndTypeIndex().getUnsignedInt() - 1];
    var methodName =
        ((ConstantUtf8) constantPools[nameAndType.getNameIndex().getUnsignedInt() - 1])
            .getStringOfBytes();

    // argument
    var descriptor =
        ((ConstantUtf8) constantPools[nameAndType.getDescriptorIndex().getUnsignedInt() - 1])
            .getStringOfBytes();
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
  private static final Pattern PATTERN = Pattern.compile("\\(L?([/\\w]+);?\\)V");

  private static Class<?>[] parseArgumentType(String descriptor) throws ClassNotFoundException {
    var matcher = PATTERN.matcher(descriptor);
    var args = new ArrayList<Class<?>>();
    while (matcher.find()) {
      var typeName = matcher.group(1);
      args.add(resolveClassNameToFQCN(typeName));
    }

    var ret = new Class[args.size()];
    return args.toArray(ret);
  }

  private static Class<?> resolveClassNameToFQCN(String name) throws ClassNotFoundException {
    switch (name) {
      case "I":
        return Integer.TYPE;
      default:
        return Class.forName(NameConverter.toFQCN(name));
    }
  }
}
