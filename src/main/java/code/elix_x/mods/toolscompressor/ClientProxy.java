package code.elix_x.mods.toolscompressor;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraftforge.client.MinecraftForgeClient;
import code.elix_x.mods.toolscompressor.blocks.ToolsCompressor;
import code.elix_x.mods.toolscompressor.renderer.ItemCompressedToolRenderer;
import code.elix_x.mods.toolscompressor.renderer.ItemRendererToolsCompressor;
import code.elix_x.mods.toolscompressor.renderer.RenderToolsCompressor;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		// render here
		
//		
		
	}

	public void Init() {
		MinecraftForgeClient.registerItemRenderer(MainToolsCompressor.compressedTool, new ItemCompressedToolRenderer());
		RenderToolsCompressor render = new RenderToolsCompressor();
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityToolsCompressor.class, render);
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MainToolsCompressor.toolscompressor), new ItemRendererToolsCompressor(render, new TileEntityToolsCompressor()));
	}

	public void postInit() {

	}

	public void sendToServer(Packet packet) {

	}

	public void sendToPlayers(Packet packet) {

	}

}
