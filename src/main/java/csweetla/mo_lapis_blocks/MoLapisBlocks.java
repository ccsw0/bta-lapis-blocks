package csweetla.mo_lapis_blocks;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.*;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.block.tag.BlockTags;
import net.minecraft.core.item.block.ItemBlockSlab;
import net.minecraft.client.render.block.model.BlockModelStairs;
import net.minecraft.client.render.block.model.BlockModelSlab;
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

	Block<?> blockGildedLapis;
	Block<?> blockStairGildedLapis;
	Block<?> blockSlabGildedLapis;
	Block<?> blockTrimmedLapis;
	Block<?> blockSlabTrimmedLapis;
	Block<?> blockPillarLapis;

	Block<?> blockStairBrickLapis;
	Block<?> blockSlabBrickLapis;

	@Override
	public void onInitialize() {

		int start_id = tomlConfig.getInt("block-id-start");

		BlockBuilder lapis_builder = new BlockBuilder(MOD_ID)
			.setHardness(3.0f)
			.setResistance(10.0f)
			.addTags(BlockTags.MINEABLE_BY_PICKAXE);

		blockGildedLapis = lapis_builder.clone()
			.setTextures("mo_lapis_blocks:block/gilded_lapis")
			.build("gilded.lapis", "gilded_lapis",start_id++, b -> new BlockLogic(b, Material.stone));

		blockStairGildedLapis = lapis_builder.clone()
			.setTextures("mo_lapis_blocks:block/gilded_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockModel(block -> new BlockModelStairs(block))
			.build("stairs.gilded.lapis","gilded_lapis_stairs",start_id++,b -> new BlockLogicStairs(b, blockGildedLapis));

		blockSlabGildedLapis = lapis_builder.clone()
			.setTextures("mo_lapis_blocks:block/gilded_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockItem(ItemBlockSlab::new)
			.setBlockModel(b -> new BlockModelSlab(b))
			.build("slab.gilded.lapis","gilded_lapis_slab",start_id++, b -> new BlockLogicSlab(b,blockGildedLapis));

		blockTrimmedLapis = lapis_builder.clone()
		    .setBlockModel(block -> new BlockModelLapisTrimmedHardcoded(block))
			.build("trimmed.lapis","trimmed_lapis",start_id++, b -> new BlockLogicLapisTrimmedHardcoded(b, Material.stone));

		blockSlabTrimmedLapis = lapis_builder.clone()
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockItem(ItemBlockSlab::new)
			.setSideTextures("mo_lapis_blocks:block/trimmed_lapis")
			.setTopBottomTextures("mo_lapis_blocks:block/lapis_smooth")
			.setBlockModel(b -> new BlockModelSlab(b))
			.build("slab.trimmed.lapis","trimmed_lapis_slab",start_id++, b-> new BlockLogicSlab(b, blockTrimmedLapis));

		blockPillarLapis = lapis_builder.clone()
		    .setBlockModel(block -> new BlockModelLapisPillarHardcoded(block))
			.build("pillar.lapis","lapis_pillar",start_id++, b-> new BlockLogicLapisPillarHardcoded(b, Material.stone));

		blockStairBrickLapis = lapis_builder.clone()
			.setTextures("minecraft:block/brick_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockModel(block -> new BlockModelStairs(block))
			.build("stairs.brick.lapis","brick_lapis_stairs",start_id++,b -> new BlockLogicStairs(b, Blocks.BRICK_LAPIS));

		blockSlabBrickLapis = lapis_builder.clone()
			.setTextures("minecraft:block/brick_lapis")
			.setUseInternalLight()
			.setVisualUpdateOnMetadata()
			.setBlockItem(ItemBlockSlab::new)
			.setBlockModel(b -> new BlockModelSlab(b))
			.build("slab.brick.lapis", "brick_lapis_slab",start_id,b -> new BlockLogicSlab(b,Blocks.BRICK_LAPIS));

		LOGGER.info(MOD_ID + " initialized");
	}
}
