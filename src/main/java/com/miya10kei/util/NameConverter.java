package com.miya10kei.util;

public class NameConverter {
  public static String toFQCN(String name) {
    return name.replaceAll("/", ".");
  }
}
