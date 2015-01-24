package code.elix_x.mods.toolscompressor.events;

import code.elix_x.api.toolscompressor.ValidatingToolsEvent;
import net.minecraft.init.Items;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ValidatingVanillaToolsEvent {

	public ValidatingVanillaToolsEvent(){
		
	}
	
	@SubscribeEvent
	public void addVanillaTools(ValidatingToolsEvent event){
		event.addSword(Items.wooden_sword);
		event.addSword(Items.stone_sword);
		event.addSword(Items.iron_sword);
		event.addSword(Items.golden_sword);
		event.addSword(Items.diamond_sword);
		
		event.addPickaxe(Items.wooden_pickaxe);
		event.addPickaxe(Items.stone_pickaxe);
		event.addPickaxe(Items.iron_pickaxe);
		event.addPickaxe(Items.golden_pickaxe);
		event.addPickaxe(Items.diamond_pickaxe);
		
		event.addShovel(Items.wooden_shovel);
		event.addShovel(Items.stone_shovel);
		event.addShovel(Items.iron_shovel);
		event.addShovel(Items.golden_shovel);
		event.addShovel(Items.diamond_shovel);
		
		event.addAxe(Items.wooden_axe);
		event.addAxe(Items.stone_axe);
		event.addAxe(Items.iron_axe);
		event.addAxe(Items.golden_axe);
		event.addAxe(Items.diamond_axe);
		
		event.addHoe(Items.wooden_hoe);
		event.addHoe(Items.stone_hoe);
		event.addHoe(Items.iron_hoe);
		event.addHoe(Items.golden_hoe);
		event.addHoe(Items.diamond_hoe);
	}
}
