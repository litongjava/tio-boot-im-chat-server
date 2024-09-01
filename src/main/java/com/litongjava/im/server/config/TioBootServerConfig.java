package com.litongjava.im.server.config;

import com.litongjava.im.server.constant.IpStatDuration;
import com.litongjava.im.server.listener.MyIpStatListener;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.server.ServerTioConfig;
import com.litongjava.tio.utils.environment.EnvUtils;

import lombok.extern.slf4j.Slf4j;

@AConfiguration
@Slf4j
public class TioBootServerConfig {

  @AInitialization(priority = 99)
  public void config() {

    ServerTioConfig serverTioConfig = TioBootServer.me().getServerTioConfig();

    // 设置ip监控
    serverTioConfig.setIpStatListener(MyIpStatListener.me);
    // 设置ip统计时间段
    serverTioConfig.ipStats.addDurations(IpStatDuration.IPSTAT_DURATIONS);

    // 设置心跳超时时间
    serverTioConfig.setHeartbeatTimeout(MyChatServerConstant.HEARTBEAT_TIMEOUT);

    if (EnvUtils.getBoolean("server.ssl.enable", false)) {
      log.info("enable ssl");
      String keyStoreFile = EnvUtils.get("server.ssl.keyStore", null);
      String trustStoreFile = EnvUtils.get("server.ssl.trustStore", null);
      String keyStorePwd = EnvUtils.get("server.ssl.password", null);
      try {
        serverTioConfig.useSsl(keyStoreFile, trustStoreFile, keyStorePwd);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

}
