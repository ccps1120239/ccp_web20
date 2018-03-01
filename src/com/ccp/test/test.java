package com.ccp.test;

import java.io.Serializable;

public interface test {
    public <T> T get(Class<T> tClass,Serializable id);

}
