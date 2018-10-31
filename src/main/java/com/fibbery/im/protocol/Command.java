package com.fibbery.im.protocol;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public interface Command {
    /* 登录请求指令 */
    byte LOGIN_REQUEST_COMAMND = 1;

    /* 登录响应指令*/
    byte LOGIN_RESPONSE_COMMAND = 2;
}
