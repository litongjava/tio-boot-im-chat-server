package com.litongjava.im.server.config;
import com.litongjava.im.server.handler.ImWebSocketHandler;
import com.litongjava.jfinal.aop.annotation.AConfiguration;
import com.litongjava.jfinal.aop.annotation.AInitialization;
import com.litongjava.tio.boot.server.TioBootServer;
import com.litongjava.tio.boot.websocket.handler.WebSocketRoutes;

@AConfiguration
public class WebSocketConfig {

  @AInitialization
  public void config() {
    WebSocketRoutes webSocketRoutes = new WebSocketRoutes();
    
    webSocketRoutes.add("/im", new ImWebSocketHandler());
    
    // 添加到TioBootServer,为了保证高性能,默认webSocketRoutes为null,必须添加
    TioBootServer.me().setWebSocketRoutes(webSocketRoutes);
  }
}
