package com.qunce.io;

import com.qunce.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;

/**
 * @Description
 * 1. big-endian:
 * 1. little-endian:
 * 2. 此类只会被输入输入流调用
 * @Author hu zhongxi
 */
@Slf4j
public class BitsTest {

/*    @Test
    public void test() throws Exception {
        String filename = "E:\\aa.txt";
        Person person = new Person("张三", 25, false, 20141.0D);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filename));
        objectOutputStream.writeObject(person);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filename));
        Object object = objectInputStream.readObject();

        log.info("输出信息：{}", object);
        assertEquals(person, object);
    }*/

    @Test
    public void basic() {
        assertEquals(127, Byte.MAX_VALUE);
        assertEquals(-128, Byte.MIN_VALUE);

        /**
         * 一个字符占两个字节
         */
        assertEquals(65535, Character.MAX_VALUE);
        assertEquals(0, Character.MIN_VALUE);

        /**
         * 十进制：20013
         * 二进制：1001110 00101101
         */
        System.out.println((int)'中');
        System.out.println((char)20013);
    }

    /**
     *
     */
    @Test
    public void getBoolean() {
        boolean result = Bits.getBoolean(new byte[]{1, 2, 3}, 2);
        assertEquals(true, result);
    }

    /**
     * 理解大端排序
     * 以字符“中”为例，“中”转换为十进制为：20013，转换位二进制为：1001110 00101101，
     * 分开转换为十进制结果为：78，45
     * 大端排序就是高位在前，低位在后。这是数据编解码的范畴。
     */
    @Test
    public void getChar() {
        char result = Bits.getChar(new byte[]{78, 45}, 0);
        assertEquals('中', result);

        assertEquals(20013, (int)'中');
        assertEquals('中', (char) 20013);
    }

    @Test
    public void getShort() {
        short result = Bits.getShort(new byte[]{78, 45}, 0);
        assertEquals(20013, result);
    }

    /**
     * 以此类推
     * 需要注意的int转float、long转double都需要调用原生方法
     */

}

class Bits {

    static boolean getBoolean(byte[] b, int off) {
        return b[off] != 0;
    }

    static char getChar(byte[] b, int off) {
        return (char) ((b[off + 1] & 0xFF) +
                (b[off] << 8));
    }

    static short getShort(byte[] b, int off) {
        return (short) ((b[off + 1] & 0xFF) +
                (b[off] << 8));
    }

    static int getInt(byte[] b, int off) {
        return ((b[off + 3] & 0xFF)      ) +
                ((b[off + 2] & 0xFF) <<  8) +
                ((b[off + 1] & 0xFF) << 16) +
                ((b[off    ]       ) << 24);
    }

    static float getFloat(byte[] b, int off) {
        return Float.intBitsToFloat(getInt(b, off));
    }

    static long getLong(byte[] b, int off) {
        return ((b[off + 7] & 0xFFL)      ) +
                ((b[off + 6] & 0xFFL) <<  8) +
                ((b[off + 5] & 0xFFL) << 16) +
                ((b[off + 4] & 0xFFL) << 24) +
                ((b[off + 3] & 0xFFL) << 32) +
                ((b[off + 2] & 0xFFL) << 40) +
                ((b[off + 1] & 0xFFL) << 48) +
                (((long) b[off])      << 56);
    }

    static double getDouble(byte[] b, int off) {
        return Double.longBitsToDouble(getLong(b, off));
    }

    /*
     * Methods for packing primitive values into byte arrays starting at given
     * offsets.
     */

    static void putBoolean(byte[] b, int off, boolean val) {
        b[off] = (byte) (val ? 1 : 0);
    }

    static void putChar(byte[] b, int off, char val) {
        b[off + 1] = (byte) (val      );
        b[off    ] = (byte) (val >>> 8);
    }

    static void putShort(byte[] b, int off, short val) {
        b[off + 1] = (byte) (val      );
        b[off    ] = (byte) (val >>> 8);
    }

    static void putInt(byte[] b, int off, int val) {
        b[off + 3] = (byte) (val       );
        b[off + 2] = (byte) (val >>>  8);
        b[off + 1] = (byte) (val >>> 16);
        b[off    ] = (byte) (val >>> 24);
    }

    static void putFloat(byte[] b, int off, float val) {
        putInt(b, off,  Float.floatToIntBits(val));
    }

    static void putLong(byte[] b, int off, long val) {
        b[off + 7] = (byte) (val       );
        b[off + 6] = (byte) (val >>>  8);
        b[off + 5] = (byte) (val >>> 16);
        b[off + 4] = (byte) (val >>> 24);
        b[off + 3] = (byte) (val >>> 32);
        b[off + 2] = (byte) (val >>> 40);
        b[off + 1] = (byte) (val >>> 48);
        b[off    ] = (byte) (val >>> 56);
    }

    static void putDouble(byte[] b, int off, double val) {
        putLong(b, off, Double.doubleToLongBits(val));
    }
}

