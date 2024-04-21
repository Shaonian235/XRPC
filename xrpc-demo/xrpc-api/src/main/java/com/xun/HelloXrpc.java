package com.xun;

/**
 * ClassName: com.xun.HelloXrpc
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author ZJX
 * @Create 2024/4/21 18:14
 * @Version 1.0
 */
public interface HelloXrpc {
    /**
     * 通用接口，server和client都需要依赖
     * @param msg 发送的具体消息
     * @return 返回的结果
     */
    String sayHi(String msg);
}
