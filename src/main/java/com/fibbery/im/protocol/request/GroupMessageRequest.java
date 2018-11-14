package com.fibbery.im.protocol.request;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@Data
public class GroupMessageRequest extends BasePacket {

    private long groupId;

    private String message;

    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST_COMMAND;
    }

}
