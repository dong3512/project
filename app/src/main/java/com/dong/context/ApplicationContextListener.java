package com.dong.context;

public interface ApplicationContextListener {
  void contextInitialized();

  void contextDestroyed();
}
