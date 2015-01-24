package code.elix_x.mods.toolscompressor.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.MinecraftForge;
import code.elix_x.api.toolscompressor.Validater;
import code.elix_x.api.toolscompressor.ValidatingFuelEvent;
import code.elix_x.api.toolscompressor.ValidatingGlueEvent;
import code.elix_x.api.toolscompressor.ValidatingToolsEvent;
import code.elix_x.mods.toolscompressor.slots.TCSlot;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;

public class ContainerToolsCompressor extends Container {

	public TileEntityToolsCompressor toolsCompressor;

	private int moveTime;
	private int slimyTime;
	private int compressTime;
	
	private int moveTimeTotal;
	private int slimyTimeTotal;
	private int compressTimeTotal;
	
	private boolean isWorking;

	private int timeMultiplier;

	public ContainerToolsCompressor(InventoryPlayer inventory, TileEntityToolsCompressor tileentity) {
		toolsCompressor = tileentity;

		/*moveTime = 0;
		slimyTime = 0;
		compressTime = 0;*/

		/** output slot **/
		this.addSlotToContainer(new TCSlot(toolsCompressor, 0, 203, 71, "out"));

		/** slots 1-9 **/
		this.addSlotToContainer(new TCSlot(toolsCompressor, 1, 15, 22, "in-1"));
		this.addSlotToContainer(new TCSlot(toolsCompressor, 2, 64, 22, "in-2"));
		this.addSlotToContainer(new TCSlot(toolsCompressor, 3, 113, 22, "in-3"));

		this.addSlotToContainer(new TCSlot(toolsCompressor, 4, 15, 71, "in-4"));
		this.addSlotToContainer(new TCSlot(toolsCompressor, 5, 64, 71, "in-5"));
		this.addSlotToContainer(new TCSlot(toolsCompressor, 6, 113, 71, "in-6"));

		this.addSlotToContainer(new TCSlot(toolsCompressor, 7, 15, 120, "in-7"));
		this.addSlotToContainer(new TCSlot(toolsCompressor, 8, 64, 120, "in-8"));
		this.addSlotToContainer(new TCSlot(toolsCompressor, 9, 113, 120, "in-9"));

		/** slimy slot **/
		this.addSlotToContainer(new TCSlot(toolsCompressor, 10, 154, 31, "in-slime"));
		/** fuel slot **/
		this.addSlotToContainer(new TCSlot(toolsCompressor, 11, 154, 99, "in-fuel"));

		/** inventory **/

		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; j++){
				this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 44 + j * 18, 166 + i * 18));
			}
		}

		/** hot bar **/
		for(int i = 0; i < 9; i++){
			this.addSlotToContainer(new Slot(inventory, i, 44 + i * 18, 224));
		}

	}

	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();

		for (int i = 0; i < this.crafters.size(); ++i)
		{
			ICrafting icrafting = (ICrafting)this.crafters.get(i);

			if (this.moveTime != this.toolsCompressor.moveTime)
			{
				icrafting.sendProgressBarUpdate(this, 0, this.toolsCompressor.moveTime);
			}

			if (this.slimyTime != this.toolsCompressor.slimyTime)
			{
				icrafting.sendProgressBarUpdate(this, 1, this.toolsCompressor.slimyTime);
			}
			
			if (this.compressTime != this.toolsCompressor.compressTime)
			{
				icrafting.sendProgressBarUpdate(this, 2, this.toolsCompressor.compressTime);
			}
			
			if (this.moveTimeTotal != this.toolsCompressor.moveTimeTotal)
			{
				icrafting.sendProgressBarUpdate(this, 3, this.toolsCompressor.moveTimeTotal);
			}

			if (this.slimyTimeTotal != this.toolsCompressor.slimyTimeTotal)
			{
				icrafting.sendProgressBarUpdate(this, 4, this.toolsCompressor.slimyTimeTotal);
			}
			
			if (this.compressTimeTotal != this.toolsCompressor.compressTimeTotal)
			{
				icrafting.sendProgressBarUpdate(this, 5, this.toolsCompressor.compressTimeTotal);
			}
			
			if(this.isWorking != this.toolsCompressor.isWorking()){
				icrafting.sendProgressBarUpdate(this, 100, this.toolsCompressor.isWorking() ? 1 : 0);
			}
		}

		
		this.moveTime = this.toolsCompressor.moveTime;
		this.slimyTime = this.toolsCompressor.slimyTime;
		this.compressTime = this.toolsCompressor.compressTime;
		
		this.moveTimeTotal = this.toolsCompressor.moveTimeTotal;
		this.slimyTimeTotal = this.toolsCompressor.slimyTimeTotal;
		this.compressTimeTotal= this.toolsCompressor.compressTimeTotal;
		
		this.isWorking = this.toolsCompressor.isWorking();
	}
	
	@Override
	public void updateProgressBar(int id, int amount){
		if(id == 0)
			toolsCompressor.moveTime = amount;
		if(id == 1)
			toolsCompressor.slimyTime = amount;
		if(id == 2)
			toolsCompressor.compressTime = amount;
		if(id == 3)
			toolsCompressor.moveTimeTotal = amount;
		if(id == 4)
			toolsCompressor.slimyTimeTotal = amount;
		if(id == 5)
			toolsCompressor.compressTimeTotal = amount;
		
		
		if(id == 100)
			toolsCompressor.setWorking(amount == 1);
	}

	public void addCraftingToCrafters(ICrafting crafting){
		super.addCraftingToCrafters(crafting);
		crafting.sendProgressBarUpdate(this, 0, this.toolsCompressor.moveTime);
		crafting.sendProgressBarUpdate(this, 1, this.toolsCompressor.slimyTime);
		crafting.sendProgressBarUpdate(this, 2, this.toolsCompressor.compressTime);
		crafting.sendProgressBarUpdate(this, 3, this.toolsCompressor.moveTimeTotal);
		crafting.sendProgressBarUpdate(this, 4, this.toolsCompressor.slimyTimeTotal);
		crafting.sendProgressBarUpdate(this, 5, this.toolsCompressor.compressTimeTotal);
		crafting.sendProgressBarUpdate(this, 100, this.toolsCompressor.isWorking() ? 1 : 0);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return toolsCompressor.isUseableByPlayer(player);
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slotId){
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(slotId);
		if(slot != null && slot.getHasStack()){
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if(slotId > 11){
				if(Validater.isSwordValid(itemstack1)){
					if(!mergeItemStack(itemstack1, 1, 2, false)){
						if(!mergeItemStack(itemstack1, 6, 10, false)){
							return null;
						}
					}
				} else if(Validater.isPickaxeValid(itemstack1)){
					if(!mergeItemStack(itemstack1, 2, 3, false)){
						if(!mergeItemStack(itemstack1, 6, 10, false)){
							return null;
						}
					}
				} else if(Validater.isShovelValid(itemstack1)){
					if(!mergeItemStack(itemstack1, 3, 4, false)){
						if(!mergeItemStack(itemstack1, 6, 10, false)){
							return null;
						}
					}
				} else if(Validater.isAxeValid(itemstack1)){
					if(!mergeItemStack(itemstack1, 4, 5, false)){
						if(!mergeItemStack(itemstack1, 6, 10, false)){
							return null;
						}
					}
				} else if(Validater.isHoeValid(itemstack1)){
					if(!mergeItemStack(itemstack1, 5, 6, false)){
						if(!mergeItemStack(itemstack1, 6, 10, false)){
							return null;
						}
					}
				} else if(Validater.isGlueValid(itemstack1)){
					if(!mergeItemStack(itemstack1, 10, 11, false)){
						if(!mergeItemStack(itemstack1, 6, 10, false)){
							return null;
						}
					}
				} else if(Validater.isFuelValid(itemstack1)){
					if(!mergeItemStack(itemstack1, 11, 12, false)){
						if(!mergeItemStack(itemstack1, 6, 10, false)){
							return null;
						}
					}
				} else {
					if(!mergeItemStack(itemstack1, 6, 10, false)){
						return null;
					}
				}
			} else {
				if(!mergeItemStack(itemstack1, 12, inventorySlots.size(), false)){
					return null;
				}
			}

			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack)null);
			}
			else
			{
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
			slot.onSlotChanged();
		}
		return itemstack;
	}

}
