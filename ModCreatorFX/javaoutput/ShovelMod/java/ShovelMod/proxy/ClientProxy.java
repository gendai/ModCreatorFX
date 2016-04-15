package ShovelMod.proxy;
import ShovelMod.init.ShovelModItems;

public class ClientProxy extends CommonProxy {

@Override
public void registerRenders( ){
ShovelModItems.registerRenders();
}

}