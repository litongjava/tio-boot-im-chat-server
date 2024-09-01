package com.litongjava.im.server.listener;

import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.TioConfig;
import com.litongjava.tio.core.intf.Packet;
import com.litongjava.tio.core.stat.IpStat;
import com.litongjava.tio.core.stat.IpStatListener;
import com.litongjava.tio.utils.json.JsonUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyIpStatListener implements IpStatListener {
  public static final MyIpStatListener me = new MyIpStatListener();

  /**
   * 
   */
  private MyIpStatListener() {
  }

  @Override
  public void onExpired(TioConfig tioConfig, IpStat ipStat) {
    // 在这里把统计数据入库中或日志
    if (log.isInfoEnabled()) {
      log.info("可以把统计数据入库\r\n{}", JsonUtils.toJson(ipStat));
    }
  }

  @Override
  public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect, IpStat ipStat) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("onAfterConnected\r\n{}", JsonUtils.toJson(ipStat));
    }
  }

  @Override
  public void onDecodeError(ChannelContext channelContext, IpStat ipStat) {
    if (log.isInfoEnabled()) {
      log.info("onDecodeError\r\n{}", JsonUtils.toJson(ipStat));
    }
  }

  @Override
  public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess, IpStat ipStat) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("onAfterSent\r\n{}\r\n{}", packet.logstr(), JsonUtils.toJson(ipStat));
    }
  }

  @Override
  public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize, IpStat ipStat) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("onAfterDecoded\r\n{}\r\n{}", packet.logstr(), JsonUtils.toJson(ipStat));
    }
  }

  @Override
  public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes, IpStat ipStat) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("onAfterReceivedBytes\r\n{}", JsonUtils.toJson(ipStat));
    }
  }

  @Override
  public void onAfterHandled(ChannelContext channelContext, Packet packet, IpStat ipStat, long cost) throws Exception {
    if (log.isInfoEnabled()) {
      log.info("onAfterHandled\r\n{}\r\n{}", packet.logstr(), JsonUtils.toJson(ipStat));
    }
  }
}