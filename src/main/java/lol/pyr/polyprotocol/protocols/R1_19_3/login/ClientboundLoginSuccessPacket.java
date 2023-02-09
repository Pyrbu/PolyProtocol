package lol.pyr.polyprotocol.protocols.R1_19_3.login;

import lol.pyr.polyprotocol.PacketBuffer;
import lol.pyr.polyprotocol.ProtocolState;
import lol.pyr.polyprotocol.ProtocolVersion;
import lol.pyr.polyprotocol.api.Packet;
import lombok.Data;

import java.util.*;

@Data
public class ClientboundLoginSuccessPacket implements Packet {
    private final UUID uuid;
    private final String username;
    private final Property[] properties;

    @Override
    public ProtocolState getProtocolState() {
        return ProtocolState.LOGIN;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        return ProtocolVersion.R1_19_3;
    }

    @Override
    public int getId() {
        return 0x02;
    }

    public static ClientboundLoginSuccessPacket fromBuffer(PacketBuffer buffer) {
        UUID uuid = buffer.readUUID();
        String username = buffer.readString();
        int propertyAmount = buffer.readVarInt();
        Property[] properties = new Property[propertyAmount];
        for (int i = 0; i < propertyAmount; i++) properties[i] = Property.read(buffer);
        return new ClientboundLoginSuccessPacket(uuid, username, properties);
    }

    @Override
    public PacketBuffer toBuffer() {
        PacketBuffer buffer = new PacketBuffer();
        buffer.writeUUID(uuid);
        buffer.writeString(username);
        buffer.writeVarInt(properties.length);
        for (Property property: properties) property.write(buffer);
        return buffer;
    }

    @Data
    static class Property {
        private final String name;
        private final String value;
        private final String signature;

        public boolean isSigned() {
            return signature != null;
        }

        public PacketBuffer write(PacketBuffer buffer) {
            buffer.writeString(name);
            buffer.writeString(value);
            buffer.writeBoolean(isSigned());
            if (isSigned()) buffer.writeString(signature);
            return buffer;
        }

        public static Property read(PacketBuffer buffer) {
            return new Property(
                    buffer.readString(),
                    buffer.readString(),
                    buffer.readBoolean() ? buffer.readString() : null
            );
        }
    }
}
