package com.fibbery.im.protocol.request;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

import java.util.List;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@Data
public class CreateGroupRequest extends BasePacket {

    private List<Long> userIds;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_REQUEST_COMMAND;
    }
}
