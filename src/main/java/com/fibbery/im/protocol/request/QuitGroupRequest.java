package com.fibbery.im.protocol.request;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@Data
public class QuitGroupRequest extends BasePacket {

    private long groupId;

    @Override
    public byte getCommand() {
        return Command.QUIT_GROUP_REQUEST_COMMAND;
    }
}
