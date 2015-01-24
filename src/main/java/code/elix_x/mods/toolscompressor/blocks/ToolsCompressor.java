package code.elix_x.mods.toolscompressor.blocks;

import java.util.Random;

import code.elix_x.mods.toolscompressor.MainToolsCompressor;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ToolsCompressor extends BlockContainer{

	/*private static ArrayList<Item> swords = new ArrayList();
	private static ArrayList<Item> pickaxes = new ArrayList();
	private static ArrayList<Item> shovels = new ArrayList();
	private static ArrayList<Item> axes = new ArrayList();
	private static ArrayList<Item> hoes = new ArrayList();*/

	private static boolean keepInventory = false;
	private Random random;

	public ToolsCompressor() {
		super(Material.anvil);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setBlockName("toolscompressor");
		this.setBlockTextureName("toolscompressor:toolscompressor");
		this.setHardness(25.0f);
		this.setHarvestLevel("pickaxe", 2);
		this.setResistance(8.0f);
		random = new Random();
	}

	/**
	 * Fires ValidatingToolsEvent and fills lists with valid Items
	 */
	/*public static void fireValidation() {
		MinecraftForge.EVENT_BUS.post(new ValidatingToolsEvent(swords, pickaxes, shovels, axes, hoes));
	}*/

	/*@Override
	public TileEntity createTileEntity(World world, int metdata){
		TileEntityToolsCompressor tileEntity = new TileEntityToolsCompressor();
		return tileEntity;	
	}*/

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityToolsCompressor();	
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ){
		if(world.isRemote){
			return true;
		} else if(!player.isSneaking()) {
			TileEntityToolsCompressor te = (TileEntityToolsCompressor) world.getTileEntity(x, y, z);

			if(te != null){
				FMLNetworkHandler.openGui(player, MainToolsCompressor.instance, MainToolsCompressor.guiIdToolsCompressor, world, x, y, z);
			}

			return true;
		} else {
			return false;
		}
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack itemstack){
		if(itemstack.hasDisplayName()){
			((TileEntityToolsCompressor) world.getTileEntity(x, y, z)).setCustomName(itemstack.getDisplayName());
		}
	}

	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
	{
		return true;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor Block
	 */
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!world.isRemote && isIndirectlyPowered(world, x, y, z))
		{
			if(world.getTileEntity(x, y, z) instanceof TileEntityToolsCompressor)
				((TileEntityToolsCompressor) world.getTileEntity(x, y, z)).start();
		}
	}

	/**
	 * Checks if block is powered
	 * @param world = current world
	 * @param x = x position
	 * @param y = y position
	 * @param z = z position
	 * @return true if powered
	 */
	private boolean isIndirectlyPowered(World world, int x, int y, int z)
	{
		return world.getIndirectPowerOutput(x, y - 1, z, 0) ? true : (world.getIndirectPowerOutput(x, y + 1, z, 1) ? true : (world.getIndirectPowerOutput(x, y, z - 1, 2) ? true : (world.getIndirectPowerOutput(x, y, z + 1, 3) ? true : (world.getIndirectPowerOutput(x + 1, y, z, 5) ? true : (world.getIndirectPowerOutput(x - 1, y, z, 4) ? true : (world.getIndirectPowerOutput(x, y, z, 0) ? true : (world.getIndirectPowerOutput(x, y + 2, z, 1) ? true : (world.getIndirectPowerOutput(x, y + 1, z - 1, 2) ? true : (world.getIndirectPowerOutput(x, y + 1, z + 1, 3) ? true : (world.getIndirectPowerOutput(x - 1, y + 1, z, 4) ? true : world.getIndirectPowerOutput(x + 1, y + 1, z, 5)))))))))));
	}

	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
		if(world.getTileEntity(x, y, z) instanceof TileEntityToolsCompressor && ((TileEntityToolsCompressor) world.getTileEntity(x, y, z)).isWorking()){
//			EntityTNTPrimed tnt = new EntityTNTPrimed(world);
			world.newExplosion(null, x, y, z, 25, true, true);
		}
	}
	/**
	 * start of render-thing
	 */

	public int getRenderType(){
		return -1;
	}

	public boolean isOpaqueCube(){
		return false;
	}

	public boolean renderAsNormalBlock(){
		return false;
	}
}
