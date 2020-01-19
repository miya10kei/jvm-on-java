package com.miya10kei.model;

import com.miya10kei.model.attribute.Attribute;
import com.miya10kei.model.attribute.AttributeFactory;
import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantPoolFactory;
import com.miya10kei.typs.U2;
import com.miya10kei.typs.U4;
import java.io.IOException;
import java.io.InputStream;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class ClassFile {
  private final U4 magic;
  private final U2 minorVersion;
  private final U2 majorVersion;
  private final U2 constantPoolCount;
  private final ConstantPool[] constantPools;
  private final U2 accessFlags;
  private final U2 thisClass;
  private final U2 superClass;
  private final U2 interfacesCount;
  private final U2[] interfaces;
  private final U2 fieldsCount;
  private final Field[] fields;
  private final U2 methodsCount;
  private final Method[] methods;
  private final U2 attributesCount;
  private final Attribute[] attributes;

  public ClassFile(InputStream data) throws IOException {
    this.magic = new U4(data.readNBytes(4));
    this.minorVersion = new U2(data.readNBytes(2));
    this.majorVersion = new U2(data.readNBytes(2));
    this.constantPoolCount = new U2(data.readNBytes(2));
    final int maxIndex = this.constantPoolCount.getUnsignedInt() - 1;

    this.constantPools = new ConstantPool[maxIndex];
    for (int i = 0; i < maxIndex; i++) {
      this.constantPools[i] = ConstantPoolFactory.getInstance(data);
    }

    this.accessFlags = new U2(data.readNBytes(2));
    this.thisClass = new U2(data.readNBytes(2));
    this.superClass = new U2(data.readNBytes(2));

    this.interfacesCount = new U2(data.readNBytes(2));
    this.interfaces = new U2[this.interfacesCount.getUnsignedInt()];
    for (int i = 0; i < interfacesCount.getUnsignedInt(); i++) {
      this.interfaces[i] = new U2(data.readNBytes(2));
    }

    this.fieldsCount = new U2(data.readNBytes(2));
    this.fields = new Field[this.fieldsCount.getUnsignedInt()];
    // TODO implements Field

    this.methodsCount = new U2(data.readNBytes(2));
    this.methods = new Method[this.methodsCount.getUnsignedInt()];
    for (int i = 0; i < this.methodsCount.getUnsignedInt(); i++) {
      this.methods[i] = new Method(data, this.constantPools);
    }

    this.attributesCount = new U2(data.readNBytes(2));
    this.attributes = new Attribute[this.attributesCount.getUnsignedInt()];
    for (int i = 0; i < this.attributesCount.getUnsignedInt(); i++) {
      final Attribute attr = AttributeFactory.getInstance(data, this.constantPools);
      this.attributes[i] = attr;
    }
  }
}
