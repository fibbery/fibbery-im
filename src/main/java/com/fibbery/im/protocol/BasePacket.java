package com.fibbery.im.protocol;

import lombok.Data;

/**
 * @author fibbery
 * @date 2018/11/1
 */
@Data
public abstract class BasePacket {

    private Byte version = 1;

    public abstract byte getCommand();
}
