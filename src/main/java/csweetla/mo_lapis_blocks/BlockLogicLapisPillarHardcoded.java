package csweetla.mo_lapis_blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import org.jetbrains.annotations.NotNull;

import static csweetla.mo_lapis_blocks.MoLapisBlocks.LOGGER;

public class BlockLogicLapisPillarHardcoded extends BlockLogic {

	public BlockLogicLapisPillarHardcoded(Block<?> block, Material material) {
		super(block, material);
	}

	@Override
	public void onBlockPlacedByMob(World world, int x, int y, int z, @NotNull Side side, Mob mob, double xPlaced, double yPlaced) {
		Axis axis = mob.getPlacementDirection(side, PlacementMode.SIDE).getAxis();

		if (axis == Axis.X) {
			world.setBlockMetadataWithNotify(x, y, z, 5);
			return;
		}
		if (axis == Axis.Z) {
			world.setBlockMetadataWithNotify(x, y, z, 1);
			return;
		}

		Direction hRotation = mob.getHorizontalPlacementDirection(side);

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
