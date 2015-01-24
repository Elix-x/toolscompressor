package code.elix_x.mods.toolscompressor.slots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import code.elix_x.api.toolscompressor.Validater;
import code.elix_x.api.toolscompressor.ValidatingFuelEvent;
import code.elix_x.api.toolscompressor.ValidatingGlueEvent;
import code.elix_x.api.toolscompressor.ValidatingToolsEvent;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TCSlot extends Slot {

	private String name;
	private TileEntityToolsCompressor compressor;
	
	public static IIcon sword;
	public static IIcon pickaxe;
	public static IIcon shovel;
	public static IIcon axe;
	public static IIcon hoe;
	public static IIcon glue;
	public static IIcon fuel;

	public TCSlot(IInventory inventory, int i, int j, int k) {
		super(inventory, i, j, k);
	}

	public TCSlot(TileEntityToolsCompressor inventory, int i, int j, int k, String n) {
		this(inventory, i, j, k);
		name = n;
		compressor = inventory;
	}

	public boolean isItemValid(ItemStack itemstack){
		if(compressor.isWorking()){
			return false;
		}
		if(name == "out"){
			return false;
		}
		if(name == "in-1"){
			return Validater.isSwordValid(itemstack);
		}
		if(name == "in-2"){
			return Validater.isPickaxeValid(itemstack);
		}
		if(name == "in-3"){
			return Validater.isShovelValid(itemstack);
		}
		if(name == "in-4"){
			return Validater.isAxeValid(itemstack);
		}
		if(name == "in-5"){
			return Validater.isHoeValid(itemstack);
		}
		if(name == "in-slime"){
			return Validater.isGlueValid(itemstack);
		}
		if(name == "in-coal"){
			return Validater.isFuelValid(itemstack);
		}
		return true;
	}

	/*@Override
    @SideOnly(Side.CLIENT)
    public ResourceLocation getBackgroundIconTexture()
    {
    	if(name == "in-1"){
    		return new ResourceLocation("toolscompressor", "/textures/gui/sword");
    	}
    	if(name == "in-2"){
    		return new ResourceLocation("toolscompressor", "/textures/gui/pickaxe");
    	}
    	if(name == "in-3"){
    		return new ResourceLocation("toolscompressor", "/textures/gui/shovel");
    	}
    	if(name == "in-4"){
    		return new ResourceLocation("toolscompressor", "/textures/gui/axe");
    	}
    	if(name == "in-5"){
    		return new ResourceLocation("toolscompressor", "/textures/gui/hoe");
    	}
    	if(name == "in-slime"){
    		return new ResourceLocation("toolscompressor", "/textures/gui/glue");
    	}
    	if(name == "in-coal"){
    		return new ResourceLocation("toolscompressor", "/textures/gui/fuel");
    	}
    	
        return super.getBackgroundIconTexture();
    }*/
	
	@SideOnly(Side.CLIENT)
    public IIcon getBackgroundIconIndex()
    {
        if(name == "in-1"){
    		return sword;
    	}
    	if(name == "in-2"){
    		return pickaxe;
    	}
    	if(name == "in-3"){
    		return shovel;
    	}
    	if(name == "in-4"){
    		return axe;
    	}
    	if(name == "in-5"){
    		return hoe;
    	}
    	if(name == "in-slime"){
    		return glue;
    	}
    	if(name == "in-fuel"){
    		return fuel;
    	}
    	
        return super.getBackgroundIconIndex();
    }
}
