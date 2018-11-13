package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/3
 */
@Data
public class MessageResponse extends BasePacket {

    private long senderId;

    private String senderName;

    private long receiverId;

    private String message;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_RESPONSE_COMMAND;
    }
}
