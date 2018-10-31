package com.fibbery.im.serialize;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    byte getAlgorithm();

    byte[] serialize(Object T);

    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
