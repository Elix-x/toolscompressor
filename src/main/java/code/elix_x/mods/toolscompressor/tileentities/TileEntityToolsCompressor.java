package code.elix_x.mods.toolscompressor.tileentities;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import code.elix_x.api.toolscompressor.Validater;
import code.elix_x.mods.toolscompressor.items.ItemCompressedTools;

public class TileEntityToolsCompressor extends TileEntity implements IInventory{

	private String customName;
	private ItemStack[] slots;

	public int moveTimeTotal;
	public int slimyTimeTotal;
	public int compressTimeTotal;

	public int speed;

	public int moveTime;
	public int slimyTime;
	public int compressTime;

	private boolean isWorking;

	private NBTTagCompound combinedToolData = new NBTTagCompound();

	public TileEntityToolsCompressor(){
		slots = new ItemStack[12];

		moveTimeTotal = 0 * 1000;
		slimyTimeTotal = 0 * 1000;
		compressTimeTotal = 30 * 20 * 1000;

		moveTime = 0;
		slimyTime = 0;
		compressTime = 0;

		speed = 0;
		}

	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);

		nbt.setBoolean("isWorking", isWorking);

		nbt.setInteger("moveTime", moveTime);
		nbt.setInteger("slimyTime", slimyTime);
		nbt.setInteger("compressTime", compressTime);

		nbt.setInteger("moveTimeTotal", moveTimeTotal);
		nbt.setInteger("slimyTimeTotal", slimyTimeTotal);
		nbt.setInteger("compressTimeTotal", compressTimeTotal);

		nbt.setInteger("speed", speed);

		for(int i = 0; i < 12; i ++){
			if(slots[i] != null){
				NBTTagCompound tag = new NBTTagCompound();
				slots[i].writeToNBT(tag);
				nbt.setTag("slot" + i, tag);
			}
		}

		nbt.setTag("combinedToolData", combinedToolData);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);

		isWorking = nbt.getBoolean("isWorking");

		moveTime = nbt.getInteger("moveTime");
		slimyTime = nbt.getInteger("slimyTime");
		compressTime = nbt.getInteger("compressTime");

		moveTimeTotal = nbt.getInteger("moveTimeTotal");
		slimyTimeTotal = nbt.getInteger("slimyTimeTotal");
		compressTimeTotal = nbt.getInteger("compressTimeTotal");

		speed = nbt.getInteger("speed");

		for(int i = 0; i < 12; i ++){
			slots[i] = nbt.hasKey("slot" + i) ? ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("slot" + i)) : null;
		}

		combinedToolData = nbt.getCompoundTag("combinedToolData");
	}

	public void start(){	
		if(isWorking)
			return;
		if(getStackInSlot(10) == null || getStackInSlot(11) == null)
			return;
		if(Validater.getSpeedMultiplier(getStackInSlot(11).getItem()) < 1)	
			return;

		isWorking = true;
		int ta = 0;

		for(int i = 1; i < 10; i++)
			if(getStackInSlot(i) != null)
				ta++;

		moveTimeTotal = 5 * 500 * 100 * ta;
		slimyTimeTotal = 1 * 500 * 100 * ta;
		compressTimeTotal = 3 * 500 * 100 * ta;

		moveTime = 0;
		slimyTime = 0;
		compressTime = 0;

		speed = Validater.getSpeedMultiplier(getStackInSlot(11).getItem()) * 100;

		/*	System.out.println(moveTimeTotal);
		System.out.println(slimyTimeTotal);
		System.out.println(compressTimeTotal);
		System.out.println(speed);*/

		Map<Integer, ItemStack> map = new HashMap();

		for(int i = 1; i < 10; i++){
			if(getStackInSlot(i) != null){
				map.put(i, getStackInSlot(i));
				setInventorySlotContents(i, null);
			}
		}		

		ItemStack glue = getStackInSlot(10);
		if(glue.stackSize < 2)
			glue = null;
		else
			glue.stackSize--;
		setInventorySlotContents(10, glue);

		ItemStack fuel = getStackInSlot(11);
		if(fuel.stackSize < 2)
			fuel = null;
		else
			fuel.stackSize--;
		setInventorySlotContents(11, fuel);

		ItemStack tool = ItemCompressedTools.createNewToolAndSetItems(map);
		tool.writeToNBT(combinedToolData);

		markDirty();
		//		finish();
	}

	public void finish() {
		isWorking = false;

		moveTime = 0;
		slimyTime = 0;
		compressTime = 0;

		moveTimeTotal = 0;
		slimyTimeTotal = 0;
		compressTimeTotal = 0;

		speed = 0;

		setInventorySlotContents(0, ItemStack.loadItemStackFromNBT(combinedToolData));

		//		combinedToolData = new NBTTagCompound();
		markDirty();
	}

	@Override
	public int getSizeInventory() {
		return slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		/*if(slots[slot] == null)
			return null;*/
		return slots[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		if(slots[slot] == null)
			return null;
		ItemStack itemstack = slots[slot].splitStack(amount);
		if(slots[slot].stackSize == 0)
			slots[slot] = null;
		return itemstack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if(slots[slot] != null){
			ItemStack itemstack = slots[slot];
			slots[slot] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		slots[slot] = itemstack;
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
			itemstack.stackSize = getInventoryStackLimit();
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? customName : "container.toolscompressor";
	}

	public void setCustomName(String name){
		customName = name;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.customName != null && this.customName.length() != 0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if(worldObj.getTileEntity(xCoord, yCoord, zCoord) != this)
			return false;
		return player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64;
	}

	public void openInventory() {}
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		if(isWorking)
			return false;

		if(slot == 0)
			return false;

		if(slot == 1){
			return Validater.isSwordValid(itemstack);
		}
		if(slot == 2){
			return Validater.isPickaxeValid(itemstack);
		}
		if(slot == 3){
			return Validater.isShovelValid(itemstack);
		}
		if(slot == 4){
			return Validater.isAxeValid(itemstack);
		}
		if(slot == 5){
			return Validater.isHoeValid(itemstack);
		}
		if(slot == 10){
			return Validater.isGlueValid(itemstack);
		}
		if(slot == 11){
			return Validater.isFuelValid(itemstack);
		}

		return true;
	}

	public int getMovingProgressScaled(int i){
		return moveTimeTotal == 0 ? 0 : moveTime * i / moveTimeTotal;
	}

	public int getSlimyProgressScaled(int i){
		return slimyTimeTotal == 0 ? 0 : slimyTime * i / slimyTimeTotal;
	}

	public int getCompressProgressScaled(int i){
		return compressTimeTotal == 0 ? 0 : compressTime * i / compressTimeTotal;
	}

	public int getBurningScaled(int i){
		return (compressTimeTotal + slimyTimeTotal + moveTimeTotal) == 0 ? 0 : (compressTime + slimyTime + moveTime) * i / (compressTimeTotal + slimyTimeTotal + moveTimeTotal); 
	}

	public boolean isMoving(){
		return isWorking && moveTime < moveTimeTotal;
	}

	public boolean isGlueing(){
		return isWorking && slimyTime < slimyTimeTotal;
	}

	public boolean isCompressing(){
		return isWorking && compressTime < compressTimeTotal;
	}

	public boolean finishedMoving(){
		return isWorking && moveTime >= moveTimeTotal;
	}

	public boolean finishedGlueing(){
		return isWorking && slimyTime >= slimyTimeTotal;
	}

	public boolean finishedCompressing(){
		return isWorking && compressTime >= compressTimeTotal;
	}

	public boolean isWorking(){
		return isWorking;
	}

	public void setWorking(boolean b){
		isWorking = b;
	}

	public void updateEntity(){
		if(!worldObj.isRemote){
			if(isWorking){
				Random random = worldObj.rand;
				if(!finishedMoving()){
					moveTime += speed;
				} else {
					if(!finishedGlueing()){
						slimyTime += speed;
					} else {
						if(!finishedCompressing()){
							compressTime += speed;
						} else {
							finish();
						}
					}
				}
			}
		}
		if(isWorking){
			Random random = worldObj.rand;
			if(!finishedMoving()){
				for(int i = 0; i < 51; i++){
					double d0 = (double)((float)xCoord + 0.25 + random.nextFloat() * 0.5F);
					double d1 = (double)((float)yCoord + 1.1F + random.nextFloat() * 0.6F);
					double d2 = (double)((float)zCoord + 0.25 + random.nextFloat() * 0.5F);
					worldObj.spawnParticle("cloud", d0, d1, d2, 0.0D, 0.0D, 0.0D);
				}
			} else {
				if(!finishedGlueing()){
					for(int i = 0; i < 101; i++){
						double d0 = (double)((float)xCoord + 0.25 + random.nextFloat() * 0.5F);
						double d1 = (double)((float)yCoord + 0.7F + random.nextFloat() * 0.6F);
						double d2 = (double)((float)zCoord + 0.25 + random.nextFloat() * 0.5F);
						worldObj.spawnParticle("spell", d0, d1, d2, 0.0D, 0.0D, 0.0D);
					}
				} else {
					if(!finishedCompressing()){
						for(int i = 0; i < 101; i++){
							double d0 = (double)((float)xCoord + 0.4F + random.nextFloat() * 0.2F);
							double d1 = (double)((float)yCoord + 0.7F + random.nextFloat() * 0.3F);
							double d2 = (double)((float)zCoord + 0.4F + random.nextFloat() * 0.2F);
							worldObj.spawnParticle("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
						}

						for(int i = 0; i < 101; i++){
							double d0 = (double)((float)xCoord + 0.4F + random.nextFloat() * 0.2F);
							double d1 = (double)((float)yCoord - 0.7F - random.nextFloat() * 0.3F);
							double d2 = (double)((float)zCoord + 0.4F + random.nextFloat() * 0.2F);
							worldObj.spawnParticle("smoke", d0, d1, d2, -0.0D, -0.0D, -0.0D);
						}
					}
				}
			}
		}
	}

}
