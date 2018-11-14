package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@Data
public class JoinGroupResponse extends BasePacket {

    private boolean success;

    private String message;

    @Override
    public byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE_COMMAND;
    }

}
