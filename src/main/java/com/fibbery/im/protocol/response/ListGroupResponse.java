package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;
import lombok.Data;

import java.util.Map;

/**
 * @author fibbery
 * @date 2018/11/14
 */
@Data
public class ListGroupResponse extends BasePacket {

    private boolean success;

    private long groupId;

    private Map<Long,String> users;

    private String message;

    @Override
    public byte getCommand() {
        return Command.LIST_GROUP_RESPONSE_COMMAND;
    }
}
