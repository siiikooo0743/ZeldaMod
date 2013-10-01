package com.zeldamod;

import com.zeldamod.block.*;
import com.zeldamod.item.*;
import com.zeldamod.tileEntity.*;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="zeldamod", name="Zelda Mod", version="0.0.1")
@NetworkMod(clientSideRequired=true)
public class ZeldaMod 
{
	public static Block dirtSlab;
	public static Block grassSlab;
	public static Block deepSnow;
	public static Block specialTallGrass;
	public static Block specialGrass;
	public static Block lockedDoor;
	public static Block slopedIceBlock;
	public static Block pushable;
	
	public static Item iceTriangleOrigin;
	public static Item iceTriangleCreator;
	public static Item iceTriangleCreatorTop;
	public static Item slopedIce;
	public static Item key;
	public static Item lockedDoorCreator;
	public static Item metaTester;

	
	@Instance
	public static ZeldaMod instance;

	@SidedProxy(clientSide="com.zeldamod.client.ClientProxy", serverSide="com.zeldamod.CommonProxy")
	public static CommonProxy commonproxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		dirtSlab = new BlockDirtSlab(1000, false, Material.ground).setUnlocalizedName("dirtSlab");
		grassSlab = new BlockDirtSlab(1001, false, Material.grass).setUnlocalizedName("grassSlab");
		pushable = new BlockPushable(1002, Material.rock).setUnlocalizedName("pushable");
		slopedIceBlock = new BlockSlopedIce(1003);
		deepSnow = new BlockDeepSnow(1004).setUnlocalizedName("deepSnow");
		specialTallGrass = new BlockSpecialTallGrass(1005).setUnlocalizedName("specialTallGrass");
		specialGrass = new BlockSpecialGrass(1006).setUnlocalizedName("specialGrass");
		lockedDoor = new BlockLockedDoor(1007, Material.iron).setUnlocalizedName("lockedDoor");
		
		
		iceTriangleOrigin = new ItemIceTriangleOrigin(6000).setUnlocalizedName("iceTriangleOrigin");
		iceTriangleCreator = new ItemIceTriangleCreator(6001, false).setUnlocalizedName("iceTriangleCreator");
		iceTriangleCreatorTop = new ItemIceTriangleCreator(6002, true).setUnlocalizedName("iceTriangleCreatorTop");
		slopedIce = new ItemSlopedIce(6003).setUnlocalizedName("slopedIce");
		key = new Item(6004).setUnlocalizedName("key").setCreativeTab(CreativeTabs.tabMisc);
		lockedDoorCreator = new ItemLockedDoor(6005).setUnlocalizedName("lockedDoorCreator").setCreativeTab(CreativeTabs.tabRedstone);
		metaTester = new ItemMetaTester(6006).setUnlocalizedName("metaTester").setCreativeTab(CreativeTabs.tabTools);
		
		LanguageRegistry.addName(dirtSlab, "Grass Slab");
		LanguageRegistry.addName(grassSlab, "Grass Slab");
		LanguageRegistry.addName(deepSnow, "deep Snow");
		LanguageRegistry.addName(lockedDoorCreator, "locked Door");
		LanguageRegistry.addName(metaTester, "metaTester");
		
		//LanguageRegistry.addName(new ItemStack(grassSlab, 1, 1), "Grass Slab");
		
		GameRegistry.registerBlock(deepSnow, "deepSnow");
		GameRegistry.registerBlock(specialTallGrass, "specialTallGrass");
		GameRegistry.registerBlock(specialGrass, "specialGrass");
		GameRegistry.registerBlock(lockedDoor, "lockedDoor");
		GameRegistry.registerBlock(dirtSlab, "dirtSlab");
		GameRegistry.registerBlock(grassSlab, "grassSlab");
		GameRegistry.registerBlock(pushable, "pushable");
		GameRegistry.registerBlock(slopedIceBlock, "slopedIce");
		
		GameRegistry.registerTileEntity(TileEntityPushable.class, "pushable");
		GameRegistry.registerTileEntity(TileEntitySlopedIce.class, "slopedIce");
		
		GameRegistry.registerItem(iceTriangleOrigin, "iceTriangleOrigin");
		GameRegistry.registerItem(iceTriangleCreator, "iceTriangleCreator");
		GameRegistry.registerItem(iceTriangleCreatorTop, "iceTriangleCreatorTop");
		GameRegistry.registerItem(slopedIce, "slopedIceItem");
		GameRegistry.registerItem(key, "key");
		GameRegistry.registerItem(lockedDoorCreator, "lockedDoorCreator");
		GameRegistry.registerItem(metaTester, "metaTester");
		
		
		ClientRegistry.registerTileEntity(TileEntitySlopedIce.class, "slopedIceRender", new TileEntitySlopedIceRenderer() );
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{ }
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{ }
}
