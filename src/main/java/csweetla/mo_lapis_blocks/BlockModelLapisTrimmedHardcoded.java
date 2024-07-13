package csweetla.mo_lapis_blocks;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;

public class BlockModelLapisTrimmedHardcoded<T extends Block> extends BlockModelStandard<T> {
	public BlockModelLapisTrimmedHardcoded(Block b) {
		super(b);
	}

	public static IconCoordinate[] textures = new IconCoordinate[] {
        TextureRegistry.getTexture("mo_lapis_blocks:block/lapis_smooth"),
        TextureRegistry.getTexture("mo_lapis_blocks:block/trimmed_lapis"),
        TextureRegistry.getTexture("mo_lapis_blocks:block/trimmed_lapis_r90"),
        TextureRegistry.getTexture("mo_lapis_blocks:block/trimmed_lapis_r180"),
        TextureRegistry.getTexture("mo_lapis_blocks:block/trimmed_lapis_r270"),
    };

	@Override
	public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int metadata) {
		if (metadata == 1) {
			// metadata 1 indicates placed on bottom
			if (side == Side.TOP || side == Side.BOTTOM)
				return textures[0];
			return textures[3];

		} else if (metadata == 0) {
			// metadata 0 indicates placed top
			if (side == Side.TOP || side == Side.BOTTOM)
				return textures[0];
			return textures[1];

		} else if (metadata == 2) {
			// metadata 2 indicates placed north
			if (side == Side.NORTH || side == Side.SOUTH)
				return textures[0];
			if (side == Side.TOP || side == Side.BOTTOM)
				return textures[1];
			if (side == Side.EAST)
				return textures[2];

			return textures[4];

		} else if (metadata == 3) {
			// metadata 3 indicates placed south
			if (side == Side.NORTH || side == Side.SOUTH)
				return textures[0];
			if (side == Side.EAST)
				return textures[4];
			if (side == Side.WEST)
				return textures[2];

			return textures[3];
		} else if (metadata == 4) {
			// metadata 4 indicates placed East
			if (side == Side.EAST || side == Side.WEST)
				return textures[0];
			if (side == Side.TOP || side == Side.BOTTOM)
				return textures[2];
			if (side == Side.NORTH)
				return textures[4];

			return textures[2];

		} else if (metadata == 5) {
			// metadata 5 indicates placed West
			if (side == Side.EAST || side == Side.WEST)
				return textures[0];
			if (side == Side.NORTH)
				return textures[2];
			if (side == Side.SOUTH)
				return textures[4];

			return textures[4];
		}

		return textures[0];
	}
}
