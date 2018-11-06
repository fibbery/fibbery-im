package com.fibbery.im.protocol.request;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/1
 */
@Data
public class LoginRequest extends BasePacket {

    private Long userId;

    private String password;

    @Override
    public byte getCommand() {
        return Command.LOGIN_REQUEST_COMAMND;
    }
}
