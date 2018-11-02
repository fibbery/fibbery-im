package com.fibbery.im.serialize;

/**
 * @author fibbery
 * @date 2018/11/1
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 获取算法标志
     * @return
     */
    byte getAlgorithm();

    /**
     * 序列化
     * @param T
     * @return
     */
    byte[] serialize(Object T);

    /**
     * 反序列化
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
