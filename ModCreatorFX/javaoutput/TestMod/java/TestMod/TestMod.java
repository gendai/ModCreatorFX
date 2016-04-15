package TestMod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import TestMod.proxy.CommonProxy;
import TestMod.init.TestModItems;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_name, version = Reference.VERSION)
public class TestMod {
@Instance(Reference.MOD_ID)
public static TestMod instance;
@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
public static CommonProxy proxy;

@EventHandler
public void preInit(FMLPreInitializationEvent event){
TestModItems.init(this);
TestModItems.register();
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