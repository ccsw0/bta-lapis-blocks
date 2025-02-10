package csweetla.mo_lapis_blocks;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.*;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.util.helper.Side;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;
import static csweetla.mo_lapis_blocks.MoLapisBlocks.*;

public class MoLapisModelEntrypoint implements ModelEntrypoint {
	@Override
	public void initBlockModels(BlockModelDispatcher dispatcher) {

		ModelHelper.setBlockModel(blockGildedLapis, () -> {
			BlockModelStandard<?> bm = new BlockModelStandard<>(blockGildedLapis);
			bm.setAllTextures(0,MOD_ID + ":block/gilded_lapis");
			return bm;
		});


		ModelHelper.setBlockModel(blockStairGildedLapis, () -> {
			BlockModelStairs<?> bm = new BlockModelStairs<>(blockStairGildedLapis);
			bm.setAllTextures(0,MOD_ID + ":block/gilded_lapis");
			return bm;
		});

		ModelHelper.setBlockModel(blockSlabGildedLapis, () -> {
			BlockModelSlab<?> bm = new BlockModelSlab<>(blockSlabGildedLapis);
			bm.setAllTextures(0,MOD_ID + ":block/gilded_lapis");
			return bm;
		});

		ModelHelper.setBlockModel(blockTrimmedLapis, () -> {
			BlockModelLapisTrimmedHardcoded<?> bm = new BlockModelLapisTrimmedHardcoded<>(blockTrimmedLapis);
			bm.setAllTextures(0,MOD_ID + ":block/trimmed_lapis");
			return bm;
		});

//		ModelHelper.setBlockModel(blockSlabTrimmedLapis, () -> {
//			BlockModelSlab<?> bm = new BlockModelSlab(blockTrimmedLapis);
//			bm.setAllTextures(0,MOD_ID + ":block/trimmed_lapis");
//			return bm;
//		});

		ModelHelper.setBlockModel(blockSlabTrimmedLapis, () -> {
			BlockModelSlab<?> bm = new BlockModelSlab<>(blockSlabTrimmedLapis);
			bm.setTex(0,MOD_ID + ":block/trimmed_lapis", Side.sides);
			bm.setTex(0,MOD_ID + ":block/lapis_smooth", Side.TOP);
			bm.setTex(0,MOD_ID + ":block/lapis_smooth", Side.BOTTOM);

			return bm;
		});

		ModelHelper.setBlockModel(blockPillarLapis, () -> {
			BlockModelLapisPillarHardcoded<?> bm = new BlockModelLapisPillarHardcoded<>(blockPillarLapis);
			bm.setAllTextures(0,MOD_ID + ":block/lapis_pillar");
			return bm;
		});

		ModelHelper.setBlockModel(blockStairBrickLapis, () -> {
			BlockModelStairs<?> bm = new BlockModelStairs<>(blockStairBrickLapis);
			bm.setAllTextures(0,"minecraft:block/brick_lapis");
			return bm;
		});

		ModelHelper.setBlockModel(blockSlabBrickLapis, () -> {
			BlockModelSlab<?> bm = new BlockModelSlab<>(blockSlabBrickLapis);
			bm.setAllTextures(0,"minecraft:block/brick_lapis");
			return bm;
		});

	}

	@Override
	public void initItemModels(ItemModelDispatcher dispatcher) {

	}

	@Override
	public void initEntityModels(EntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}

	@Override
	public void initBlockColors(BlockColorDispatcher dispatcher) {

	}
}
