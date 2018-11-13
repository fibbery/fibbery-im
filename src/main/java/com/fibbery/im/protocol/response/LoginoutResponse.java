package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@Data
public class LoginoutResponse extends BasePacket {

    private boolean success;

    @Override
    public byte getCommand() {
        return Command.LOGIN_OUT_RESPONSE_COMMAND;
    }
}
