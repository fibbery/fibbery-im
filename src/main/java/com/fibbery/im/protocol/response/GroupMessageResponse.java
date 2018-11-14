package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@Data
public class GroupMessageResponse extends BasePacket {

    private boolean success;

    private long senderId;

    private String senderName;

    private long groupId;

    private String message;

    @Override
    public byte getCommand() {
        return Command.GROUP_MESSAGE_RESPONSE_COMMAND;
    }
}
