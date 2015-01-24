package code.elix_x.mods.toolscompressor.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import code.elix_x.mods.toolscompressor.MainToolsCompressor;
import code.elix_x.mods.toolscompressor.containers.ContainerToolsCompressor;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);

		if(te != null){
			switch(ID){
			case MainToolsCompressor.guiIdToolsCompressor:
				if(te instanceof TileEntityToolsCompressor){
					return new ContainerToolsCompressor(player.inventory, (TileEntityToolsCompressor) te);
				}
				return null;
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);

		if(te != null){
			switch(ID){
			case MainToolsCompressor.guiIdToolsCompressor:
				if(te instanceof TileEntityToolsCompressor){
					return new GUIToolsCompressor(player.inventory, (TileEntityToolsCompressor) te);
				}
				return null;
			}
		}
		return null;
	}

}
