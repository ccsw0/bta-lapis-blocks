package csweetla.mo_lapis_blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.client.render.stitcher.TextureRegistry;

import static csweetla.mo_lapis_blocks.MoLapisBlocks.LOGGER;
import static csweetla.mo_lapis_blocks.MoLapisBlocks.MOD_ID;

public class BlockLapisTrimmedHardcoded extends Block {
	public BlockLapisTrimmedHardcoded(String key, int id, Material material) {
		super(key, id, material);
	}

	@Override
	public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
		Axis axis = entity.getPlacementDirection(side, PlacementMode.SIDE).getAxis();
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
}
