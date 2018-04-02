package com.libjam.neonrain.vabrant.debugwindow;

public class CoreClient {

	ClientListener _listener;
	
	public void setListener(ClientListener listener) {
		System.out.println("set");
		_listener = listener;
	}
	
	public void run() {
		_listener.run();
	}
	
	public void checkProcess() {
		_listener.checkProcess();
	}
	
	public interface ClientListener{
		public void run();
		public void checkProcess();
	}
}
