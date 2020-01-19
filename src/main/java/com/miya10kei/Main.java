package com.miya10kei;

import com.miya10kei.model.ClassFile;
import com.miya10kei.model.Method;
import com.miya10kei.model.attribute.Attribute;
import com.miya10kei.model.attribute.Code;
import com.miya10kei.model.instruction.InstructionExecutor;
import java.io.BufferedInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Please set Java class file path to 1st argument only.");
      System.exit(1);
    }
    try (var data = new BufferedInputStream(Files.newInputStream(Paths.get(args[0])))) {
      var classFile = new ClassFile(data);
      exec(classFile);
      System.exit(0);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
    }
  }

  // TODO must refactor
  private static void exec(ClassFile classFile) throws ReflectiveOperationException {
    var mainMethod = findMainMethod(classFile);
    var codeAttr = findCodeAttribute(mainMethod.getAttributes());
    InstructionExecutor.exec(codeAttr, classFile.getConstantPools());
  }

  private static Method findMainMethod(ClassFile classFile) {
    for (Method method : classFile.getMethods()) {
      if ("main".equals(method.getName())) {
        return method;
      }
    }
    throw new RuntimeException("Not found main method");
  }

  private static Code findCodeAttribute(Attribute[] attributes) {
    for (Attribute attr : attributes) {
      if (attr instanceof Code) {
        return (Code) attr;
      }
    }
    throw new RuntimeException("Not found Code attribute");
  }
}
