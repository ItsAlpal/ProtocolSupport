package protocolsupport.protocol.transformer.middlepacketimpl.v_1_6_1_7.clientbound.play;

import java.util.Collection;
import java.util.Collections;

import protocolsupport.api.ProtocolVersion;
import protocolsupport.protocol.ClientBoundPacket;
import protocolsupport.protocol.PacketDataSerializer;
import protocolsupport.protocol.transformer.middlepacket.clientbound.play.MiddleEntitySetAttributes;
import protocolsupport.protocol.transformer.middlepacketimpl.PacketData;

public class EntitySetAttributes extends MiddleEntitySetAttributes<Collection<PacketData>> {

	@Override
	public Collection<PacketData> toData(ProtocolVersion version) {
		PacketDataSerializer serializer = PacketDataSerializer.createNew(version);
		serializer.writeInt(entityId);
		serializer.writeInt(attributes.length);
		for (Attribute attribute : attributes) {
			serializer.writeString(attribute.key);
			serializer.writeDouble(attribute.value);
			serializer.writeShort(attribute.modifiers.length);
			for (Modifier modifier : attribute.modifiers) {
				serializer.writeUUID(modifier.uuid);
				serializer.writeDouble(modifier.amount);
				serializer.writeByte(modifier.operation);
			}
		}
		return Collections.singletonList(new PacketData(ClientBoundPacket.PLAY_ENTITY_ATTRIBUTES_ID, serializer));
	}

}
