package code.elix_x.mods.toolscompressor.events;

import java.util.Iterator;

import code.elix_x.api.toolscompressor.ValidatingFuelEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ScanningForFuelEvent {

	public ScanningForFuelEvent(){

	}

	@SubscribeEvent
	public void addVanillaTools(ValidatingFuelEvent event){
		Iterator<Item> iterator = Item.itemRegistry.iterator();
		while(iterator.hasNext()){
			Item i = iterator.next();
			if(TileEntityFurnace.isItemFuel(new ItemStack(i))){
				event.addfuel(i, TileEntityFurnace.getItemBurnTime(new ItemStack(i)) / 2 / 100);
			}
		}
	}

}
