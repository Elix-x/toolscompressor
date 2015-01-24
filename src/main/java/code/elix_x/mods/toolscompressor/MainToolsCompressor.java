package code.elix_x.mods.toolscompressor;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import code.elix_x.api.toolscompressor.Validater;
import code.elix_x.mods.toolscompressor.blocks.ToolsCompressor;
import code.elix_x.mods.toolscompressor.clientserver.OnKeyOnCTMessage;
import code.elix_x.mods.toolscompressor.events.ScanningForFuelEvent;
import code.elix_x.mods.toolscompressor.events.ScanningForToolsEvent;
import code.elix_x.mods.toolscompressor.events.ToolsCompressorKeyRegistry;
import code.elix_x.mods.toolscompressor.events.ValidatingVanillaGlueEvent;
import code.elix_x.mods.toolscompressor.events.ValidatingVanillaToolsEvent;
import code.elix_x.mods.toolscompressor.gui.GuiHandler;
import code.elix_x.mods.toolscompressor.items.ItemCompressedTools;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;


@Mod(modid = "toolscompressor", version = "1.0" ,name="Tools compresser")
public class MainToolsCompressor {

	@SidedProxy(clientSide = "code.elix_x.mods.toolscompressor.ClientProxy", serverSide = "code.elix_x.mods.toolscompressor.CommonProxy")
	public static CommonProxy proxy;

	@Mod.Instance("toolscompressor")
	public static MainToolsCompressor instance;
	
	public static SimpleNetworkWrapper net;
	
	public static ItemCompressedTools compressedTool;
	public static ToolsCompressor toolscompressor;
	public static final int guiIdToolsCompressor = 0;

	@EventHandler
	public void preinit(FMLPreInitializationEvent event)
	{ 
		net = NetworkRegistry.INSTANCE.newSimpleChannel("toolscompressormodchannel");
		net.registerMessage(OnKeyOnCTMessage.OnKeyOnCTMessageHandler.class, OnKeyOnCTMessage.class, 0, Side.SERVER);
		proxy.preInit();
	}


	@EventHandler
	public void init(FMLInitializationEvent event)
	{ 
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		compressedTool = new ItemCompressedTools();
		GameRegistry.registerItem(compressedTool, "compressedTool");
		toolscompressor = new ToolsCompressor();
		GameRegistry.registerBlock(toolscompressor, "toolscompressor");
		GameRegistry.registerTileEntity(TileEntityToolsCompressor.class, "TileEntityToolsCompressor");
		GameRegistry.addRecipe(new ItemStack(toolscompressor), new Object[] {"%$%", "& &", "%$%", '%', Blocks.iron_block, '$', Blocks.piston, '&', Blocks.furnace});
		
		MinecraftForge.EVENT_BUS.register(new ValidatingVanillaToolsEvent());
		MinecraftForge.EVENT_BUS.register(new ScanningForToolsEvent());
		
		MinecraftForge.EVENT_BUS.register(new ValidatingVanillaGlueEvent());
		MinecraftForge.EVENT_BUS.register(new ScanningForFuelEvent());
				
		MinecraftForge.EVENT_BUS.register(new ItemCompressedTools.AttackWithCompressedToolEvent());
		MinecraftForge.EVENT_BUS.register(new ItemCompressedTools.startMineBlockEvent());
		
		if(event.getSide() == Side.CLIENT)
			FMLCommonHandler.instance().bus().register(new ToolsCompressorKeyRegistry());
		
		proxy.Init();
	}

	@EventHandler
	public void postinit(FMLPostInitializationEvent event)
	{ 
		Validater.validate();
		proxy.postInit();
	}
	
}
