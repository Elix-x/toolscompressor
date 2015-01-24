package code.elix_x.mods.toolscompressor.renderer;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import code.elix_x.mods.toolscompressor.models.ModelToolsCompressor;
import code.elix_x.mods.toolscompressor.tileentities.TileEntityToolsCompressor;

public class RenderToolsCompressor extends TileEntitySpecialRenderer{

	public static ResourceLocation texture = new ResourceLocation("toolscompressor:textures/models/toolscompressor.png");

	private ModelToolsCompressor model = new ModelToolsCompressor();

	private RenderItem itemrenderer;

	public RenderToolsCompressor(){
		itemrenderer = new RenderItem();
	}

	public void renderTileEntityAt(TileEntityToolsCompressor tileentity, double x, double y, double z, float f) {

		
		
		GL11.glPushMatrix();

		GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
		GL11.glRotatef(180, 0f, 0f, 1f);

		this.bindTexture(texture);

		GL11.glPushMatrix();

		this.model.render(0.0625f);

		GL11.glPopMatrix();
		
		/*GL11.glPushMatrix();
		
		GL11.glTranslatef((float)x, (float)y, (float)z);
		EntityItem item = new EntityItem(tileentity.getWorldObj());
		GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
		item.setEntityItemStack(tileentity.getStackInSlot(1));
		itemrenderer.doRender(item, x, y, z, 0f, 0f);
		
		GL11.glPopMatrix();*/

		/*GL11.glPushMatrix();



		GL11.glPopMatrix();*/


		/*for(int i = 1; i < 9; i++){
			if(tileentity.getStackInSlot(i) == null)
					continue;
			EntityItem item = new EntityItem(tileentity.getWorldObj());
			item.setEntityItemStack(tileentity.getStackInSlot(i));

			RenderItem renderer = new RenderItem();

			GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
			GL11.glRotatef(180, 0f, 0f, 1f);
			GL11.glPushMatrix();
			renderer.doRender(item, x, y + 20.0d, z, 0, 0);
			GL11.glPopMatrix();
		}*/

	/*	GL11.glPushMatrix();
		GL11.glDisable(2896);
		GL11.glTranslatef((float)x, (float)y, (float)z);
//		item.hoverStart = 0.0F;
		for(int i = 1; i < 9; i++){
			System.out.println("item n" + i + " : " + tileentity.getStackInSlot(i).getDisplayName());
			if(tileentity.getStackInSlot(i) == null)
				continue;
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x, (float)y, (float)z);
			EntityItem item = new EntityItem(tileentity.getWorldObj());
			GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
			item.setEntityItemStack(tileentity.getStackInSlot(i));				
			this.itemrenderer.doRender(item, x, y + 20.0d, z, 0, 0);
			GL11.glPopMatrix();
		}
		GL11.glEnable(2896);
		GL11.glPopMatrix();*/
		
		
		
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
		this.renderTileEntityAt((TileEntityToolsCompressor) tileentity, x, y, z, f);
	}

	/** Here starts animation part**/

	public void frameDown(){
		for(float i = 8; i < 13; i++){
			model.frame1.rotationPointY = i;
			model.frame2.rotationPointY = i;
			model.frame3.rotationPointY = i;
			model.frame4.rotationPointY = i;
			model.grabber1.rotationPointY = i;
			model.grabber2.rotationPointY = i;
			model.grabberhand1.rotationPointY = i;
			model.grabberhand2.rotationPointY = i;
		}
	}

	public void frameUp(){
		for(float i = 12; i > 7; i--){
			model.frame1.rotationPointY = i;
			model.frame2.rotationPointY = i;
			model.frame3.rotationPointY = i;
			model.frame4.rotationPointY = i;
			model.grabber1.rotationPointY = i;
			model.grabber2.rotationPointY = i;
			model.grabberhand1.rotationPointY = i;
			model.grabberhand2.rotationPointY = i;
		}
	}

	public void moveHandTo(float x, float y){

	}

}