package code.elix_x.api.toolscompressor;

import java.util.ArrayList;

import net.minecraft.item.Item;
import cpw.mods.fml.common.eventhandler.Event;

public class ValidatingToolsEvent extends Event {

	private ArrayList<Item> swords = new ArrayList();
	private ArrayList<Item> pickaxes = new ArrayList();
	private ArrayList<Item> shovels = new ArrayList();
	private ArrayList<Item> axes = new ArrayList();
	private ArrayList<Item> hoes = new ArrayList();

	/*public ValidatingToolsEvent(ArrayList<ArrayList<Item>> list){
		list.
	}*/

	public ValidatingToolsEvent(ArrayList<Item> sw, ArrayList<Item> p, ArrayList<Item> sh, ArrayList<Item> a, ArrayList<Item> h){
		swords = sw;
		pickaxes = p;
		shovels = sh;
		axes = a;
		hoes = h;
	}

	public void addSword(Item item){
		if(!swords.contains(item))
			swords.add(item);
	}

	public void addPickaxe(Item item){
		if(!pickaxes.contains(item))
			pickaxes.add(item);
	}

	public void addShovel(Item item){
		if(!shovels.contains(item))
			shovels.add(item);
	}

	public void addAxe(Item item){
		if(!axes.contains(item))
			axes.add(item);
	}

	public void addHoe(Item item){
		if(!hoes.contains(item))
			hoes.add(item);
	}
	
	public void removeSword(Item item){
		if(swords.contains(item))
			swords.remove(item);
	}

	public void removePickaxe(Item item){
		if(pickaxes.contains(item))
			pickaxes.remove(item);
	}

	public void removeShovel(Item item){
		if(shovels.contains(item))
			shovels.remove(item);
	}

	public void removeAxe(Item item){
		if(axes.contains(item))
			axes.remove(item);
	}

	public void removeHoe(Item item){
		if(hoes.contains(item))
			hoes.remove(item);
	}
}
