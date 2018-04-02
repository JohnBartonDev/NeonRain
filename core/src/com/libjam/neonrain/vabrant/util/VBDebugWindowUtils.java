package com.libjam.neonrain.vabrant.util;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;

public class VBDebugWindowUtils {
	
	public static int REGISTRY_PORT = 5000;
	private static boolean IS_REGISTRY_RUNNING = false;
	private static Process _clientProcess;
	private Thread _clientThread;
	
	public static void startRegistry() {
		if(!isDesktop()) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Application is not of type Desktop", new IllegalArgumentException());
			return;
		}
		
		try {
			LocateRegistry.createRegistry(REGISTRY_PORT);
			Gdx.app.log(VBDebugWindowUtils.class.getSimpleName(), "Registry Created");
			
			
			IS_REGISTRY_RUNNING = true;
		}
		catch(Exception e) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Make sure only one instance of the main application is running.");
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static void bind(String name, Remote stub) {
		if(!isDesktop()) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Application is not of type Desktop", new IllegalArgumentException());
			return;
		}
		
		if(!IS_REGISTRY_RUNNING) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Registry not started.");
			return;
		}
		
		try {
			Registry registry = LocateRegistry.getRegistry(REGISTRY_PORT);
			registry.rebind(name, stub);
			Gdx.app.log(VBDebugWindowUtils.class.getSimpleName(), stub.getClass().getSimpleName() + " stub bound as \"" + name + "\".");
		}
		catch(Exception e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	public static Remote lookup(String stubName) {
		if(!isDesktop()) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Application is not of type Desktop", new IllegalArgumentException());
			return null;
		}
		
		if(!IS_REGISTRY_RUNNING) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Registry not started.");
			return null;
		}
		
		Remote stub = null;
		try {
			Registry registry = LocateRegistry.getRegistry(REGISTRY_PORT);
			stub = registry.lookup(stubName);
			Gdx.app.log(VBDebugWindowUtils.class.getSimpleName(), "Stub found.");
		}
		catch(Exception e) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Stub not found.");
			e.printStackTrace();
			Gdx.app.exit();
		}
		return stub;
	}
	
	public static void createDebugWindow(Class client) {
		if(!isDesktop()) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Application is not of type Desktop", new IllegalArgumentException());
			return;
		}
		
		if(!IS_REGISTRY_RUNNING) {
			Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "Registry not started.");
			return;
		}
		
		try {
			/*Method[] methods = client.getMethods();
			for(int i = methods.length - 1; i >= 0; i--) {
				if(methods[i].getName().equals("main")) break;
				if(i == 0) {
					Gdx.app.error(VBDebugWindowUtils.class.getSimpleName(), "No Main method found.", new IllegalArgumentException());
					Gdx.app.exit();
				}
			}*/
			createClientProcess(client);
			Gdx.app.log(VBDebugWindowUtils.class.getSimpleName(), "Debug Window Closed.");
		}
		catch(Exception e) {
			e.printStackTrace();
			Gdx.app.exit();
		}
	}
	
	private static boolean isDesktop() {
		ApplicationType type = Gdx.app.getType();
		if(type == ApplicationType.Desktop || type == ApplicationType.HeadlessDesktop) return true;
		return false;
	}
	
	private static int createClientProcess(Class clientClass) throws IOException, InterruptedException {
		String javaHome = System.getProperty("java.home");
        String javaBin = javaHome +  File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = clientClass.getCanonicalName();

        ProcessBuilder builder = new ProcessBuilder( javaBin, "-cp", classpath, className);
        builder.inheritIO();
        
        _clientProcess = builder.start();
        
        Gdx.app.log(VBDebugWindowUtils.class.getSimpleName(), "Debug Window Created.");
        
        _clientProcess.waitFor();
        return _clientProcess.exitValue();
	}
	
	private static class Window implements Runnable{

		@Override
		public void run() {
			
		}
		
	}

}
