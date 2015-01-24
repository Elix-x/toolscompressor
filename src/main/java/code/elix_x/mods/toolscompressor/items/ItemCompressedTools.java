package code.elix_x.mods.toolscompressor.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.lwjgl.input.Keyboard;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import code.elix_x.api.toolscompressor.ValidatingToolsEvent;
import code.elix_x.mods.toolscompressor.MainToolsCompressor;
import code.elix_x.mods.toolscompressor.slots.TCSlot;

import com.google.common.collect.Multimap;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * New class for item compressed tool, because old one isn't working...
 * Big thanks to forge for itemstack sensitive methods!
 * @author elix_x
 *
 */
public class ItemCompressedTools extends Item{

	public ItemCompressedTools(){
		this.setMaxDamage(9);
		this.setTextureName("toolscompressor:air");
		this.setFull3D();
		this.setHasSubtypes(true);
		this.setUnlocalizedName("combinedtool");
	}

	public void registerIcons(IIconRegister register){
		TCSlot.sword = register.registerIcon("toolscompressor:gui/sword");
		TCSlot.pickaxe = register.registerIcon("toolscompressor:gui/pickaxe");
		TCSlot.shovel = register.registerIcon("toolscompressor:gui/shovel");
		TCSlot.axe = register.registerIcon("toolscompressor:gui/axe");
		TCSlot.hoe = register.registerIcon("toolscompressor:gui/hoe");
		TCSlot.glue = register.registerIcon("toolscompressor:gui/glue");
		TCSlot.fuel = register.registerIcon("toolscompressor:gui/fuel");
	}
	/**
	 * All interaction methods are not dependent of current Item, so they are static.
	 * They are also public for cross modal integration, but when you use them (especially if it's nullifier methods), BE SURE WHAT ARE YOU DOING!
	 */
	/**================================================================= INTERACTION METHODS START =================================================================**/

	/**================================================================= BASIC METHODS START =================================================================**/

	public static ItemStack fixTags(ItemStack itemstack){
		NBTTagCompound tags = itemstack.writeToNBT(new NBTTagCompound());
		if(!tags.hasKey("tag"))
			tags.setTag("tag", new NBTTagCompound());
		if(!tags.getCompoundTag("tag").hasKey("compressedtooldata")){
			tags.getCompoundTag("tag").setTag("compressedtooldata", new NBTTagCompound());
			tags.setTag("tag", tags.getCompoundTag("tag"));
		}

		NBTTagCompound compressedtooldata = tags.getCompoundTag("tag").getCompoundTag("compressedtooldata");
		if(!compressedtooldata.hasKey("sword"))
			compressedtooldata.setTag("sword", new NBTTagCompound());
		if(!compressedtooldata.hasKey("pickaxe"))
			compressedtooldata.setTag("pickaxe", new NBTTagCompound());
		if(!compressedtooldata.hasKey("shovel"))
			compressedtooldata.setTag("shovel", new NBTTagCompound());
		if(!compressedtooldata.hasKey("axe"))
			compressedtooldata.setTag("axe", new NBTTagCompound());
		if(!compressedtooldata.hasKey("hoe"))
			compressedtooldata.setTag("hoe", new NBTTagCompound());
		if(!compressedtooldata.hasKey("6th"))
			compressedtooldata.setTag("6th", new NBTTagCompound());
		if(!compressedtooldata.hasKey("7th"))
			compressedtooldata.setTag("7th", new NBTTagCompound());
		if(!compressedtooldata.hasKey("8th"))
			compressedtooldata.setTag("8th", new NBTTagCompound());
		if(!compressedtooldata.hasKey("9th"))
			compressedtooldata.setTag("9th", new NBTTagCompound());
		if(!compressedtooldata.hasKey("fixed"))
			compressedtooldata.setBoolean("fixed", false);

		tags.getCompoundTag("tag").setTag("compressedtooldata", compressedtooldata);
		tags.setTag("tag", tags.getCompoundTag("tag"));

		itemstack.readFromNBT(tags);
		return itemstack;
	}
	/**
	 * 9 get methods
	 * @param itemstack = itemstack with item compressed tool
	 * @return itemstack that is asked
	 */
	public static ItemStack getSword(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("sword"));
	}

	public static ItemStack getPickaxe(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("pickaxe"));
	}

	public static ItemStack getShovel(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("shovel"));
	}

	public static ItemStack getAxe(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("axe"));
	}

	public static ItemStack getHoe(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("hoe"));
	}

	public static ItemStack get6thItem(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("6th"));
	}

	public static ItemStack get7thItem(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("7th"));
	}

	public static ItemStack get8thItem(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("8th"));
	}

	public static ItemStack get9thItem(ItemStack itemstack){
		fixTags(itemstack);
		return ItemStack.loadItemStackFromNBT(itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("9th"));
	}

	/**
	 * 9 check methods
	 * @param itemstack
	 * @return
	 */
	public static boolean hasSword(ItemStack itemstack){
		fixTags(itemstack);
		return getSword(itemstack) != null;
	}

	public static boolean hasPickaxe(ItemStack itemstack){
		fixTags(itemstack);
		return getPickaxe(itemstack) != null;
	}

	public static boolean hasShovel(ItemStack itemstack){
		fixTags(itemstack);
		return getShovel(itemstack) != null;
	}

	public static boolean hasAxe(ItemStack itemstack){
		fixTags(itemstack);
		return getAxe(itemstack) != null;
	}

	public static boolean hasHoe(ItemStack itemstack){
		fixTags(itemstack);
		return getHoe(itemstack) != null;
	}

	public static boolean has6thItem(ItemStack itemstack){
		fixTags(itemstack);
		return get6thItem(itemstack) != null;
	}

	public static boolean has7thItem(ItemStack itemstack){
		fixTags(itemstack);
		return get7thItem(itemstack) != null;
	}

	public static boolean has8thItem(ItemStack itemstack){
		fixTags(itemstack);
		return get8thItem(itemstack) != null;
	}

	public static boolean has9thItem(ItemStack itemstack){
		fixTags(itemstack);
		return get9thItem(itemstack) != null;
	}

	/**
	 * 9 set methods
	 * @param itemstack = itemstack with item compressed tool
	 * @param tool = itemstack that needs to be placed in asked position
	 * @return updated itemstack
	 */
	public static ItemStack setSword(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifieSword(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("sword"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack setPickaxe(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifiePickaxe(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("pickaxe"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack setShovel(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifieShovel(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("shovel"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack setAxe(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifieAxe(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("axe"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack setHoe(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifieHoe(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("hoe"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack set6thItem(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifieSword(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("6th"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack set7thItem(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifie7thItem(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("7th"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack set8thItem(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifie8thItem(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("8th"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack set9thItem(ItemStack itemstack, ItemStack tool){
		fixTags(itemstack);
		if(tool == null)
			return nullifie9thItem(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		tool.writeToNBT(nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").getCompoundTag("9th"));
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	/**
	 * 9 nullifier methods 
	 * @param itemstack = itemstack with item compressed tool
	 * @return updated itemstack
	 */
	public static ItemStack nullifieSword(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("sword", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifiePickaxe(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("pickaxe", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifieShovel(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("shovel", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifieAxe(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("axe", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifieHoe(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("hoe", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifie6thItem(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("6th", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifie7thItem(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("7th", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifie8thItem(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("8th", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	public static ItemStack nullifie9thItem(ItemStack itemstack){
		fixTags(itemstack);
		NBTTagCompound nbt = itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setTag("9th", new NBTTagCompound());
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
		return itemstack;
	}

	/**
	 * Getter for mode fixation
	 * @param itemstack = itemstack that we are checking
	 * @return is current mode fixed
	 */
	public static boolean isFixed(ItemStack itemstack){
		fixTags(itemstack);
		return itemstack.writeToNBT(new NBTTagCompound()).getCompoundTag("tag").getCompoundTag("compressedtooldata").getBoolean("fixed");
	}

	public static void setFixed(ItemStack itemstack, boolean fix){
		fixTags(itemstack);
		NBTTagCompound nbt =  itemstack.writeToNBT(new NBTTagCompound());
		nbt.getCompoundTag("tag").getCompoundTag("compressedtooldata").setBoolean("fixed", fix);
		itemstack.readFromNBT(nbt); itemstack.setTagCompound(nbt.getCompoundTag("tag"));
	}

	/**================================================================= BASIC METHODS END =================================================================**/



	/**================================================================= SECONDARY METHODS START =================================================================**/

	/**
	 * Checks if no tool is choose (in that case, metadata of itemstack == 0)
	 * @param itemstack = itemstack to get value from
	 * @return
	 */
	public static boolean isNoneChoosed(ItemStack itemstack){
		return itemstack.getItemDamage() == 0;
	}

	/**
	 * @param itemstack
	 * @return tool currently selected, null if None is choose
	 */
	public static ItemStack getCurrentTool(ItemStack itemstack){
		return getToolFromMeta(itemstack, itemstack.getItemDamage());
	}


	public static ItemStack setCurrentTool(ItemStack itemstack, ItemStack tool) {
		return setToolForMeta(itemstack, tool, itemstack.getItemDamage());
	}

	public static boolean isCurrentToolSomething(ItemStack itemstack){
		return isToolForMetaSomething(itemstack, itemstack.getItemDamage());
	}

	public static boolean isToolForMetaSomething(ItemStack itemstack, int metadata){
		return getToolFromMeta(itemstack, metadata) != null;
	}

	/**
	 * Returns metadata corresponding for tool
	 * @param itemstack = itemstack to scan
	 * @param tool = tool for search
	 * @return metadata with this tool
	 */
	public static int getMetaFromTool(ItemStack itemstack, ItemStack tool){
		if(hasSword(itemstack) && ItemStack.areItemStacksEqual(getSword(itemstack), tool)){
			return 1;
		}
		if(hasPickaxe(itemstack) && ItemStack.areItemStacksEqual(getPickaxe(itemstack), tool)){
			return 2;
		}
		if(hasShovel(itemstack) && ItemStack.areItemStacksEqual(getShovel(itemstack), tool)){
			return 3;
		}
		if(hasAxe(itemstack) && ItemStack.areItemStacksEqual(getAxe(itemstack), tool)){
			return 4;
		}
		if(hasHoe(itemstack) && ItemStack.areItemStacksEqual(getHoe(itemstack), tool)){
			return 5;
		}
		if(has6thItem(itemstack) && ItemStack.areItemStacksEqual(get6thItem(itemstack), tool)){
			return 6;
		}
		if(has7thItem(itemstack) && ItemStack.areItemStacksEqual(get7thItem(itemstack), tool)){
			return 7;
		}
		if(has8thItem(itemstack) && ItemStack.areItemStacksEqual(get8thItem(itemstack), tool)){
			return 8;
		}
		if(has9thItem(itemstack) && ItemStack.areItemStacksEqual(get9thItem(itemstack), tool)){
			return 9;
		}
		return -1;
	}

	public static ItemStack setToolForMeta(ItemStack itemstack, ItemStack tool, int metadata){
		if(metadata == 1)
			return setSword(itemstack, tool);
		if(metadata == 2)
			return setPickaxe(itemstack, tool);
		if(metadata == 3)
			return setShovel(itemstack, tool);
		if(metadata == 4)
			return setAxe(itemstack, tool);
		if(metadata == 5)
			return setHoe(itemstack, tool);
		if(metadata == 6)
			return set6thItem(itemstack, tool);
		if(metadata == 7)
			return set7thItem(itemstack, tool);
		if(metadata == 8)
			return set8thItem(itemstack, tool);
		if(metadata == 9)
			return set9thItem(itemstack, tool);
		return itemstack;
	}

	/**
	 * Returns tool corresponding for metadata
	 * @param itemstack
	 * @param metadata
	 * @return tool found on this metadata
	 */
	public static ItemStack getToolFromMeta(ItemStack itemstack, int metadata){
		if(metadata == 1)
			return getSword(itemstack);
		if(metadata == 2)
			return getPickaxe(itemstack);
		if(metadata == 3)
			return getShovel(itemstack);
		if(metadata == 4)
			return getAxe(itemstack);
		if(metadata == 5)
			return getHoe(itemstack);
		if(metadata == 6)
			return get6thItem(itemstack);
		if(metadata == 7)
			return get7thItem(itemstack);
		if(metadata == 8)
			return get8thItem(itemstack);
		if(metadata == 9)
			return get9thItem(itemstack);
		return null;
	}

	public static boolean hasToolForMeta(ItemStack itemstack, int metadata){
		return getToolFromMeta(itemstack, metadata) != null;
	}

	/**
	 * clear compressed tool data inside itemstack
	 * @param itemstack = itemstack that needs to be cleared
	 * @return cleared itemstack
	 */
	public static ItemStack clearItemStack(ItemStack itemstack){
		nullifieSword(itemstack);
		nullifiePickaxe(itemstack);
		nullifieShovel(itemstack);
		nullifieAxe(itemstack);
		nullifieHoe(itemstack);
		nullifie6thItem(itemstack);
		nullifie7thItem(itemstack);
		nullifie8thItem(itemstack);
		nullifie9thItem(itemstack);
		return itemstack;
	}

	/**================================================================= SECONDARY METHODS END =================================================================**/



	/**================================================================= THIRD LEVEL METHODS START =================================================================**/

	/**
	 * Gets convenient mode for block harvesting.
	 * It also works with tools, tool types from other mods
	 * @param itemstack = itemstack to get data from
	 * @param block = block that will be harvested
	 * @param meta = metadata of block
	 * @return tool that can harvest this block
	 */
	public static ItemStack getConvinientToolForBlock(ItemStack itemstack, Block block, int metadata){
		for(int meta = 1; meta < 10; meta++){
			if(hasToolForMeta(itemstack, meta)){
				ItemStack tool = getToolFromMeta(itemstack, meta);
				if(ForgeHooks.canToolHarvestBlock(block, metadata, tool))
					return tool;
			}
		}
		return null;
	}

	/**
	 * Gets tool with biggest amount of attack damage
	 * @param itemstack = itemstack to scan
	 * @return tool with biggest amount of attack damage
	 */
	public static ItemStack getToolWithMostDamage(ItemStack itemstack){
		ItemStack finaltool = null;
		float damage = 0;
		for(int meta = 1; meta < 10; meta++){
			if(hasToolForMeta(itemstack, meta)){
				ItemStack tool = getToolFromMeta(itemstack, meta);
				/*if(tool.getAttributeModifiers().containsKey(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName())){
					Object[] array = tool.getAttributeModifiers().get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()).toArray();
					for(int i = 0; i < array.length - 1; i++){
						if(array[i] instanceof AttributeModifier){
							if(((AttributeModifier) array[i]).getName() == SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()){
								if(damage < ((AttributeModifier) array[i]).getAmount()){
									damage = (float) ((AttributeModifier) array[i]).getAmount();
									finaltool = tool;
								}
							}	
						}
					}
				}*/
				if(tool.getAttributeModifiers().containsKey(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName())){
					Object[] array = tool.getAttributeModifiers().get(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName()).toArray();
					for(int i = 0; i < array.length; i++){
						//						System.out.println(array[i]);
						//						System.out.println(array[i] instanceof AttributeModifier);
						if(array[i] instanceof AttributeModifier){
							if(((AttributeModifier) array[i]).getName() == "Weapon modifier" || ((AttributeModifier) array[i]).getName() == "Tool modifier"){
								if(damage < ((AttributeModifier) array[i]).getAmount()){
									damage = (float) ((AttributeModifier) array[i]).getAmount();
									finaltool = tool;
								}
							}
						}
						//						System.out.println(damage);
					}
				}
			}
		}
		return finaltool;
	}

	public static void setEnchantementsForCTForMode(ItemStack itemstack, int mode) {
		ItemStack tool = getToolFromMeta(itemstack, mode);
		EnchantmentHelper.setEnchantments(new HashMap(), itemstack);
		if(tool != null && tool.isItemEnchanted()){
			EnchantmentHelper.setEnchantments(EnchantmentHelper.getEnchantments(tool), itemstack);
		}
	}

	/**================================================================= THIRD LEVEL METHODS END =================================================================**/



	/**================================================================= IN-OUT METHODS START =================================================================**/

	/**
	 * Second level method, works same way like setItems(), but requires only Map to fill compressed tool with items, because auto creates new ItemStack
	 * @param map = map of ItemStacks to be placed inside this tool
	 * @return new ItemStack with filled data
	 */
	public static ItemStack createNewToolAndSetItems(Map<Integer, ItemStack> map){
		return setItems(map, new ItemStack(MainToolsCompressor.compressedTool));
	}

	/**
	 * May be called by itself, but can be also called by createNewToolAndSetItems
	 * sets Items and updates itemstack
	 * @param map = map of ItemStacks to be placed inside this tool
	 * @param itemstack = itemstack that needs to be filled/updated
	 * @return updated itemstack
	 */
	public static ItemStack setItems(Map<Integer, ItemStack> map, ItemStack itemstack){
		fixTags(itemstack);
		for(int metadata = 1; metadata < 10; metadata++){
			if(map.containsKey(metadata)){
				setToolForMeta(itemstack, map.get(metadata), metadata);
			} else {
				setToolForMeta(itemstack, null, metadata);
			}
		}
		return itemstack;
	}

	/**
	 * Gets map of tools from itemstack
	 * @param itemstack = itemstack to take data from
	 * @return map of tools inside itemstack
	 */
	public static Map<Integer, ItemStack> getItems(ItemStack itemstack){
		Map<Integer, ItemStack> map = new HashMap();
		for(int metadata = 1; metadata < 10; metadata++){
			if(hasToolForMeta(itemstack, metadata)){
				map.put(metadata, getToolFromMeta(itemstack, metadata));
			}
		}
		return map;
	}

	/**
	 * Called when one of keys is pressed
	 * @param player = player that pressed key
	 * @param fixed = if fixing key is pressed
	 * @param mode = mode that was choosed
	 */
	public void onKeyDown(EntityPlayer player, boolean fixed, int mode) {
		fixTags(player.getCurrentEquippedItem());
		if(fixed){
			setFixed(player.getCurrentEquippedItem(), !isFixed(player.getCurrentEquippedItem()));
		} else {
			player.getCurrentEquippedItem().setItemDamage(mode);
			setEnchantementsForCTForMode(player.getCurrentEquippedItem(), mode);
		}
	}

	/**================================================================= IN-OUT METHODS END =================================================================**/

	/**================================================================= INTERACTION METHODS END =================================================================**/


	/**
	 * Action performing methods, overriding all from Item, to make it possible to work, that have different code 
	 */
	/**================================================================= SPECIAL ACTION METHODS START =================================================================**/

	/** Called by the default implemetation of EntityItem's onUpdate method, allowing for cleaner
	 * control over the update of the item without having to write a subclass.
	 *
	 * @param entityItem The entity Item
	 * @return Return true to skip any further update code.
	 */
	/*public boolean onEntityItemUpdate(EntityItem entityItem)
	{
		return false;
	}*/


	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean f3){
		int a = 0;
		for(int i = 0; i < 10; i ++){
			if(getToolFromMeta(itemstack, i) != null)
				a++;
		}
		list.add(getCurrentTool(itemstack) != null ? ("Current tool is: " + getCurrentTool(itemstack).getDisplayName()) : "Tool not selected");
		list.add(isFixed(itemstack) ? "Current mode is fixed" : "Current mode isn't fixed");
		list.add("Compressed tool contains " + a + " items: ");
		list.add("");
		for(int i = 0; i < 10; i ++){
			if(getToolFromMeta(itemstack, i) != null){
				ItemStack tool = getToolFromMeta(itemstack, i);
				list.add("tool in slot " + i + " : " + tool.getDisplayName());
				if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
					list.add("{");
					List l = tool.getTooltip(player, f3);
					Iterator iterator = l.iterator();
					while(iterator.hasNext()){
						list.add(iterator.next());
					}
					list.add("}");
					list.add("");
				}
			}
		}
		/*if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			tool.getItem().addInformation(tool, p_77624_2_, p_77624_3_, p_77624_4_);
			setCurrentTool(itemstack, tool);
		}*/
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack itemstack, World p_77659_2_, EntityPlayer player){
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			tool = tool.getItem().onItemRightClick(tool, p_77659_2_, player);
			setCurrentTool(itemstack, tool);
			if(player.getItemInUse() != null && player.getItemInUse() == tool){
				player.setItemInUse(itemstack, player.getItemInUseCount());
			}
		}
		return itemstack;
	}

	public ItemStack onEaten(ItemStack itemstack, World p_77654_2_, EntityPlayer player){
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			tool = tool.getItem().onEaten(tool, p_77654_2_, player);
			setCurrentTool(itemstack, tool);
			if(player.getItemInUse() != null && player.getItemInUse() == tool){
				player.setItemInUse(itemstack, player.getItemInUseCount());
			}
		}
		return itemstack;
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	 * update it's contents.
	 */
	public void onUpdate(ItemStack itemstack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_){
		for(int metadata = 1; metadata < 10; metadata++){
			if(getToolFromMeta(itemstack, metadata) != null)
				getToolFromMeta(itemstack, metadata).getItem().onUpdate(getToolFromMeta(itemstack, metadata), p_77663_2_, p_77663_3_, p_77663_4_, p_77663_5_);
		}
	}


	public String getItemStackDisplayName(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack))
			return getCurrentTool(itemstack).getDisplayName();
		return super.getItemStackDisplayName(itemstack);
	}


	/**
	 * Called when a player drops the item into the world,
	 * returning false from this will prevent the item from
	 * being removed from the players inventory and spawning
	 * in the world
	 *
	 * @param player The player that dropped the item
	 * @param item The item stack, before the item is removed.
	 */
	public boolean onDroppedByPlayer(ItemStack itemstack, EntityPlayer player)
	{
		return true;
	}


	/**
	 * ItemStack sensitive version of getItemAttributeModifiers
	 */
	public Multimap getAttributeModifiers(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			Multimap ret = tool.getAttributeModifiers();
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return super.getAttributeModifiers(itemstack);
	}


	/**
	 * Called before a block is broken.  Return true to prevent default block harvesting.
	 *
	 * Note: In SMP, this is called on both client and server sides!
	 *
	 * @param itemstack The current ItemStack
	 * @param X The X Position
	 * @param Y The X Position
	 * @param Z The X Position
	 * @param player The Player that is wielding the item
	 * @return True to prevent harvesting, false to continue as normal
	 */
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
	{
		if(!isFixed(itemstack))
			itemstack.setItemDamage(getMetaFromTool(itemstack, getConvinientToolForBlock(itemstack, player.worldObj.getBlock(X, Y, Z), player.worldObj.getBlockMetadata(X, Y, Z))));

		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().onBlockStartBreak(tool, X, Y, Z, player);
			setCurrentTool(itemstack, tool);
			return ret;
		}

		return false;
	}

	public static class AttackWithCompressedToolEvent {

		public AttackWithCompressedToolEvent(){

		}

		@SubscribeEvent
		public void onAttack(AttackEntityEvent event){
			if(event.entityPlayer.getCurrentEquippedItem() != null){
				if(event.entityPlayer.getCurrentEquippedItem().getItem() == MainToolsCompressor.compressedTool){
					ItemStack itemstack = event.entityPlayer.getCurrentEquippedItem();
					if(!isFixed(itemstack))
						itemstack.setItemDamage(getMetaFromTool(itemstack, getToolWithMostDamage(itemstack)));
				}
			}
		}
	}

	public static class startMineBlockEvent{

		public startMineBlockEvent(){

		}

		@SubscribeEvent
		public void onInterract(PlayerInteractEvent event){
			if(event.action == event.action.LEFT_CLICK_BLOCK){
				if(event.entityPlayer.getCurrentEquippedItem() != null){
					if(event.entityPlayer.getCurrentEquippedItem().getItem() == MainToolsCompressor.compressedTool){
						EntityPlayer player = event.entityPlayer;
						ItemStack itemstack = player.getCurrentEquippedItem();
						if(!isFixed(itemstack))
							itemstack.setItemDamage(getMetaFromTool(itemstack, getConvinientToolForBlock(itemstack, player.worldObj.getBlock(event.x, event.y, event.z), player.worldObj.getBlockMetadata(event.x, event.y, event.z))));
					}
				}
			}
		}
	}

	/**================================================================= SPECIAL ACTION METHODS END =================================================================**/


	/**
	 * Action performing methods, overriding all methods from Item, to make it possible to work, created using same pattern:
	 * 
	 *public toReturn currentMethod(ItemStack itemstack, ...)
	 * if(isCurrentToolSomething(itemstack)){
	 *  ItemStack tool = getCurrentTool(itemstack);
	 *	toReturn ret = tool.getItem().currentMethod(tool, ...)
	 *	setCurrentTool(itemstack, tool);
	 *	return ret;
	 * }
	 * return super.currentMethod(itemstack, ...) (or special value);
	 *}
	 */
	/**================================================================= ACTION METHODS START =================================================================**/

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	public boolean onItemUse(ItemStack itemstack, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().onItemUse(tool, p_77648_2_, p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	public float func_150893_a(ItemStack itemstack, Block p_150893_2_)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			float ret = tool.getItem().func_150893_a(tool, p_150893_2_);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return 1.0F;
	}

	/**
	 * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
	 * the damage on the stack.
	 */
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().hitEntity(tool, p_77644_2_, p_77644_3_);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	public boolean onBlockDestroyed(ItemStack itemstack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().onBlockDestroyed(tool, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Returns true if the item can be used on the given entity, e.g. shears on sheep.
	 */
	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer p_111207_2_, EntityLivingBase p_111207_3_)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().itemInteractionForEntity(tool, p_111207_2_, p_111207_3_);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Returns true if this item should be rotated by 180 degrees around the Y axis when being held in an entities
	 * hands.
	 */
	@SideOnly(Side.CLIENT)
	public boolean shouldRotateAroundWhenRendering()
	{
		return false;
	}  

	/**
	 * ItemStack sensitive version of getContainerItem.
	 * Returns a full ItemStack instance of the result.
	 *
	 * @param itemStack The current ItemStack
	 * @return The resulting ItemStack
	 */
	public ItemStack getContainerItem(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			ItemStack ret = tool.getItem().getContainerItem(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return super.getContainerItem(itemstack);
	}

	/**
	 * ItemStack sensitive version of hasContainerItem
	 * @param stack The current item stack
	 * @return True if this item has a 'container'
	 */
	public boolean hasContainerItem(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().hasContainerItem(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * If this returns true, after a recipe involving this item is crafted the container item will be added to the
	 * player's inventory instead of remaining in the crafting grid.
	 */
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().doesContainerItemLeaveCraftingGrid(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Called when item is crafted/smelted. Used only by maps so far.
	 */
	/*public void onCreated(ItemStack itemstack, World p_77622_2_, EntityPlayer p_77622_3_) {}*/

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			EnumAction ret = tool.getItem().getItemUseAction(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return EnumAction.none;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			int ret = tool.getItem().getMaxItemUseDuration(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return super.getMaxItemUseDuration(itemstack);
	}

	/**
	 * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
	 */
	public void onPlayerStoppedUsing(ItemStack itemstack, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_){
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			tool.getItem().onPlayerStoppedUsing(tool, p_77615_2_, p_77615_3_, p_77615_4_);
			setCurrentTool(itemstack, tool);
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack itemstack, int pass)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().hasEffect(tool, pass);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Return an item rarity from EnumRarity
	 */
	public EnumRarity getRarity(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			EnumRarity ret = tool.getItem().getRarity(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return EnumRarity.uncommon;
	}

	/**
	 * Checks isDamagable and if it cannot be stacked
	 */
	public boolean isItemTool(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().isItemTool(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack itemstack, ItemStack p_82789_2_)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().getIsRepairable(itemstack, p_82789_2_);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * This is called when the item is used, before the block is activated.
	 * @param stack The Item Stack
	 * @param player The Player that used the item
	 * @param world The Current World
	 * @param x Target X Position
	 * @param y Target Y Position
	 * @param z Target Z Position
	 * @param side The side of the target hit
	 * @return Return true to prevent any further processing.
	 */
	public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().onItemUseFirst(tool, player, world, x, y, z, side, hitX, hitY, hitZ);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Metadata-sensitive version of getStrVsBlock
	 * @param itemstack The Item Stack
	 * @param block The block the item is trying to break
	 * @param metadata The items current metadata
	 * @return The damage strength
	 */
	public float getDigSpeed(ItemStack itemstack, Block block, int metadata)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			float ret = tool.getItem().getDigSpeed(tool, block, metadata);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return super.getDigSpeed(itemstack, block, metadata);
	}

	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingTick(ItemStack itemstack, EntityPlayer player, int count)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			tool.getItem().onUsingTick(tool, player, count);
			setCurrentTool(itemstack, tool);
		}
	}

	/**
	 * Called when the player Left Clicks (attacks) an entity.
	 * Processed before damage is done, if return value is true further processing is canceled
	 * and the entity is not attacked.
	 *
	 * @param stack The Item being used
	 * @param player The player that is attacking
	 * @param entity The entity being attacked
	 * @return True to cancel the rest of the interaction.
	 */
	public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().onLeftClickEntity(tool, player, entity);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 *
	 * Should this item, when held, allow sneak-clicks to pass through to the underlying block?
	 *
	 * @param world The world
	 * @param x The X Position
	 * @param y The X Position
	 * @param z The X Position
	 * @param player The Player that is wielding the item
	 * @return
	 */
	public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player)
	{
		ItemStack itemstack = player.getCurrentEquippedItem();
		if(itemstack.getItem() != MainToolsCompressor.compressedTool)
			return false;
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().doesSneakBypassUse(world, x, y, z, player);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Allow or forbid the specific book/item combination as an anvil enchant
	 *
	 * @param stack The item
	 * @param book The book
	 * @return if the enchantment is allowed
	 */
	public boolean isBookEnchantable(ItemStack itemstack, ItemStack book)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().isBookEnchantable(tool, book);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Called when a entity tries to play the 'swing' animation.
	 *
	 * @param entityLiving The entity swinging the item.
	 * @param stack The Item stack
	 * @return True to cancel any further processing by EntityLiving
	 */
	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().onEntitySwing(entityLiving, tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}


	/**
	 * Determines if the durability bar should be rendered for this item.
	 * Defaults to vanilla stack.isDamaged behavior.
	 * But modders can use this for any data they wish.
	 *
	 * @param stack The current Item Stack
	 * @return True if it should render the 'durability' bar.
	 */
	public boolean showDurabilityBar(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().showDurabilityBar(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Queries the percentage of the 'Durability' bar that should be drawn.
	 *
	 * @param stack The current ItemStack
	 * @return 1.0 for 100% 0 for 0%
	 */
	public double getDurabilityForDisplay(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			double ret = tool.getItem().getDurabilityForDisplay(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return super.getDurabilityForDisplay(itemstack);
	}

	/**
	 * ItemStack sensitive version of {@link #canHarvestBlock(Block)}
	 * @param par1Block The block trying to harvest
	 * @param itemStack The itemstack used to harvest the block
	 * @return true if can harvest the block
	 */
	public boolean canHarvestBlock(Block par1Block, ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			boolean ret = tool.getItem().canHarvestBlock(par1Block, tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return false;
	}

	/**
	 * Queries the harvest level of this item stack for the specifred tool class,
	 * Returns -1 if this tool is not of the specified type
	 *
	 * @param stack This item stack instance
	 * @param toolClass Tool Class
	 * @return Harvest level, or -1 if not the specified tool type.
	 */
	public int getHarvestLevel(ItemStack itemstack, String toolClass)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			int ret = tool.getItem().getHarvestLevel(tool, toolClass);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return super.getHarvestLevel(itemstack, toolClass);
	}

	/**
	 * ItemStack sensitive version of getItemEnchantability
	 *
	 * @param stack The ItemStack
	 * @return the item echantability value
	 */
	public int getItemEnchantability(ItemStack itemstack)
	{
		if(isCurrentToolSomething(itemstack)){
			ItemStack tool = getCurrentTool(itemstack);
			int ret = tool.getItem().getItemEnchantability(tool);
			setCurrentTool(itemstack, tool);
			return ret;
		}
		return 0;
	}

	/**================================================================= ACTION METHODS END =================================================================**/

}
