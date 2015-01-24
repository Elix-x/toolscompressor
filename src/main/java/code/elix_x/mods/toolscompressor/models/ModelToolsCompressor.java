package code.elix_x.mods.toolscompressor.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelToolsCompressor extends ModelBase
{
	//fields
	public ModelRenderer bar21;
	public ModelRenderer bar22;
	public ModelRenderer bar23;
	public ModelRenderer bar24;
	public ModelRenderer downside;
	public ModelRenderer upperside;
	public ModelRenderer pistondown1;
	public ModelRenderer pistondown2;
	public ModelRenderer pistonup1;
	public ModelRenderer pistonup2;
	public ModelRenderer frame1;
	public ModelRenderer frame2;
	public ModelRenderer frame3;
	public ModelRenderer frame4;
	public ModelRenderer grabber2;
	public ModelRenderer grabber1;
	public ModelRenderer grabberhand1;
	public ModelRenderer grabberhand2;

	public ModelToolsCompressor()
	{
		textureWidth = 64;
		textureHeight = 64;

		bar21 = new ModelRenderer(this, 34, 0);
		bar21.addBox(0F, 0F, 0F, 1, 16, 1);
		bar21.setRotationPoint(-8F, 8F, -8F);
		bar21.setTextureSize(64, 64);
		bar21.mirror = true;
		setRotation(bar21, 0F, 0F, 0F);
		bar22 = new ModelRenderer(this, 34, 0);
		bar22.addBox(0F, 0F, 0F, 1, 16, 1);
		bar22.setRotationPoint(7F, 8F, -8F);
		bar22.setTextureSize(64, 64);
		bar22.mirror = true;
		setRotation(bar22, 0F, 0F, 0F);
		bar23 = new ModelRenderer(this, 34, 0);
		bar23.addBox(0F, 0F, 0F, 1, 16, 1);
		bar23.setRotationPoint(-8F, 8F, 7F);
		bar23.setTextureSize(64, 64);
		bar23.mirror = true;
		setRotation(bar23, 0F, 0F, 0F);
		bar24 = new ModelRenderer(this, 34, 0);
		bar24.addBox(0F, 0F, 0F, 1, 16, 1);
		bar24.setRotationPoint(7F, 8F, 7F);
		bar24.setTextureSize(64, 64);
		bar24.mirror = true;
		setRotation(bar24, 0F, 0F, 0F);
		downside = new ModelRenderer(this, 0, 19);
		downside.addBox(0F, 0F, 0F, 16, 2, 16);
		downside.setRotationPoint(-8F, 22F, -8F);
		downside.setTextureSize(64, 64);
		downside.mirror = true;
		setRotation(downside, 0F, 0F, 0F);
		upperside = new ModelRenderer(this, 0, 19);
		upperside.addBox(0F, 0F, 0F, 16, 2, 16);
		upperside.setRotationPoint(-8F, 8F, -8F);
		upperside.setTextureSize(64, 64);
		upperside.mirror = true;
		setRotation(upperside, 0F, 0F, 0F);
		pistondown1 = new ModelRenderer(this, 38, 0);
		pistondown1.addBox(0F, -3F, 0F, 4, 3, 4);
		pistondown1.setRotationPoint(-2F, 23F, -2F);
		pistondown1.setTextureSize(64, 64);
		pistondown1.mirror = true;
		setRotation(pistondown1, 0F, 0F, 0F);
		pistondown2 = new ModelRenderer(this, 0, 37);
		pistondown2.addBox(0F, -1F, 0F, 14, 1, 14);
		pistondown2.setRotationPoint(-7F, 21F, -7F);
		pistondown2.setTextureSize(64, 64);
		pistondown2.mirror = true;
		setRotation(pistondown2, 0F, 0F, 0F);
		pistonup1 = new ModelRenderer(this, 38, 0);
		pistonup1.addBox(0F, 0F, 0F, 4, 3, 4);
		pistonup1.setRotationPoint(-2F, 9F, -2F);
		pistonup1.setTextureSize(64, 64);
		pistonup1.mirror = true;
		setRotation(pistonup1, 0F, 0F, 0F);
		pistonup2 = new ModelRenderer(this, 0, 37);
		pistonup2.addBox(0F, 0F, 0F, 14, 1, 14);
		pistonup2.setRotationPoint(-7F, 11F, -7F);
		pistonup2.setTextureSize(64, 64);
		pistonup2.mirror = true;
		setRotation(pistonup2, 0F, 0F, 0F);
		frame1 = new ModelRenderer(this, 0, 0);
		frame1.addBox(0F, 0F, 0F, 16, 1, 1);
		frame1.setRotationPoint(-8F, 8F, -8F);
		frame1.setTextureSize(64, 64);
		frame1.mirror = true;
		setRotation(frame1, 0F, 0F, 0F);
		frame2 = new ModelRenderer(this, 0, 2);
		frame2.addBox(0F, 0F, 0F, 1, 1, 16);
		frame2.setRotationPoint(-8F, 8F, -8F);
		frame2.setTextureSize(64, 64);
		frame2.mirror = true;
		setRotation(frame2, 0F, 0F, 0F);
		frame3 = new ModelRenderer(this, 0, 2);
		frame3.addBox(15F, 0F, 0F, 1, 1, 16);
		frame3.setRotationPoint(-8F, 8F, -8F);
		frame3.setTextureSize(64, 64);
		frame3.mirror = true;
		setRotation(frame3, 0F, 0F, 0F);
		frame4 = new ModelRenderer(this, 0, 0);
		frame4.addBox(0F, 0F, 15F, 16, 1, 1);
		frame4.setRotationPoint(-8F, 8F, -8F);
		frame4.setTextureSize(64, 64);
		frame4.mirror = true;
		setRotation(frame4, 0F, 0F, 0F);
		grabber2 = new ModelRenderer(this, 0, 2);
		grabber2.addBox(0F, 0F, 0F, 1, 1, 16);
		grabber2.setRotationPoint(-8F, 8F, -8F);
		grabber2.setTextureSize(64, 64);
		grabber2.mirror = true;
		setRotation(grabber2, 0F, 0F, 0F);
		grabber1 = new ModelRenderer(this, 0, 0);
		grabber1.addBox(0F, 0F, 0F, 16, 1, 1);
		grabber1.setRotationPoint(-8F, 8F, -8F);
		grabber1.setTextureSize(64, 64);
		grabber1.mirror = true;
		setRotation(grabber1, 0F, 0F, 0F);
		grabberhand1 = new ModelRenderer(this, 54, 0);
		grabberhand1.addBox(0F, 0F, 0F, 1, 2, 1);
		grabberhand1.setRotationPoint(-8F, 8F, -8F);
		grabberhand1.setTextureSize(64, 64);
		grabberhand1.mirror = true;
		setRotation(grabberhand1, 0F, 0F, 0F);
		grabberhand2 = new ModelRenderer(this, 59, 0);
		grabberhand2.addBox(0F, 2F, 0F, 1, 1, 1);
		grabberhand2.setRotationPoint(-8F, 8F, -8F);
		grabberhand2.setTextureSize(64, 64);
		grabberhand2.mirror = true;
		setRotation(grabberhand2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		render(f5);
	}

	public void render(float f) {
		bar21.render(f);
		bar22.render(f);
		bar23.render(f);
		bar24.render(f);
		downside.render(f);
		upperside.render(f);
		pistondown1.render(f);
		pistondown2.render(f);
		pistonup1.render(f);
		pistonup2.render(f);
		frame1.render(f);
		frame2.render(f);
		frame3.render(f);
		frame4.render(f);
		grabber2.render(f);
		grabber1.render(f);
		grabberhand1.render(f);
		grabberhand2.render(f);		
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
