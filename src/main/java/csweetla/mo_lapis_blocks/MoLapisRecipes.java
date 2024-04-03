package csweetla.mo_lapis_blocks;

import net.minecraft.core.data.DataLoader;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class MoLapisRecipes implements RecipeEntrypoint {

	@Override
	public void onRecipesReady() {
		DataLoader.loadRecipesFromFile("/assets/mo_lapis_blocks/recipes/workbench.json");
	}

	@Override
	public void initNamespaces() {

	}
}
