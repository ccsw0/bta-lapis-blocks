package csweetla.mo_lapis_blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

import net.minecraft.client.render.stitcher.TextureRegistry;

public class BlockModelLapisPillarHardcoded<T extends Block> extends BlockModelStandard<T> {
	public BlockModelLapisPillarHardcoded(Block b) {
		super(b);
	}

	public static IconCoordinate textures[] = new IconCoordinate[] {
        TextureRegistry.getTexture("mo_lapis_blocks:block/lapis_pillar_cap"),
		TextureRegistry.getTexture("mo_lapis_blocks:block/lapis_pillar_cap_r90"),
		TextureRegistry.getTexture("mo_lapis_blocks:block/lapis_pillar_cap_r180"),
		TextureRegistry.getTexture("mo_lapis_blocks:block/lapis_pillar_cap_r270"),
        TextureRegistry.getTexture("mo_lapis_blocks:block/lapis_pillar_side"),
        TextureRegistry.getTexture("mo_lapis_blocks:block/lapis_pillar_side_r90"),
	};

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int metadata) {
		if (metadata == 5) {
			// metadata 5 indicates aligned along z axis (east-west)

			if (side == Side.EAST || side == Side.WEST)
				return textures[0];

			return textures[5];

		} else if (metadata == 1) {
			// metadata 1 indicates aligned along x axis (north-south)
			if (side == Side.NORTH || side == Side.SOUTH)
				return textures[0];

			if (side == Side.TOP || side == Side.BOTTOM)
				return textures[4];

			return textures[5];
		}

		//otherwise, we are placed vertically.
		if (!(side == Side.TOP || side == Side.BOTTOM)) {
			return textures[4];
		}

		// rotate the top/bottom of the pillar

		// NORTH
		if (metadata == 2)
			return textures[0];
		// EAST
		else if (metadata == 3)
			return textures[1];
		// SOUTH
		else if (metadata == 4)
			return textures[2];
		// WEST
		else if (metadata == 0)
			return textures[3];

		return textures[0];
	}
}
