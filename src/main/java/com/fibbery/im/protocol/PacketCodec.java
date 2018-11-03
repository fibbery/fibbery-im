package com.fibbery.im.protocol;

import com.fibbery.im.serialize.Serializer;
import com.fibbery.im.serialize.SerializerAlgorithm;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 数据包 编码/解码器
 *
 * @author fibbery
 * @date 2018/11/1
 */
public class PacketCodec {

    public static PacketCodec INSTANCE = new PacketCodec();

    private static final int MAGIC_NUM = 0x12345678;

    /**
     * 序列化方式
     */
    private static Map<Byte, Serializer> serializerMap;

    /**
     * 各种基础请求包
     */
    private static Map<Byte, Class<? extends BasePacket>> packetTypeMap;

    static {
        Reflections reflections = new Reflections("com.fibbery");

        //载入序列化方式
        serializerMap = new HashMap<>();
        Set<Class<? extends Serializer>> clazzes = reflections.getSubTypesOf(Serializer.class);
        for (Class<? extends Serializer> clazz : clazzes) {
            boolean isAbstrace = Modifier.isAbstract(clazz.getModifiers());
            if (isAbstrace) {
                continue;
            }
            try {
                Serializer serializer = clazz.newInstance();
                serializerMap.put(serializer.getAlgorithm(), serializer);
            } catch (Exception e) {
                System.err.println(clazz.getSimpleName() + "无空构造，不进行录入");
            }

        }

        //载入数据包格式
        packetTypeMap = new HashMap<>();
        Set<Class<? extends BasePacket>> classes = reflections.getSubTypesOf(BasePacket.class);
        for (Class<? extends BasePacket> clazz : classes) {
            boolean isAbstrace = Modifier.isAbstract(clazz.getModifiers());
            if (isAbstrace) {
                continue;
            }
            try {
                BasePacket packet = clazz.newInstance();
                packetTypeMap.put(packet.getCommand(), clazz);
            } catch (Exception e) {
                System.err.println(clazz.getSimpleName() + "无空构造，不进行录入");
            }
        }
    }

    /**
     * * 包结构
     * |  魔法数字  |  协议版本  | 序列化方式  |  指令  |  数据长度  |  真实数据
     * 4byte       1byte      1byte      1byte     4byte
     *
     * @param packet
     * @return
     */
    public ByteBuf encode(BasePacket packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        //魔法数
        byteBuf.writeInt(MAGIC_NUM);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerAlgorithm.JSON);
        byteBuf.writeByte(packet.getCommand());

        byte[] data = Serializer.DEFAULT.serialize(packet);
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
        return byteBuf;
    }


    public BasePacket decode(ByteBuf byteBuf) {
        //跳过魔法数
        byteBuf.skipBytes(4);
        //跳过版本
        byte version = byteBuf.readByte();
        //获取序列化方式
        byte serializerAlgorithm = byteBuf.readByte();
        //获取指令
        byte command = byteBuf.readByte();
        //获取数据长度
        int length = byteBuf.readInt();
        //获取真是数据
        byte[] buffer = new byte[length];
        byteBuf.readBytes(buffer);

        //还原数据
        Serializer serializer = serializerMap.get(serializerAlgorithm);
        Class<? extends BasePacket> clazz = packetTypeMap.get(command);
        if (serializer == null || clazz == null) {
            throw new RuntimeException("未能找到数据包对应的class或序列化方式");
        }
        BasePacket packet = serializer.deserialize(clazz, buffer);
        packet.setVersion(version);
        return packet;
    }

    private PacketCodec() {
    }
}
