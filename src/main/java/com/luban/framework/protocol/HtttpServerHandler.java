package com.luban.framework.protocol;

import com.alibaba.fastjson.JSONObject;
import com.luban.framework.Invocation;
import com.luban.framework.register.LocalRegister;
import org.apache.commons.io.IOUtils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HtttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp)  {
        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            String interfaceName = invocation.getInterfaceName();
            Class clazz = LocalRegister.get(interfaceName);
            Method method = clazz.getMethod(invocation.getMethodName(), invocation.getParamType());
            String result = (String) method.invoke(clazz.newInstance(), invocation.getParams());
            IOUtils.write(result,resp.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static void test(){

    }
}
