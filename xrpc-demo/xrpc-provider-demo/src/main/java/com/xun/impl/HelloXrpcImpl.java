package com.xun.impl;

import com.xun.HelloXrpc;

/**
 * ClassName: HelloXrpcImpl
 * Package: com.xun.impl
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/21 18:19
 * @Version 1.0
 */
public class HelloXrpcImpl implements HelloXrpc
{

    @Override
    public String sayHi(String msg) {
        return "Hi Consumer:"+msg;
    }
}
