package com.luban.provider;

import com.luban.framework.URL;
import com.luban.framework.protocol.HttpServer;
import com.luban.framework.register.LocalRegister;
import com.luban.framework.register.RemoteMapRegister;

public class Provider {
    public static void main(String[] args) {

        LocalRegister.regist(HelloService.class.getName(),HelloServiceImpl.class);

        URL url = new URL("localhost", 8080);
        RemoteMapRegister.regist(HelloService.class.getName(),url);

        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHostname(), url.getPort());
    }
}
