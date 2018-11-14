package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@Data
public class QuitGroupResponse extends BasePacket {

    private boolean success;

    private String message;

    private long groupId;

    @Override
    public byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE_COMMAND;
    }
}
