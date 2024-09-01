package com.litongjava.im.server.config;

public interface MyChatServerConstant {

  /**
   * 用于群聊的group id
   */
  String GROUP_ID = "group-01";
  String CHARSET = "utf-8";
  /**
   * 心跳超时时间，单位：毫秒
   */
  int HEARTBEAT_TIMEOUT = 1000 * 60;
}
