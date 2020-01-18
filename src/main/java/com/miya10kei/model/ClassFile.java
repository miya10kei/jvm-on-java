package com.miya10kei.model;

import com.miya10kei.model.attribute.Attribute;
import com.miya10kei.model.attribute.AttributeFactory;
import com.miya10kei.model.constant_pool.ConstantPool;
import com.miya10kei.model.constant_pool.ConstantPoolFactory;
import java.io.DataInput;
import java.io.IOException;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class ClassFile {

  private final long magic;
  private final int minorVersion;
  private final int majorVersion;
  private final int constantPoolCount;
  private final ConstantPool[] constantPools;
  private final int accessFlags;
  private final int thisClass;
  private final int superClass;
  private final int interfacesCount;
  private final int[] interfaces;
  private final int fieldsCount;
  private final Field[] fields;
  private final int methodsCount;
  private final Method[] methods;
  private final int attributesCount;
  private final Attribute[] attributes;

  public ClassFile(DataInput data) throws IOException {
    this.magic = Integer.toUnsignedLong(data.readInt());
    this.minorVersion = data.readUnsignedShort();
    this.majorVersion = data.readUnsignedShort();
    this.constantPoolCount = data.readUnsignedShort();
    final int maxIndex = this.constantPoolCount - 1;
    this.constantPools = new ConstantPool[maxIndex];
    for (int i = 0; i < maxIndex; i++) {
      var cp = ConstantPoolFactory.getInstance(data);
      this.constantPools[i] = cp;
    }
    this.accessFlags = data.readUnsignedShort();
    this.thisClass = data.readUnsignedShort();
    this.superClass = data.readUnsignedShort();
    this.interfacesCount = data.readUnsignedShort();
    this.interfaces = new int[this.interfacesCount];
    for (int i = 0; i < interfacesCount; i++) {
      this.interfaces[i] = data.readUnsignedShort();
    }
    this.fieldsCount = data.readUnsignedShort();
    this.fields = new Field[this.fieldsCount];
    // TODO implements Field
    this.methodsCount = data.readUnsignedShort();
    this.methods = new Method[this.methodsCount];
    for (int i = 0; i < this.methodsCount; i++) {
      this.methods[i] = new Method(data, this.constantPools);
    }
    this.attributesCount = data.readUnsignedShort();
    this.attributes = new Attribute[this.attributesCount];
    for (int i = 0; i < this.attributesCount; i++) {
      final Attribute attr = AttributeFactory.getInstance(data, this.constantPools);
      this.attributes[i] = attr;
    }
  }
}
