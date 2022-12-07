package com.zhide.govwatch.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {
    @Value("${spring.socketio.host}")
    private String host;

    @Value("${spring.socketio.port}")
    private Integer port;

    @Value("${spring.socketio.bossCount}")
    private int bossCount;

    @Value("${spring.socketio.workCount}")
    private int workCount;

    @Value("${spring.socketio.allowCustomRequests}")
    private boolean allowCustomRequests;

    @Value("${spring.socketio.upgradeTimeout}")
    private int upgradeTimeout;

    @Value("${spring.socketio.pingTimeout}")
    private int pingTimeout;

    @Value("${spring.socketio.pingInterval}")
    private int pingInterval;

    /**
     * 以下配置在上面的application.properties中已经注明
     * @return
     */
    @Bean
    public SocketIOServer socketIOServer() {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(port);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);
        return new SocketIOServer(config);
    }

   /**
            * 用于扫描netty-socketio的注解，比如 @OnConnect、@OnEvent
	 */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }
}
