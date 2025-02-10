package csweetla.mo_lapis_blocks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.*;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.core.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class MoLapisBlocks implements ModInitializer {
	public static final String MOD_ID = "mo_lapis_blocks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static TomlConfigHandler tomlConfig;

	static {
		tomlConfig = new TomlConfigHandler(MOD_ID,
			new Toml()
				.addEntry("block-id-start","All mod blocks will have ids starting at this number. Edit this only if you are having id collision with other mods","7870")
		);
	}

	public static Block<BlockLogic> blockGildedLapis;
	public static Block<BlockLogicStairs> blockStairGildedLapis;
	public static Block<BlockLogicSlab> blockSlabGildedLapis;
	public static Block<BlockLogic> blockTrimmedLapis;
	public static Block<BlockLogicSlab> blockSlabTrimmedLapis;
	public static Block<BlockLogicLapisPillarHardcoded> blockPillarLapis;
	public static Block<BlockLogicStairs> blockStairBrickLapis;
	public static Block<BlockLogicSlab> blockSlabBrickLapis;

	@Override
	public void onInitialize() {

		int start_id = tomlConfig.getInt("block-id-start");

		BlockBuilder lapis_builder = new BlockBuilder(MOD_ID)
			.setHardness(3.0f)
			.setResistance(10.0f)
			.addTags(BlockTags.MINEABLE_BY_PICKAXE);

		blockGildedLapis = lapis_builder.clone()
			.build("gilded.lapis", "gilded_lapis",start_id++, b -> new BlockLogic(b, Material.stone));

		blockStairGildedLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.build("stairs.gilded.lapis","gilded_lapis_stairs",start_id++,b -> new BlockLogicStairs(b, blockGildedLapis));

		blockSlabGildedLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockItem(ItemBlockSlab::new)
			.build("slab.gilded.lapis","gilded_lapis_slab",start_id++, b -> new BlockLogicSlab(b,blockGildedLapis));

		blockTrimmedLapis = lapis_builder.clone()
			.build("trimmed.lapis","trimmed_lapis",start_id++, b -> new BlockLogicLapisTrimmedHardcoded(b, Material.stone));

		blockSlabTrimmedLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockItem(ItemBlockSlab::new)
			.build("slab.trimmed.lapis","trimmed_lapis_slab",start_id++, b-> new BlockLogicSlab(b, blockTrimmedLapis));

		blockPillarLapis = lapis_builder.clone()
			.build("pillar.lapis","lapis_pillar",start_id++, b-> new BlockLogicLapisPillarHardcoded(b, Material.stone));


		// ************** LEGACY **********************
		// THESE BLOCKS WERE OFFICIALLY ADDED TO BTA
		// THEY WERE LEFT HERE FOR COMPATIBILITY
		// THEY CANT BE CRAFTED
		// THEY DROP THE NEW VANILLA BLOCKS
		// DO NOT REMOVE FOR 10,0000 years
		blockStairBrickLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.addTags(BlockTags.NOT_IN_CREATIVE_MENU)
			.build("stairs.brick.lapis","brick_lapis_stairs",start_id++,b -> new BlockLogicStairs(b, Blocks.BRICK_LAPIS)
		{
			@Override
			public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
				ItemStack[] result = dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[]{new ItemStack(Blocks.STAIRS_BRICK_LAPIS)} : null;
				if (result != null) {
					for (ItemStack stack : result) {
						stack.setMetadata(meta & 240);
						stack.itemID = Blocks.STAIRS_BRICK_LAPIS.id();
					}
				}

				return result;
			}
		});

		blockSlabBrickLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockItem(ItemBlockSlab::new)
			.addTags(BlockTags.NOT_IN_CREATIVE_MENU)
			.build("slab.brick.lapis", "brick_lapis_slab",start_id,b -> new BlockLogicSlab(b,Blocks.BRICK_LAPIS)
				{
					@Override
					public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
						ItemStack[] result = dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[]{new ItemStack(Blocks.SLAB_BRICK_LAPIS)} : null;
						if (result != null) {
							for (ItemStack stack : result) {
								stack.setMetadata(meta & 240);
								stack.itemID = Blocks.SLAB_BRICK_LAPIS.id();
							}
						}

						return result;
					}
				}
			);
		// ************** /LEGACY **********************

		LOGGER.info(MOD_ID + " initialized");
	}
}
