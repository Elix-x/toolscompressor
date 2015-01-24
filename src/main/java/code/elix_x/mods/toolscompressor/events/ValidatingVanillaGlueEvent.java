package code.elix_x.mods.toolscompressor.events;

import code.elix_x.api.toolscompressor.ValidatingGlueEvent;
import net.minecraft.init.Items;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ValidatingVanillaGlueEvent {

	public ValidatingVanillaGlueEvent(){

	}

	@SubscribeEvent
	public void addVanillaGlue(ValidatingGlueEvent event){
		event.addGlue(Items.slime_ball);
	}

}
