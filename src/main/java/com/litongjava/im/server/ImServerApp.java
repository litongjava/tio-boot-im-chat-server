package com.litongjava.im.server;

import com.litongjava.jfinal.aop.annotation.AComponentScan;
import com.litongjava.tio.boot.TioApplication;

@AComponentScan
public class ImServerApp {
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    TioApplication.run(ImServerApp.class, args);
    long end = System.currentTimeMillis();
    System.out.println((end - start) + "ms");
  }
}
