package code.elix_x.mods.toolscompressor.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import code.elix_x.mods.toolscompressor.MainToolsCompressor;
import code.elix_x.mods.toolscompressor.clientserver.OnKeyOnCTMessage;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class ToolsCompressorKeyRegistry {

	private static final String fix = "Compressed tool fix key";
	private static final String mode = "Compressed tool mode change key";

	private static final int keyValueFix = Keyboard.KEY_F;
	private static final int keyValueMode = Keyboard.KEY_M;	

	public static KeyBinding keyFix;
	public static KeyBinding keyMode;

	public ToolsCompressorKeyRegistry() {
		keyFix = new KeyBinding(fix, keyValueFix, "Compressed tools");
		keyMode = new KeyBinding(mode, keyValueMode, "Compressed tools");

		ClientRegistry.registerKeyBinding(keyFix);
		ClientRegistry.registerKeyBinding(keyMode);
	}

	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (!FMLClientHandler.instance().isGUIOpen(GuiChat.class)) {
			if(keyFix.isPressed()) {
				if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null)
					if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() == MainToolsCompressor.compressedTool)
						MainToolsCompressor.net.sendToServer(new OnKeyOnCTMessage(Minecraft.getMinecraft().thePlayer, true, -1));
			}

			if(keyMode.isPressed()) {
				if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem() != null)
					if(Minecraft.getMinecraft().thePlayer.getCurrentEquippedItem().getItem() == MainToolsCompressor.compressedTool){
						int m = -1;

						if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0)){
							m = 0;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD1)){
							m = 1;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2)){
							m = 2;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3)){
							m = 3;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4)){
							m = 4;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5)){
							m = 5;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6)){
							m = 6;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD7)){
							m = 7;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8)){
							m = 8;
						} else if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9)){
							m = 9;
						}

						MainToolsCompressor.net.sendToServer(new OnKeyOnCTMessage(Minecraft.getMinecraft().thePlayer, keyFix.isPressed(), m));
					}
			}
		}
	}


}
