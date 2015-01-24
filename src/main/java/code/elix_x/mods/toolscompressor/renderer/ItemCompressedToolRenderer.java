package code.elix_x.mods.toolscompressor.renderer;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;

import code.elix_x.mods.toolscompressor.items.ItemCompressedTools;
import cpw.mods.fml.client.FMLClientHandler;

public class ItemCompressedToolRenderer implements IItemRenderer {

	ItemRenderer renderer;
	public ItemCompressedToolRenderer() {
		renderer = new ItemRenderer(Minecraft.getMinecraft());
	}


	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}


	@Override
	public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data) {
		if(type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON){
			EntityLivingBase entity = (EntityLivingBase) data[1];
			if(type == IItemRenderer.ItemRenderType.EQUIPPED)
				GL11.glTranslated(1, 0, 0.5);
			if(itemstack.getItemDamage() == 0){
				for(int i = 1; i < 10; i++){
					ItemStack item = ItemCompressedTools.getToolFromMeta(itemstack, i);
					if(item != null){
						if(MinecraftForgeClient.getItemRenderer(item, type) != null)
							ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
						else
							renderer.renderItem(entity, item, 0, type);
						GL11.glTranslated(-0.1, 0, 0);
					}
				}
			} else {
				ItemStack item = ItemCompressedTools.getCurrentTool(itemstack);
				if(item != null){
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
			}
		}
		if(type == IItemRenderer.ItemRenderType.ENTITY){
			EntityItem entity = (EntityItem) data[1];
			if(itemstack.getItemDamage() == 0){
				for(int i = 1; i < 10; i++){
					ItemStack item = ItemCompressedTools.getToolFromMeta(itemstack, i);
					if(item != null){
						entity.setEntityItemStack(item);
						if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
							RenderManager.instance.renderEntitySimple(entity, 1);
						GL11.glTranslated(-0.1, 0, 0);
					}
				}
			} else {
				ItemStack item = ItemCompressedTools.getCurrentTool(itemstack);
				if(item != null){
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
			}
			entity.setEntityItemStack(itemstack);
		}
		if(type == IItemRenderer.ItemRenderType.INVENTORY){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer; 
			GL11.glTranslated(0.5, 0, 0);
			if(itemstack.getItemDamage() == 0){
				for(int i = 1; i < 10; i++){
					ItemStack item = ItemCompressedTools.getToolFromMeta(itemstack, i);
					if(item != null){
						if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
							renderer.renderItem(player, item, 0, type);	
						GL11.glTranslated(-0.1, 0, 0);
					}
				}
			} else {
				ItemStack item = ItemCompressedTools.getCurrentTool(itemstack);
				if(item != null){
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);	
				}
			}
		}
	}

	/*@Override
	public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data) {
		((ItemCompressedTools) itemstack.getItem()).readFromNBT(itemstack);
		if(type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON){
			EntityLivingBase entity = (EntityLivingBase) data[1];
			if(type == IItemRenderer.ItemRenderType.EQUIPPED)
				GL11.glTranslated(1, 0, 0.5);
			ItemStack item;
			if(itemstack.getItemDamage() == 0){
				if((item = ((ItemCombinedTools) itemstack.getItem()).getSword()) != null){
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getPickaxe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getShovel()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getAxe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getHoe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get6thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get7thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get8thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get9thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(MinecraftForgeClient.getItemRenderer(item, type) != null)
						ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
					else
						renderer.renderItem(entity, item, 0, type);
				}
			}
			if(itemstack.getItemDamage() == 1 && (item = ((ItemCombinedTools) itemstack.getItem()).getSword()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 2 && (item = ((ItemCombinedTools) itemstack.getItem()).getPickaxe()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 3 && (item = ((ItemCombinedTools) itemstack.getItem()).getShovel()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 4 && (item = ((ItemCombinedTools) itemstack.getItem()).getAxe()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 5 && (item = ((ItemCombinedTools) itemstack.getItem()).getHoe()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 6 && (item = ((ItemCombinedTools) itemstack.getItem()).get6thItem()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 7 && (item = ((ItemCombinedTools) itemstack.getItem()).get7thItem()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 8 && (item = ((ItemCombinedTools) itemstack.getItem()).get8thItem()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
			if(itemstack.getItemDamage() == 9 && (item = ((ItemCombinedTools) itemstack.getItem()).get9thItem()) != null){
				if(MinecraftForgeClient.getItemRenderer(item, type) != null)
					ForgeHooksClient.renderEquippedItem(type, MinecraftForgeClient.getItemRenderer(item, type), (RenderBlocks) data[0], entity, item);
				else
					renderer.renderItem(entity, item, 0, type);
			}
		}
		if(type == IItemRenderer.ItemRenderType.ENTITY){
			EntityItem entity = (EntityItem) data[1];
			ItemStack item;
			if(itemstack.getItemDamage() == 0){
				if((item = ((ItemCombinedTools) itemstack.getItem()).getSword()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getPickaxe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getShovel()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getAxe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getHoe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get6thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get7thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get8thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get9thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					entity.setEntityItemStack(item);
					if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
						RenderManager.instance.renderEntitySimple(entity, 1);
				}
			}
			if(itemstack.getItemDamage() == 1 && (item = ((ItemCombinedTools) itemstack.getItem()).getSword()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 2 && (item = ((ItemCombinedTools) itemstack.getItem()).getPickaxe()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 3 && (item = ((ItemCombinedTools) itemstack.getItem()).getShovel()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 4 && (item = ((ItemCombinedTools) itemstack.getItem()).getAxe()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 5 && (item = ((ItemCombinedTools) itemstack.getItem()).getHoe()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 6 && (item = ((ItemCombinedTools) itemstack.getItem()).get6thItem()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 7 && (item = ((ItemCombinedTools) itemstack.getItem()).get7thItem()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 8 && (item = ((ItemCombinedTools) itemstack.getItem()).get8thItem()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			if(itemstack.getItemDamage() == 9 && (item = ((ItemCombinedTools) itemstack.getItem()).get9thItem()) != null){
				entity.setEntityItemStack(item);
				if(!ForgeHooksClient.renderEntityItem(entity, item, 0, 0, new Random(), FMLClientHandler.instance().getClient().renderEngine, (RenderBlocks) data[0], 1))
					RenderManager.instance.renderEntitySimple(entity, 1);
			}
			entity.setEntityItemStack(itemstack);
		}
		if(type == IItemRenderer.ItemRenderType.INVENTORY){
			EntityPlayer player = Minecraft.getMinecraft().thePlayer; 
			GL11.glTranslated(0.5, 0, 0);
			ItemStack item;
			if(itemstack.getItemDamage() == 0){
				if((item = ((ItemCombinedTools) itemstack.getItem()).getSword()) != null){
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);	
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getPickaxe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getShovel()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getAxe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).getHoe()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get6thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get7thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get8thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
				if((item = ((ItemCombinedTools) itemstack.getItem()).get9thItem()) != null){
					GL11.glTranslated(-0.1, 0, 0);
					if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
						renderer.renderItem(player, item, 0, type);
				}
			}
			if(itemstack.getItemDamage() == 1 && (item = ((ItemCombinedTools) itemstack.getItem()).getSword()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 2 && (item = ((ItemCombinedTools) itemstack.getItem()).getPickaxe()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 3 && (item = ((ItemCombinedTools) itemstack.getItem()).getShovel()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 4 && (item = ((ItemCombinedTools) itemstack.getItem()).getAxe()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 5 && (item = ((ItemCombinedTools) itemstack.getItem()).getHoe()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 6 && (item = ((ItemCombinedTools) itemstack.getItem()).get6thItem()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 7 && (item = ((ItemCombinedTools) itemstack.getItem()).get7thItem()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 8 && (item = ((ItemCombinedTools) itemstack.getItem()).get8thItem()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
			if(itemstack.getItemDamage() == 9 && (item = ((ItemCombinedTools) itemstack.getItem()).get9thItem()) != null){
				if(!ForgeHooksClient.renderInventoryItem((RenderBlocks) data[0], FMLClientHandler.instance().getClient().renderEngine, item, true, 0, 0, 0))
					renderer.renderItem(player, item, 0, type);
			}
		}
	}*/

}
