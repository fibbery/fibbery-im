package com.fibbery.im.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public class JsonSerializer implements Serializer {

    @Override
    public byte getAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object t) {
        return JSON.toJSONBytes(t);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
