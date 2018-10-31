package com.fibbery.im.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public class JsonSerializer implements Serializer {

    public byte getAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    public byte[] serialize(Object t) {
        return JSON.toJSONBytes(t);
    }

    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
