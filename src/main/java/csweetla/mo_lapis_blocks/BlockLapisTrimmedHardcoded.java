package csweetla.mo_lapis_blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import turniplabs.halplibe.helper.TextureHelper;

import static csweetla.mo_lapis_blocks.MoLapisBlocks.LOGGER;
import static csweetla.mo_lapis_blocks.MoLapisBlocks.MOD_ID;

public class BlockLapisTrimmedHardcoded extends Block {

	int lapis_block_tex_id = TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_smooth.png");//Block.texCoordToIndex(0, 9);

	public BlockLapisTrimmedHardcoded(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		Axis axis = entity.getPlacementDirection(side, PlacementMode.SIDE).getAxis();
		System.out.println("SIDE: " + side.toString());
		if (axis == Axis.Y) {
			if (side == Side.BOTTOM) {
				world.setBlockMetadataWithNotify(x, y, z, 1);
			} else if (side == Side.TOP) {
				world.setBlockMetadataWithNotify(x, y, z, 0);
			} else {
				LOGGER.error("How did we place on axis Y but not top or bottom???");
			}
		} else if (axis == Axis.Z) {
			if (side == Side.NORTH) {
				world.setBlockMetadataWithNotify(x, y, z, 2);
			} else if (side == Side.SOUTH) {
				world.setBlockMetadataWithNotify(x, y, z, 3);
			} else {
				LOGGER.error("How did we place on axis X but not north or south???");
			}
		} else if (axis == Axis.X) {
			if (side == Side.EAST) {
				world.setBlockMetadataWithNotify(x, y, z, 4);
			} else if (side == Side.WEST) {
				world.setBlockMetadataWithNotify(x, y, z, 5);
			} else {
				LOGGER.error("How did we place on axis X but not north or south???");
			}
		} else {
			LOGGER.error("How did we place with no axis?????");
			world.setBlockMetadataWithNotify(x, y, z, 0);
		}
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(Side side, int metadata) {
		if (metadata == 1) {
			// metadata 1 indicates placed on bottom
			if (side == Side.TOP || side == Side.BOTTOM)
				return lapis_block_tex_id;
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r180.png");

		} else if (metadata == 0) {
			// metadata 0 indicates placed top
			if (side == Side.TOP || side == Side.BOTTOM)
				return lapis_block_tex_id;
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis.png");

		} else if (metadata == 2) {
			// metadata 2 indicates placed north
			if (side == Side.NORTH || side == Side.SOUTH)
				return lapis_block_tex_id;
			if (side == Side.TOP || side == Side.BOTTOM)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis.png");
			if (side == Side.EAST)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r90.png");

			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r270.png");

		} else if (metadata == 3) {
			// metadata 3 indicates placed south
			if (side == Side.NORTH || side == Side.SOUTH)
				return lapis_block_tex_id;
			if (side == Side.EAST)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r270.png");
			if (side == Side.WEST)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r90.png");

			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r180.png");
		} else if (metadata == 4) {
			// metadata 4 indicates placed East
			if (side == Side.EAST || side == Side.WEST)
				return lapis_block_tex_id;
			if (side == Side.TOP || side == Side.BOTTOM)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r90.png");
			if (side == Side.NORTH)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r270.png");

			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r90.png");

		} else if (metadata == 5) {
			// metadata 5 indicates placed West
			if (side == Side.EAST || side == Side.WEST)
				return lapis_block_tex_id;
			if (side == Side.NORTH)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r90.png");
			if (side == Side.SOUTH)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r270.png");

			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "trimmed_lapis_r270.png");
		}

		return 2;
	}
}
