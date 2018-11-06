package com.fibbery.im.protocol.request;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/3
 */
@Data
public class MessageRequest extends BasePacket {

    private long receiverId;

    private String message;

    @Override
    public byte getCommand() {
        return Command.MESSAGE_REQUEST_COMMAND;
    }
}
