package ShovelMod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import ShovelMod.proxy.CommonProxy;
import ShovelMod.init.ShovelModItems;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_name, version = Reference.VERSION)
public class ShovelMod {
@Instance(Reference.MOD_ID)
public static ShovelMod instance;
@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
public static CommonProxy proxy;

@EventHandler
public void preInit(FMLPreInitializationEvent event){
ShovelModItems.init(this);
ShovelModItems.register();
}

@EventHandler
public void init(FMLInitializationEvent event){
proxy.registerRenders();
}

@EventHandler
public void postInit(FMLPostInitializationEvent event){
}

@EventHandler
public void serverLoad(FMLServerStartingEvent event){
}

}