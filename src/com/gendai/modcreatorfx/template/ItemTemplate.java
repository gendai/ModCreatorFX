package com.gendai.modcreatorfx.template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.gendai.modcreatorfx.codegen.CodeBlock;
import com.gendai.modcreatorfx.codegen.JExpr;
import com.gendai.modcreatorfx.codegen.JavaTypes.ReturnType;
import com.gendai.modcreatorfx.codegen.JavaTypes.Visibility;
import com.gendai.modcreatorfx.gui.ItemInfo;
import com.gendai.modcreatorfx.gui.ModInfo;
import com.gendai.modcreatorfx.gui.Reference;
import com.gendai.modcreatorfx.javagen.BaseGenerator;
import com.gendai.modcreatorfx.javagen.classgen.ClassGen;
import com.gendai.modcreatorfx.javagen.methodgen.MethodDeclarator;
import com.gendai.modcreatorfx.javagen.methodgen.MethodParams;
import com.gendai.modcreatorfx.resources.Resource;

public class ItemTemplate {
	
	ItemInfo item;
	ModInfo mod;
	File javadir;
	File resdir;
	File proxydir;
	File initdir;
	File itemsdir;
	File res;
	File langdir;
	File modeldir;
	
	public ItemTemplate(ModInfo mod,ItemInfo item){
		this.item = item;
		this.mod = mod;
	}
	
	@SuppressWarnings("unchecked")
	private void config(){
		javadir = new File(Reference.outputLocation+mod.getName()+"/java/"+mod.getName());
		javadir.mkdirs();
		resdir = new File(Reference.outputLocation+mod.getName()+"/resources/assets/"+mod.getName().toLowerCase());
		resdir.mkdirs();
		proxydir = new File(javadir+"/proxy");
		proxydir.mkdirs();
		initdir = new File(javadir+"/init");
		initdir.mkdirs();
		itemsdir = new File(javadir+"/items");
		itemsdir.mkdirs();
		langdir = new File(resdir.getPath()+"/lang/en_US.lang");
		langdir.getParentFile().mkdirs();
		modeldir = new File(resdir.getPath()+"/models/item/"+item.getName().toLowerCase()+".json");
		modeldir.getParentFile().mkdirs();
		try {
			langdir.createNewFile();
			FileWriter fw = new FileWriter(langdir.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("item."+item.getName()+".name="+item.getName());
			bw.close();
			
			//modeldir.createNewFile();
			Files.copy(Paths.get(Resource.class.getResource(item.getType().name()+".json").getPath().substring(3)), Paths.get(modeldir.toString()));
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(modeldir.toString()));
			JSONObject jsonObject = (JSONObject) obj;
			JSONObject arr = (JSONObject)jsonObject.get("textures");
			arr.put("layer0", mod.getName().toLowerCase()+":items/"+item.getName().toLowerCase());
			FileWriter file = new FileWriter(modeldir.toString());
			//System.out.println(jsonObject.toString());
			//System.out.println(jsonObject.toString().contains("\\/"));
			String rep = jsonObject.toString().replace("\\/", "/");
			//System.out.println(rep);
			file.write(rep);
			file.flush();
			file.close();
		} catch (IOException | ParseException e1) {
			e1.printStackTrace();
		}
		res = item.getTexturefile();
		Path pathto = Paths.get(resdir.getPath()+"/textures/items/"+item.getName().toLowerCase()+".png");
		pathto.toFile().getParentFile().mkdirs();
		try {
			Files.copy(res.toPath(), pathto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void create() throws IOException{
		config();
		BaseGenerator bg = new BaseGenerator(javadir+"/"+mod.getName()+".java", mod.getName(), mod.getName());
		ClassGen cg = bg.createClass(mod.getName(), Visibility.PUBLIC, "", "", "@Mod(modid = Reference.MOD_ID, name = Reference.MOD_name, version = Reference.VERSION)",mod.getName());
		cg.addImport(mod.getName()+".init."+mod.getName()+"Items");
		cg.addImport(mod.getName()+".proxy.CommonProxy");
		cg.addImport("net.minecraftforge.fml.common.Mod");
		cg.addImport("net.minecraftforge.fml.common.Mod.EventHandler");
		cg.addImport("net.minecraftforge.fml.common.Mod.Instance");
		cg.addImport("net.minecraftforge.fml.common.ModMetadata");
		cg.addImport("net.minecraftforge.fml.common.SidedProxy");
		cg.addImport("net.minecraftforge.fml.common.event.FMLInitializationEvent");
		cg.addImport("net.minecraftforge.fml.common.event.FMLPostInitializationEvent");
		cg.addImport("net.minecraftforge.fml.common.event.FMLPreInitializationEvent");
		cg.addImport("net.minecraftforge.fml.common.event.FMLServerStartingEvent");
		CodeBlock cb = new CodeBlock();
		JExpr expr = new JExpr();
		cb.addExpr(expr.Annotation("@Instance(Reference.MOD_ID)"),true);
		cb.addExpr(expr.declare("public static "+mod.getName(), "instance"),false);
		cb.addExpr(expr.Annotation("@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)"),true);
		cb.addExpr(expr.declare("public static CommonProxy", "proxy"),false);
		cg.addDeclaration(cb);
		MethodParams mp = new MethodParams(1);
		mp.setParamName(new String[]{"event"});
		mp.setParamType(new String[]{"FMLPreInitializationEvent"});
		MethodDeclarator md = new MethodDeclarator("preInit", ReturnType.VOID, mp, Visibility.PUBLIC);
		CodeBlock cbm = new CodeBlock();
		cbm.addExpr(new JExpr().call(mod.getName()+"Items.init(this)"),false);
		cbm.addExpr(new JExpr().call(mod.getName()+"Items.register()"),false);
		cg.addMethod(md, "@EventHandler", cbm);
		mp = new MethodParams(1);
		mp.setParamName(new String[]{"event"});
		mp.setParamType(new String[]{"FMLInitializationEvent"});
		md = new MethodDeclarator("init", ReturnType.VOID, mp, Visibility.PUBLIC);
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().call("proxy.registerRenders()"),false);
		cg.addMethod(md, "@EventHandler", cbm);
		mp = new MethodParams(1);
		mp.setParamName(new String[]{"event"});
		mp.setParamType(new String[]{"FMLPostInitializationEvent"});
		md = new MethodDeclarator("postInit", ReturnType.VOID, mp, Visibility.PUBLIC);
		cbm = new CodeBlock();
		cg.addMethod(md, "@EventHandler", cbm);
		mp = new MethodParams(1);
		mp.setParamName(new String[]{"event"});
		mp.setParamType(new String[]{"FMLServerStartingEvent"});
		md = new MethodDeclarator("serverLoad", ReturnType.VOID, mp, Visibility.PUBLIC);
		cbm = new CodeBlock();
		cg.addMethod(md, "@EventHandler", cbm);
		cg.Build();
		
		bg = new BaseGenerator(javadir+"/Reference.java", "Reference", mod.getName());
		cg = bg.createClass("Reference", Visibility.PUBLIC, "", "", "", mod.getName());
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().ToJExpr("public static final String MOD_ID"),new JExpr().Assig(new JExpr().Variable("\""+mod.getName().toLowerCase()+"\"")));
		cbm.addExpr(new JExpr().ToJExpr("public static final String MOD_name"),new JExpr().Assig(new JExpr().Variable("\""+mod.getName()+"\"")));
		cbm.addExpr(new JExpr().ToJExpr("public static final String VERSION"),new JExpr().Assig(new JExpr().Variable("\""+mod.getVersion()+"\"")));
		cbm.addExpr(new JExpr().ToJExpr("public static final String CLIENT_PROXY_CLASS"),new JExpr().Assig(new JExpr().Variable("\""+mod.getName()+".proxy.ClientProxy\"")));
		cbm.addExpr(new JExpr().ToJExpr("public static final String SERVER_PROXY_CLASS"),new JExpr().Assig(new JExpr().Variable("\""+mod.getName()+".proxy.CommonProxy\"")));
		cg.addDeclaration(cbm);
		cg.Build();
		
		bg = new BaseGenerator(proxydir+"/ClientProxy.java", "ClientProxy", mod.getName());
		cg = bg.createClass("ClientProxy", Visibility.PUBLIC, "CommonProxy", "", "", mod.getName()+".proxy");
		cg.addImport(mod.getName()+".init."+mod.getName()+"Items");
		mp = new MethodParams(0);
		md = new MethodDeclarator("registerRenders", ReturnType.VOID, mp, Visibility.PUBLIC);
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().call(mod.getName()+"Items.registerRenders()"),false);
		cg.addMethod(md, "@Override", cbm);
		cg.Build();
		
		bg = new BaseGenerator(proxydir+"/CommonProxy.java", "CommonProxy", mod.getName());
		cg = bg.createClass("CommonProxy", Visibility.PUBLIC, "", "", "", mod.getName()+".proxy");
		mp = new MethodParams(0);
		md = new MethodDeclarator("registerRenders", ReturnType.VOID, mp, Visibility.PUBLIC);
		cbm = new CodeBlock();
		cg.addMethod(md, "", cbm);
		cg.Build();
		
		bg = new BaseGenerator(initdir+"/"+mod.getName()+"Items.java", mod.getName()+"Items", mod.getName());
		cg = bg.createClass(mod.getName()+"Items", Visibility.PUBLIC, "", "", "", mod.getName()+".init");
		cbm = new CodeBlock();
		cg.addImport(mod.getName()+".Reference");
		cg.addImport(mod.getName()+"."+mod.getName());
		cg.addImport(mod.getName()+".items."+item.getName());
		cg.addImport("net.minecraft.client.Minecraft");
		cg.addImport("net.minecraft.client.resources.model.ModelResourceLocation");
		cg.addImport("net.minecraft.item.Item");
		cg.addImport("net.minecraftforge.fml.common.registry.EntityRegistry");
		cg.addImport("net.minecraftforge.fml.common.registry.GameRegistry");
		cbm.addExpr(new JExpr().declare("public static Item", item.getName().toLowerCase()),false);
		cg.addDeclaration(cbm);
		mp = new MethodParams(1);
		mp.setParamName(new String[]{"mod"});
		mp.setParamType(new String[]{mod.getName()});
		md = new MethodDeclarator("init", ReturnType.VOID, mp, Visibility.PUBLIC_STATIC);
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().Variable(item.getName().toLowerCase()),new JExpr().Assig(new JExpr().Jnew(new JExpr().ToJExpr(item.getName()+"(mod).setUnlocalizedName(\""+item.getName()+"\")"))));
		cg.SetConstructor(md, cbm);
		
		mp = new MethodParams(0);
		md = new MethodDeclarator("register", ReturnType.VOID, mp, Visibility.PUBLIC_STATIC);
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().ToJExpr("GameRegistry.registerItem("+item.getName().toLowerCase()+","+item.getName().toLowerCase()+".getUnlocalizedName().substring(5))"),false);
		cg.addMethod(md, "", cbm);
		
		md = new MethodDeclarator("registerRenders", ReturnType.VOID, mp, Visibility.PUBLIC_STATIC);
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().call("registerRender("+item.getName().toLowerCase()+")"),false);
		cg.addMethod(md, "", cbm);
		
		mp = new MethodParams(1);
		mp.setParamName(new String[]{"item"});
		mp.setParamType(new String[]{"Item"});
		md = new MethodDeclarator("registerRender", ReturnType.VOID, mp, Visibility.PUBLIC_STATIC);
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().ToJExpr("Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + \":\"+item.getUnlocalizedName().substring(5), \"inventory\"))"),false);
		cg.addMethod(md, "", cbm);
		cg.Build();
		
		bg = new BaseGenerator(itemsdir+"/"+item.getName()+".java", item.getName(), mod.getName());
		cg = bg.createClass(item.getName(), Visibility.PUBLIC, "Item", "", "", mod.getName()+".items");
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().declare(mod.getName(), "mod"),false);
		cg.addDeclaration(cbm);
		cg.addImport(mod.getName()+"."+mod.getName());
		cg.addImport("net.minecraft.item.Item");
		mp = new MethodParams(1);
		mp.setParamName(new String[]{"mod"});
		mp.setParamType(new String[]{mod.getName()});
		md = new MethodDeclarator(item.getName(), ReturnType.BLANK, mp, Visibility.PUBLIC);
		cbm = new CodeBlock();
		cbm.addExpr(new JExpr().ToJExpr("this.mod = mod"),false);
		cg.SetConstructor(md, cbm);
		cg.Build();
		/*FileInputStream fileIn = new FileInputStream("./javaoutput/"+mod.getName()+"/"+item.getName()+".ser");
		@SuppressWarnings("resource")
		ObjectInputStream in = new ObjectInputStream(fileIn);
		fileserial.add((FileSerial)in.readObject());*/
	}
}
