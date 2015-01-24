package code.elix_x.api.toolscompressor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import cpw.mods.fml.common.eventhandler.Event;

public class ValidatingFuelEvent extends Event {

	private static Map<Item, Integer> fuelValues = new HashMap();

	public ValidatingFuelEvent(Map<Item, Integer> values) {
		fuelValues = values;
	}

	public void addfuel(Item item, int value){
		if(!fuelValues.containsKey(item))
			fuelValues.put(item, value);
	}
	
	public void removefuel(Item item){
		if(fuelValues.containsKey(item))
			fuelValues.remove(item);
	}
	
}
