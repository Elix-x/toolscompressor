package code.elix_x.mods.toolscompressor.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import code.elix_x.mods.toolscompressor.containers.ContainerToolsCompressor;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;

public class GUIToolsCompressor extends GuiContainer{

	private ResourceLocation texture = new ResourceLocation("toolscompressor:textures/gui/toolscompressor.png");
	private TileEntityToolsCompressor toolsCompressor;

	public GUIToolsCompressor(InventoryPlayer inventory, TileEntityToolsCompressor tileentity) {
		super(new ContainerToolsCompressor(inventory, tileentity));
		this.toolsCompressor = tileentity;
		this.xSize = 247;
		this.ySize = 248;
	}

	protected void drawGuiContainerForegroundLayer(int i, int j){
		String name = this.toolsCompressor.hasCustomInventoryName() ? this.toolsCompressor.getInventoryName() : I18n.format(this.toolsCompressor.getInventoryName());
		this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);

		this.fontRendererObj.drawString(I18n.format("container.inventory"), this.xSize / 2 - this.fontRendererObj.getStringWidth(I18n.format("container.inventory")) / 2, this.ySize - 96, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize, 288, 288);	

		if(this.toolsCompressor.isWorking()){
			int burn = this.toolsCompressor.getBurningScaled(13);
			this.drawTexturedModalRect(guiLeft + 154, guiTop + 83 + burn, 247, burn, 13, 13 - burn, 288, 288);

			int move = this.toolsCompressor.getMovingProgressScaled(24);
			this.drawTexturedModalRect(guiLeft + 141, guiTop + 71, 0, 248, move, 16, 288, 288);
			
			int compress = this.toolsCompressor.getCompressProgressScaled(24);
			this.drawTexturedModalRect(guiLeft + 141 + 24, guiTop + 71, 24, 248, compress, 16, 288, 288);

			int glue = this.toolsCompressor.getSlimyProgressScaled(29);
			this.drawTexturedModalRect(guiLeft + 152, guiTop + 48, 247, 14, 20, glue, 288, 288);

		}
	}

	public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight)
	{
		float f = 1F / (float)textureWidth;
		float f1 = 1F / (float)textureHeight;
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double)(x), (double)(y + height), 0, (double)((float)(u) * f), (double)((float)(v + height) * f1));
		tessellator.addVertexWithUV((double)(x + width), (double)(y + height), 0, (double)((float)(u + width) * f), (double)((float)(v + height) * f1));
		tessellator.addVertexWithUV((double)(x + width), (double)(y), 0, (double)((float)(u + width) * f), (double)((float)(v) * f1));
		tessellator.addVertexWithUV((double)(x), (double)(y), 0, (double)((float)(u) * f), (double)((float)(v) * f1));
		tessellator.draw();
	}

}
