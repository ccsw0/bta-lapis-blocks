package csweetla.mo_lapis_blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import net.minecraft.client.render.stitcher.TextureRegistry;

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
}
