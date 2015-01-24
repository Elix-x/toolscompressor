package code.elix_x.mods.toolscompressor.clientserver;

import io.netty.buffer.ByteBuf;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import code.elix_x.mods.toolscompressor.items.ItemCompressedTools;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class OnKeyOnCTMessage implements IMessage{

	private EntityPlayer player;
	private boolean fixed;
	private int mode;

	public OnKeyOnCTMessage() {

	}

	public OnKeyOnCTMessage(EntityPlayer p, boolean f, int m) {
		this();
		player = p;
		fixed = f;
		mode = m;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		NBTTagCompound data = ByteBufUtils.readTag(buf);
		player = MinecraftServer.getServer().worldServerForDimension(data.getInteger("dim")).func_152378_a(UUID.fromString(data.getString("player")));
		fixed = data.getBoolean("fixed");
		mode = data.getInteger("mode");
	}

	@Override
	public void toBytes(ByteBuf buf) {
		/*UUID uuid = player.func_146094_a(player.getGameProfile());
		ByteBufUtils.writeUTF8String(buf, player.func_146094_a(player.getGameProfile()).toString());	*/
		NBTTagCompound data = new NBTTagCompound();
		data.setString("player", player.func_146094_a(player.getGameProfile()).toString());
		data.setBoolean("fixed", fixed);
		data.setInteger("mode", mode);
		data.setInteger("dim", player.worldObj.provider.dimensionId);
		ByteBufUtils.writeTag(buf, data);
	}

	
	public static class OnKeyOnCTMessageHandler implements IMessageHandler<OnKeyOnCTMessage, IMessage>{

		@Override
		public OnKeyOnCTMessage onMessage(OnKeyOnCTMessage message, MessageContext ctx) {
			((ItemCompressedTools) message.player.getCurrentEquippedItem().getItem()).onKeyDown(message.player, message.fixed, message.mode);
			return null;
		}

	}

}
