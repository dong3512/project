package com.dong.util;

public interface ObjectFactory<T> {
  T create(String csvStr);
}
