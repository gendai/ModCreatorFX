package ShovelMod.init;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraft.item.Item;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.Minecraft;
import ShovelMod.items.Rainbow;
import ShovelMod.ShovelMod;
import ShovelMod.Reference;

public class ShovelModItems {
public static Item rainbow;

public static void init(ShovelMod mod){
rainbow = new Rainbow(mod).setUnlocalizedName("Rainbow");
}

public static void register( ){
GameRegistry.registerItem(rainbow,rainbow.getUnlocalizedName().substring(5));
}

public static void registerRenders( ){
registerRender(rainbow);
}

public static void registerRender(Item item){
Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":"+item.getUnlocalizedName().substring(5), "inventory"));
}

}