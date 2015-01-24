package code.elix_x.api.toolscompressor;

import java.util.ArrayList;

import net.minecraft.item.Item;
import cpw.mods.fml.common.eventhandler.Event;

public class ValidatingGlueEvent extends Event {

	private ArrayList<Item> glues = new ArrayList();

	public ValidatingGlueEvent(ArrayList<Item> glue){
		glues = glue;
	}

	public void addGlue(Item item){
		if(!glues.contains(item))
			glues.add(item);
	}
	
	public void removeGlue(Item item){
		if(glues.contains(item))
			glues.remove(item);
	}

}
