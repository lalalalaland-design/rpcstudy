package com.luban.framework;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Setter
@Getter
public class Invocation implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class[] paramType;

    public Invocation(String interfaceName, String methodName, Object[] params, Class[] paramType) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramType = paramType;
    }

}
