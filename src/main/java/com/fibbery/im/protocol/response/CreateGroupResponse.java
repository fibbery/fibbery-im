package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

import java.util.HashMap;

/**
 * @author fibbery
 * @date 2018/11/13
 */
@Data
public class CreateGroupResponse extends BasePacket {

    private long groupId;

    private HashMap<Long, String> users;

    @Override
    public byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE_COMMAND;
    }
}
