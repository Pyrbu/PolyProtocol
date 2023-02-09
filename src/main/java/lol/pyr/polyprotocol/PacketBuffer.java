package lol.pyr.polyprotocol;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.UUID;

public class PacketBuffer {
    private final Deque<Byte> bytes = new ArrayDeque<>();

    public int size() {
        return bytes.size();
    }

    public byte[] toByteArray() {
        byte[] arr = new byte[bytes.size()];
        Deque<Byte> clone = new ArrayDeque<>(bytes);
        for (int i = 0; i < bytes.size(); i++) arr[i] = clone.removeFirst();
        return arr;
    }

    public PacketBuffer writeByte(int i) {
        bytes.addLast((byte) i);
        return this;
    }

    public byte readByte() {
        return bytes.removeFirst();
    }

    public short readUnsignedByte() {
        return (short) (readByte() & 0xFF);
    }

    public PacketBuffer writeBoolean(boolean b) {
        writeByte(b ? 1 : 0);
        return this;
    }

    public boolean readBoolean() {
        return readByte() != 0;
    }

    public PacketBuffer writeBytes(byte[] bytes) {
        for (byte b : bytes) this.bytes.addLast(b);
        return this;
    }

    public byte[] readBytes(int length) {
        byte[] ret = new byte[length];
        for (int i = 0; i < length; i++) ret[i] = readByte();
        return ret;
    }

    public PacketBuffer writeByteArray(byte[] bytes) {
        writeVarInt(bytes.length);
        writeBytes(bytes);
        return this;
    }

    public byte[] readByteArray() {
        return readBytes(readVarInt());
    }

    public PacketBuffer writePublicKey(PublicKey key) {
        writeByteArray(key.getEncoded());
        return this;
    }

    public PublicKey readPublicKey() {
        try {
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(readByteArray());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(encodedKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public PacketBuffer writeShort(int i) {
        writeByte((byte) (i >>> 8));
        writeByte((byte) i);
        return this;
    }

    public short readShort() {
        byte b1 = readByte();
        byte b2 = readByte();
        return (short) ((b1 & 0xff) << 8 | b2 & 0xff);
    }

    public int readUnsignedShort() {
        return readShort() & 0xFFFF;
    }

    public PacketBuffer writeInt(int i) {
        writeShort((short) (i >>> 16));
        writeShort((short) i);
        return this;
    }

    public int readInt() {
        short s1 = readShort();
        short s2 = readShort();
        return (s1 & 0xffff) << 16 | s2 & 0xffff;
    }

    public long readUnsignedInt() {
        return readInt() & 0xFFFFFFFFL;
    }

    public PacketBuffer writeLong(long l) {
        writeInt((int) (l >>> 32));
        writeInt((int) l);
        return this;
    }

    public long readLong() {
        int i1 = readInt();
        int i2 = readInt();
        return (i1 & 0xffffffffL) << 32 | i2 & 0xffffffffL;
    }

    public PacketBuffer writeFloat(float f) {
        writeInt(Float.floatToRawIntBits(f));
        return this;
    }

    public float getFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public PacketBuffer writeDouble(double d) {
        writeLong(Double.doubleToRawLongBits(d));
        return this;
    }

    public double getDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public PacketBuffer writeString(String s) {
        writeVarInt(s.length());
        writeBytes(s.getBytes(StandardCharsets.UTF_8));
        return this;
    }

    public String readString() {
        int j = this.readVarInt();
        return new String(readBytes(j), 0, j, StandardCharsets.UTF_8);
    }

    public PacketBuffer writeVarInt(int value) {
        while (true) {
            if ((value & 0xFFFFFF80) == 0) {
                this.writeByte(value);
                return this;
            }
            this.writeByte(value & 0x7F | 0x80);
            value >>>= 7;
        }
    }

    public int readVarInt() {
        byte b;
        int i = 0;
        int j = 0;
        while (true) {
            b = this.readByte();
            i |= (b & 0x7F) << j++ * 7;
            if ((b & 0x80) == 128) break;
            if (j <= 5) continue;
            throw new RuntimeException("VarInt too big");
        }
        return i;
    }

    public PacketBuffer writeVarLong(long value) {
        while (true) {
            if ((value & 0xFFFFFFFFFFFFFF80L) == 0L) {
                this.writeByte((int) value);
                return this;
            }
            this.writeByte((int) (value & 0x7FL) | 0x80);
            value >>>= 7;
        }
    }

    public long readVarLong() {
        byte b;
        long l = 0L;
        int i = 0;
        while (true) {
            b = this.readByte();
            l |= (long)(b & 0x7F) << i++ * 7;
            if ((b & 0x80) == 128) break;
            if (i <= 10) continue;
            throw new RuntimeException("VarLong too big");
        }
        return l;
    }

    public PacketBuffer writePosition(int x, int y, int z) {
        writeLong(((x & 0x3FFFFFFL) << 6) | ((z & 0x3FFFFFFL) << 12) | (y & 0xFFF));
        return this;
    }

    // TODO: write a position class and make a readPosition method

    public PacketBuffer writeUUID(UUID uuid) {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
        return this;
    }

    public UUID readUUID() {
        return new UUID(readLong(), readLong());
    }
}
