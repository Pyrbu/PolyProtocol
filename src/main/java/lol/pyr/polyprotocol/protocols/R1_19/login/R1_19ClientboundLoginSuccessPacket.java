package lol.pyr.polyprotocol.protocols.R1_19.login;

import lol.pyr.polyprotocol.data.PacketBuffer;
import lol.pyr.polyprotocol.protocols.ProtocolVersion;
import lol.pyr.polyprotocol.protocols.R1_16.login.R1_16ClientboundLoginSuccessPacket;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

public class R1_19ClientboundLoginSuccessPacket extends R1_16ClientboundLoginSuccessPacket {
    @Getter private final Property[] properties;

    public R1_19ClientboundLoginSuccessPacket(ProtocolVersion protocolVersion, UUID uuid, String username, Property[] properties) {
        super(protocolVersion, uuid, username);
        this.properties = properties;
    }

    public R1_19ClientboundLoginSuccessPacket(ProtocolVersion protocolVersion, PacketBuffer buffer) {
        super(protocolVersion, buffer);
        final int propertyAmount = buffer.readVarInt();
        this.properties = new Property[propertyAmount];
        for (int i = 0; i < propertyAmount; i++) this.properties[i] = new Property(buffer);
    }

    @Override
    public PacketBuffer writeTo(PacketBuffer buffer) {
        super.writeTo(buffer);
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

        public Property(PacketBuffer buffer) {
            this.name = buffer.readString();
            this.value = buffer.readString();
            this.signature = buffer.readBoolean() ? buffer.readString() : null;
        }
    }
}
