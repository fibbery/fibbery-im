package com.fibbery.im.protocol.response;

import com.fibbery.im.protocol.BasePacket;
import com.fibbery.im.protocol.Command;

/**
 * @author fibbery
 * @date 2019-01-05
 */
public class HeartBeatResponse extends BasePacket {
    @Override
    public byte getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}
