package code.elix_x.api.toolscompressor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class Validater {

	private static boolean isValidated = false;
	
	private static ArrayList<Item> swords = new ArrayList();
	private static ArrayList<Item> pickaxes = new ArrayList();
	private static ArrayList<Item> shovels = new ArrayList();
	private static ArrayList<Item> axes = new ArrayList();
	private static ArrayList<Item> hoes = new ArrayList();
	
	private static Map<Item, Integer> fuelValues = new HashMap();
	private static ArrayList<Item> glues = new ArrayList();
	
	/**
	 * Called during post init. Fills list with items, that you will be able to put in special slots. Don't call it before post init, because it loads items only once!
	 */
	public static void validate() {
		if(!isValidated){
			MinecraftForge.EVENT_BUS.post(new ValidatingToolsEvent(swords, pickaxes, shovels, axes, hoes));
			MinecraftForge.EVENT_BUS.post(new ValidatingGlueEvent(glues));
			MinecraftForge.EVENT_BUS.post(new ValidatingFuelEvent(fuelValues));
			isValidated = true;
		}
	}
	
	/**
	 * Check if item is valid for...
	 * @param item = item that we're checking
	 * @return if it is valid
	 */
	public static boolean isSwordValid(Item item){
		return swords.contains(item);
	}
	
	/**
	 * ItemStack version of isItemValid
	 * @param itemstack = itemstack that we're checking
	 * @return if it is valid
	 */
	public static boolean isSwordValid(ItemStack itemstack){
		return isSwordValid(itemstack.getItem());
	}
	
	/**
	 * Check if item is valid for...
	 * @param item = item that we're checking
	 * @return if it is valid
	 */
	public static boolean isPickaxeValid(Item item){
		return pickaxes.contains(item);
	}
	
	/**
	 * ItemStack version of isItemValid
	 * @param itemstack = itemstack that we're checking
	 * @return if it is valid
	 */
	public static boolean isPickaxeValid(ItemStack itemstack){
		return isPickaxeValid(itemstack.getItem());
	}
	
	/**
	 * Check if item is valid for...
	 * @param item = item that we're checking
	 * @return if it is valid
	 */
	public static boolean isShovelValid(Item item){
		return shovels.contains(item);
	}
	
	/**
	 * ItemStack version of isItemValid
	 * @param itemstack = itemstack that we're checking
	 * @return if it is valid
	 */
	public static boolean isShovelValid(ItemStack itemstack){
		return isShovelValid(itemstack.getItem());
	}
	
	/**
	 * Check if item is valid for...
	 * @param item = item that we're checking
	 * @return if it is valid
	 */
	public static boolean isAxeValid(Item item){
		return axes.contains(item);
	}
	
	/**
	 * ItemStack version of isItemValid
	 * @param itemstack = itemstack that we're checking
	 * @return if it is valid
	 */
	public static boolean isAxeValid(ItemStack itemstack){
		return isAxeValid(itemstack.getItem());
	}
	
	/**
	 * Check if item is valid for...
	 * @param item = item that we're checking
	 * @return if it is valid
	 */
	public static boolean isHoeValid(Item item){
		return hoes.contains(item);
	}
	
	/**
	 * ItemStack version of isItemValid
	 * @param itemstack = itemstack that we're checking
	 * @return if it is valid
	 */
	public static boolean isHoeValid(ItemStack itemstack){
		return isHoeValid(itemstack.getItem());
	}
	
	/**
	 * Check if item is valid for glue
	 * @param item = item that we're checking
	 * @return if it is valid
	 */
	public static boolean isGlueValid(Item item){
		return glues.contains(item);
	}
	
	/**
	 * ItemStack version of isItemValid
	 * @param itemstack = itemstack that we're checking
	 * @return if it is valid
	 */
	public static boolean isGlueValid(ItemStack itemstack){
		return isGlueValid(itemstack.getItem());
	}
	
	/**
	 * Check if item is valid fuel
	 * @param item = item that we're checking
	 * @return if it is valid
	 */
	public static boolean isFuelValid(Item item){
		return getSpeedMultiplier(item) > 0;
	}
	
	/**
	 * ItemStack version of isItemValid
	 * @param itemstack = itemstack that we're checking
	 * @return if it is valid
	 */
	public static boolean isFuelValid(ItemStack itemstack){
		return getSpeedMultiplier(itemstack) > 0;
	}

	/**
	 * Getting speed multiplier for compressing
	 * @param item = item that we're checking
	 * @return speed multiplier
	 */
	public static int getSpeedMultiplier(Item item){
		return fuelValues.containsKey(item) ? fuelValues.get(item) : 0;
	}
	
	/**
	 * ItemStack version of getSpeedMultiplier
	 * @param itemstack = itemstack that we're checking
	 * @return speed multiplier
	 */
	public static int getSpeedMultiplier(ItemStack itemstack){
		return getSpeedMultiplier(itemstack.getItem());
	}
	
	@Deprecated
	/**
	 * Getter for list of items, use isItemValid instead
	 * @return copy of list of items
	 */
	public static ArrayList<Item> getValidSwords(){
		ArrayList<Item> i = new ArrayList();
		Iterator<Item> iterator = swords.iterator();
		while(iterator.hasNext()){
			i.add(iterator.next());
		}
		return i;
	}
	
	@Deprecated
	/**
	 * Getter for list of items, use isItemValid instead
	 * @return copy of list of items
	 */
	public static ArrayList<Item> getValidPickaxes(){
		ArrayList<Item> i = new ArrayList();
		Iterator<Item> iterator = pickaxes.iterator();
		while(iterator.hasNext()){
			i.add(iterator.next());
		}
		return i;
	}
	
	@Deprecated
	/**
	 * Getter for list of items, use isItemValid instead
	 * @return copy of list of items
	 */
	public static ArrayList<Item> getValidShovels(){
		ArrayList<Item> i = new ArrayList();
		Iterator<Item> iterator = shovels.iterator();
		while(iterator.hasNext()){
			i.add(iterator.next());
		}
		return i;
	}
	
	@Deprecated
	/**
	 * Getter for list of items, use isItemValid instead
	 * @return copy of list of items
	 */
	public static ArrayList<Item> getValidAxes(){
		ArrayList<Item> i = new ArrayList();
		Iterator<Item> iterator = axes.iterator();
		while(iterator.hasNext()){
			i.add(iterator.next());
		}
		return i;
	}
	
	@Deprecated
	/**
	 * Getter for list of items, use isItemValid instead
	 * @return copy of list of items
	 */
	public static ArrayList<Item> getValidHoes(){
		ArrayList<Item> i = new ArrayList();
		Iterator<Item> iterator = hoes.iterator();
		while(iterator.hasNext()){
			i.add(iterator.next());
		}
		return i;
	}
	
	@Deprecated
	/**
	 * Getter for list of items, use isItemValid instead
	 * @return copy of list of items
	 */
	public static ArrayList<Item> getValidGlues(){
		ArrayList<Item> i = new ArrayList();
		Iterator<Item> iterator = glues.iterator();
		while(iterator.hasNext()){
			i.add(iterator.next());
		}
		return i;
	}
	
	@Deprecated
	/**
	 * Getter for map of fuel values, use isFuelValid and getBurnTime instead
	 * @return copy of map of fuel values
	 */
	public static Map<Item, Integer> getFuelsMap(){
		return new HashMap(fuelValues);
	}
}
