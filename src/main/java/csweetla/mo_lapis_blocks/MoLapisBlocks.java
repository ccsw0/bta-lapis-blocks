package csweetla.mo_lapis_blocks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSlab;
import net.minecraft.core.block.BlockStairs;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.client.render.block.model.BlockModelSlab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import turniplabs.halplibe.helper.BlockBuilder;
import net.minecraft.client.render.stitcher.TextureRegistry;
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

		BlockBuilder lapis_builder = new BlockBuilder(MOD_ID)
			.setHardness(3.0f)
			.setResistance(10.0f)
			.addTags(BlockTags.MINEABLE_BY_PICKAXE);

		blockGildedLapis = lapis_builder.clone()
			.setTextures("mo_lapis_blocks:block/gilded_lapis")
			.build(new Block("gilded.lapis", start_id++, Material.stone));

		blockStairGildedLapis = lapis_builder.clone()
			.setTextures("mo_lapis_blocks:block/gilded_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockModel(block -> new BlockModelStairs(block))
			.build(new BlockStairs(blockGildedLapis, start_id++));

		blockSlabGildedLapis = lapis_builder.clone()
			.setTextures("mo_lapis_blocks:block/gilded_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setItemBlock(ItemBlockSlab::new)
			.setBlockModel(BlockModelSlab::new)
			.build(new BlockSlab(blockGildedLapis, start_id++));

		blockTrimmedLapis = lapis_builder.clone()
		    .setBlockModel(block -> new BlockModelLapisTrimmedHardcoded(block))
			.build(new BlockLapisTrimmedHardcoded("trimmed.lapis", start_id++, Material.stone));

		blockSlabTrimmedLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setItemBlock(ItemBlockSlab::new)
			.setSideTextures("mo_lapis_blocks:block/trimmed_lapis")
			.setTopBottomTextures("mo_lapis_blocks:block/lapis_smooth")
			.setBlockModel(BlockModelSlab::new)
			.build(new BlockSlab(blockTrimmedLapis, start_id++));

		blockPillarLapis = lapis_builder.clone()
		    .setBlockModel(block -> new BlockModelLapisPillarHardcoded(block))
			.build(new BlockLapisPillarHardcoded("pillar.lapis", start_id++, Material.stone));

		blockStairBrickLapis = lapis_builder.clone()
			.setTextures("minecraft:block/brick_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockModel(block -> new BlockModelStairs(block))
			.build(new BlockStairs(Block.brickLapis, start_id++));

		blockSlabBrickLapis = lapis_builder.clone()
			.setTextures("minecraft:block/brick_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setItemBlock(ItemBlockSlab::new)
			.setBlockModel(BlockModelSlab::new)
			.build(new BlockSlab(Block.brickLapis, start_id++));

		LOGGER.info(MOD_ID + " initialized");
	}
}
