package com.fibbery.im.protocol;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public interface Command {
    /* 登录请求指令 */
    Byte LOGIN_REQUEST_COMAMND = 1;

    /* 登录响应指令*/
    Byte LOGIN_RESPONSE_COMMAND = 2;

    /* 消息请求服务 */
    Byte MESSAGE_REQUEST_COMMAND = 3;

    Byte MESSAGE_RESPONSE_COMMAND = 4;

    /*创建群组*/
    Byte CREATE_GROUP_REQUEST_COMMAND = 5;

    Byte CREATE_GROUP_RESPONSE_COMMAND = 6;

    /*登录退出*/
    Byte LOGIN_OUT_REQUEST_COMMAND = 7;

    Byte LOGIN_OUT_RESPONSE_COMMAND = 8;

    /*群消息*/
    Byte GROUP_MESSAGE_REQUEST_COMMAND = 9;

    Byte GROUP_MESSAGE_RESPONSE_COMMAND = 10;

    /*退群*/
    Byte QUIT_GROUP_REQUEST_COMMAND = 11;

    Byte QUIT_GROUP_RESPONSE_COMMAND = 12;

    /*加群*/
    Byte JOIN_GROUP_REQUEST_COMMAND = 13;

    Byte JOIN_GROUP_RESPONSE_COMMAND = 14;

    /*列举群成员*/
    Byte LIST_GROUP_REQUEST_COMMAND = 15;

    Byte LIST_GROUP_RESPONSE_COMMAND = 16;

}
