package com.litongjava.im.server.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.litongjava.im.server.config.MyChatServerConstant;
import com.litongjava.tio.core.ChannelContext;
import com.litongjava.tio.core.Tio;
import com.litongjava.tio.core.intf.Packet;
import com.litongjava.tio.websocket.common.WsResponse;
import com.litongjava.tio.websocket.common.WsSessionContext;
import com.litongjava.tio.websocket.server.WebSocketServerAioListener;

public class MyWebSocketServerAioListener extends WebSocketServerAioListener {
  private static Logger log = LoggerFactory.getLogger(MyWebSocketServerAioListener.class);
  public static final MyWebSocketServerAioListener me = new MyWebSocketServerAioListener();

  private MyWebSocketServerAioListener() {
  }

  @Override
  public void onAfterConnected(ChannelContext channelContext, boolean isConnected, boolean isReconnect) throws Exception {
    super.onAfterConnected(channelContext, isConnected, isReconnect);
    if (log.isInfoEnabled()) {
      log.info("onAfterConnected\r\n{}", channelContext);
    }
  }

  @Override
  public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) throws Exception {
    super.onAfterSent(channelContext, packet, isSentSuccess);
    if (log.isInfoEnabled()) {
      log.info("onAfterSent\r\n{}\r\n{}", packet.logstr(), channelContext);
    }
  }

  @Override
  public void onBeforeClose(ChannelContext channelContext, Throwable throwable, String remark, boolean isRemove) throws Exception {
    super.onBeforeClose(channelContext, throwable, remark, isRemove);
    if (log.isInfoEnabled()) {
      log.info("onBeforeClose\r\n{}", channelContext);
    }
    @SuppressWarnings("deprecation")
    WsSessionContext wsSessionContext = (WsSessionContext) channelContext.getAttribute();
    if (wsSessionContext != null && wsSessionContext.isHandshaked()) {
      int count = Tio.getAll(channelContext.tioConfig).getObj().size();
      String msg = channelContext.getClientNode().toString() + " 离开了，现在共有【" + count + "】人在线";
      // 用tio-websocket，服务器发送到客户端的Packet都是WsResponse
      WsResponse wsResponse = WsResponse.fromText(msg, MyChatServerConstant.CHARSET);
      // 群发
      Tio.sendToGroup(channelContext.tioConfig, MyChatServerConstant.GROUP_ID, wsResponse);
    }
  }

  @Override
  public void onAfterDecoded(ChannelContext channelContext, Packet packet, int packetSize) throws Exception {
    super.onAfterDecoded(channelContext, packet, packetSize);
    if (log.isInfoEnabled()) {
      log.info("onAfterDecoded\r\n{}\r\n{}", packet.logstr(), channelContext);
    }
  }

  @Override
  public void onAfterReceivedBytes(ChannelContext channelContext, int receivedBytes) throws Exception {
    super.onAfterReceivedBytes(channelContext, receivedBytes);
    if (log.isInfoEnabled()) {
      log.info("onAfterReceivedBytes\r\n{}", channelContext);
    }
  }

  @Override
  public void onAfterHandled(ChannelContext channelContext, Packet packet, long cost) throws Exception {
    super.onAfterHandled(channelContext, packet, cost);
    if (log.isInfoEnabled()) {
      log.info("onAfterHandled\r\n{}\r\n{}", packet.logstr(), channelContext);
    }
  }
}