package com.miya10kei.model.attribute;

import com.miya10kei.type.U2;
import com.miya10kei.type.U4;
import java.io.IOException;
import java.io.InputStream;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SourceFile extends Attribute {
  private final U2 sourceFileIndex;

  public SourceFile(final U2 attributeNameIndex, final U4 attributeLength, final InputStream data)
      throws IOException {
    super(attributeNameIndex, attributeLength);
    this.sourceFileIndex = new U2(data.readNBytes(2));
  }
}
