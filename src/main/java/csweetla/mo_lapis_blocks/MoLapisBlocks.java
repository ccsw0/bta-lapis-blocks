package csweetla.mo_lapis_blocks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.block.model.BlockModelRenderBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.block.BlockStairs;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockSlab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import turniplabs.halplibe.helper.TextureHelper;
import turniplabs.halplibe.util.TomlConfigHandler;
import turniplabs.halplibe.util.toml.Toml;

public class MoLapisBlocks implements ModInitializer {
	public static final String MOD_ID = "mo_lapis_blocks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static TomlConfigHandler tconfig;

	static {
		tconfig = new TomlConfigHandler(MOD_ID,
			new Toml()
				.addEntry("block-id-start","All mod blocks will have ids starting at this number. Edit this only if you are having id collision with other mods","7870")
		);
	}

	Block blockGildedLapis;
	Block blockStairGildedLapis;
	Block blockSlabGildedLapis;
	Block blockTrimmedLapis;
	Block blockSlabTrimmedLapis;
	Block blockPillarLapis;

	Block blockStairBrickLapis;
	Block blockSlabBrickLapis;

	@Override
	public void onInitialize() {

		int start_id = tconfig.getInt("block-id-start");

		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"lapis_pillar_cap.png");
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"lapis_pillar_cap_r90.png");
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"lapis_pillar_cap_r180.png");
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"lapis_pillar_cap_r270.png");

		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"lapis_pillar_side.png");
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"lapis_pillar_side_r90.png");

		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"trimmed_lapis.png");
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"trimmed_lapis_r90.png");
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"trimmed_lapis_r180.png");
		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID,"trimmed_lapis_r270.png");

		TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_smooth.png");

		BlockBuilder lapis_builder = new BlockBuilder(MOD_ID)
			.setHardness(3.0f)
			.setResistance(10.0f)
			.addTags(BlockTags.MINEABLE_BY_PICKAXE);

		blockGildedLapis = lapis_builder.clone()
			.setTextures("gilded_lapis.png")
			.build(new Block("gilded.lapis", start_id++, Material.stone));

		blockStairGildedLapis = lapis_builder.clone()
			.setTextures("gilded_lapis.png")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockModel(new BlockModelRenderBlocks(10))
			.build(new BlockStairs(blockGildedLapis, start_id++));

		blockSlabGildedLapis = lapis_builder.clone()
			.setTextures("gilded_lapis.png")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setItemBlock(ItemBlockSlab::new)
			.build(new BlockSlab(blockGildedLapis, start_id++));

		blockTrimmedLapis = lapis_builder.clone()
			.build(new BlockLapisTrimmedHardcoded("trimmed.lapis", start_id++, Material.stone));

		blockSlabTrimmedLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setItemBlock(ItemBlockSlab::new)
			.build(new BlockSlab(blockTrimmedLapis, start_id++));

		blockPillarLapis = lapis_builder.clone()
			.build(new BlockLapisPillarHardcoded("pillar.lapis", start_id++, Material.stone));

		blockStairBrickLapis = lapis_builder.clone()
			.setTextures(9,8)
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockModel(new BlockModelRenderBlocks(10))
			.build(new BlockStairs(Block.brickLapis, start_id++));

		blockSlabBrickLapis = lapis_builder.clone()
			.setTextures(9,8)
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setItemBlock(ItemBlockSlab::new)
			.build(new BlockSlab(Block.brickLapis, start_id++));

		LOGGER.info(MOD_ID + " initialized");
	}
}
