package com.fibbery.im.protocol.request;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@Data
public class LoginoutRequest extends BasePacket {

    @Override
    public byte getCommand() {
        return Command.LOGIN_OUT_REQUEST_COMMAND;
    }

}
