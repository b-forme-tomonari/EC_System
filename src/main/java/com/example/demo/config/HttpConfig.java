package com.example.demo.config;

import java.util.Optional;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
public class HttpConfig {
    
    @Bean
    WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
        // HTTPリクエストを受信するコンテナ作成
    	return server ->
            Optional.ofNullable(server)
                    .ifPresent(s -> s.addAdditionalTomcatConnectors(redirectConnector()));
    }
 
    private Connector redirectConnector() {
    	// AJPプロトコル設定 
        Connector connector = new Connector("AJP/1.3");
        connector.setScheme("http");     // プロトコル
        connector.setPort(8009);         // 着信接続を待つTCPポート番号
        connector.setRedirectPort(8443); // リダイレクトポート番号
        connector.setSecure(false);      // セキュア接続フラグ
        connector.setAllowTrace(false);  // TRACE HTTPメソッド無効
        return connector;
    }
}