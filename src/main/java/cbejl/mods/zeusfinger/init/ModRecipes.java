package cbejl.mods.zeusfinger.init;

import net.minecraft.world.item.Items;
import org.zeith.hammerlib.annotations.ProvideRecipes;
import org.zeith.hammerlib.api.IRecipeProvider;
import org.zeith.hammerlib.event.recipe.RegisterRecipesEvent;

@ProvideRecipes
public class ModRecipes implements IRecipeProvider {
    @Override
    public void provideRecipes(RegisterRecipesEvent event) {
        event.shapeless()
                .result(ItemsMI.ZEUS_FINGER)
                .addAll(Items.LIGHTNING_ROD, Items.NETHER_STAR, Items.GOLD_INGOT)
                .register();
    }
}
