package com.luban.comsumer;

import com.luban.framework.Invocation;
import com.luban.framework.ProxyFactory;
import com.luban.framework.protocol.HttpClient;
import com.luban.provider.HelloService;

import java.net.URISyntaxException;

public class Consumer {
    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String result = helloService.sayHello("xxx");
        System.out.println(result);

    }
}
