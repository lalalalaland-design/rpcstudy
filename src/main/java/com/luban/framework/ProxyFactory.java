package com.luban.framework;

import com.luban.framework.protocol.HttpClient;
import com.luban.framework.protocol.HtttpServerHandler;
import com.luban.framework.register.RemoteMapRegister;
import com.luban.provider.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory {

    public static <T> T getProxy(Class interfaceClass){

        Object instance = Proxy.newProxyInstance(ProxyFactory.class.getClassLoader(), new Class[]{interfaceClass}
                , new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        HttpClient httpClient = new HttpClient();
                        Invocation invocation = new Invocation(interfaceClass.getName(),method.getName(),
                                args,method.getParameterTypes());

                        List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
                        URL url = LoadBalance.random(urlList);

                        String result = httpClient.send(url.getHostname(), url.getPort(), invocation);
                        return result;
                    }
                });
        return (T) instance;
    }

}
