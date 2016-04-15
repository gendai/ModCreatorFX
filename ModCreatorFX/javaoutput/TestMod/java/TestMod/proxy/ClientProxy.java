package TestMod.proxy;
import TestMod.init.TestModItems;

public class ClientProxy extends CommonProxy {

@Override
public void registerRenders( ){
TestModItems.registerRenders();
}

}