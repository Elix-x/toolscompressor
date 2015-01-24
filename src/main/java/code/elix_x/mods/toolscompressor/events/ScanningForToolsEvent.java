package code.elix_x.mods.toolscompressor.events;

import java.util.Iterator;

import code.elix_x.api.toolscompressor.ValidatingToolsEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ScanningForToolsEvent {

	public ScanningForToolsEvent(){

	}

	@SubscribeEvent
	public void addVanillaTools(ValidatingToolsEvent event){
		Iterator<Item> iterator = Item.itemRegistry.iterator();
		while(iterator.hasNext()){
			Item i = iterator.next();
			if(i instanceof ItemSword){
				event.addSword(i);
			}
			if(i instanceof ItemPickaxe){
				event.addPickaxe(i);
			}
			if(i instanceof ItemSpade){
				event.addShovel(i);
			}
			if(i instanceof ItemAxe){
				event.addAxe(i);
			}
			if(i instanceof ItemHoe){
				event.addHoe(i);
			}
		}
	}

}
