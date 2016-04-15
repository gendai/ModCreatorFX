package TestMod.init;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraft.item.Item;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.Minecraft;
import TestMod.items.AxeRegion;
import TestMod.TestMod;
import TestMod.Reference;

public class TestModItems {
public static Item axeregion;

public static void init(TestMod mod){
axeregion = new AxeRegion(mod).setUnlocalizedName("AxeRegion");
}

public static void register( ){
GameRegistry.registerItem(axeregion,axeregion.getUnlocalizedName().substring(5));
}

public static void registerRenders( ){
registerRender(axeregion);
}

public static void registerRender(Item item){
Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":"+item.getUnlocalizedName().substring(5), "inventory"));
}

}