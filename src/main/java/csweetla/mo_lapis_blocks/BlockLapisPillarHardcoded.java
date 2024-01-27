package csweetla.mo_lapis_blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import turniplabs.halplibe.helper.TextureHelper;

import static csweetla.mo_lapis_blocks.MoLapisBlocks.LOGGER;
import static csweetla.mo_lapis_blocks.MoLapisBlocks.MOD_ID;

public class BlockLapisPillarHardcoded extends Block {

	public BlockLapisPillarHardcoded(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		Axis axis = entity.getPlacementDirection(side, PlacementMode.SIDE).getAxis();

		if (axis == Axis.X) {
			world.setBlockMetadataWithNotify(x, y, z, 5);
			return;
		}
		if (axis == Axis.Z) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
			return;
		}

		Direction hRotation = entity.getHorizontalPlacementDirection(side);

		if (hRotation == Direction.NORTH)
			world.setBlockMetadataWithNotify(x, y, z, 2);

		else if (hRotation == Direction.EAST)
			world.setBlockMetadataWithNotify(x, y, z, 3);
		else if (hRotation == Direction.SOUTH)
			world.setBlockMetadataWithNotify(x, y, z, 4);
		else if (hRotation == Direction.WEST)
			world.setBlockMetadataWithNotify(x, y, z, 0);
		else {

			LOGGER.error("Invalid horizontal placement direction for lapis pillar");
			// default to vertical north, i guess?
			world.setBlockMetadataWithNotify(x, y, z, 3);
		}
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(Side side, int metadata) {

		if (metadata == 5) {
			// metadata 5 indicates aligned along z axis (east-west)

			if (side == Side.EAST || side == Side.WEST)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_cap.png");

			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_side_r90.png");

		} else if (metadata == 1) {
			// metadata 1 indicates aligned along x axis (north-south)
			if (side == Side.NORTH || side == Side.SOUTH)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_cap.png");

			if (side == Side.TOP || side == Side.BOTTOM)
				return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_side.png");

			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_side_r90.png");
		}

		//otherwise, we are placed vertically.
		if (!(side == Side.TOP || side == Side.BOTTOM)) {
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_side.png");
		}

		// rotate the top/bottom of the pillar

		// NORTH
		if (metadata == 2)
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_cap.png");
		// EAST
		else if (metadata == 3)
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_cap_r90.png");
		// SOUTH
		else if (metadata == 4)
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_cap_r180.png");
		// WEST
		else if (metadata == 0)
			return TextureHelper.getOrCreateBlockTextureIndex(MOD_ID, "lapis_pillar_cap_r270.png");

		return 2;
	}
}
