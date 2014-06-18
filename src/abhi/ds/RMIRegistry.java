package abhi.ds;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RMIRegistry implements Runnable {

	//The registry entries that maintains a list of all the RemoteReference of the Distributed Objects in the System.
	//The Map will be common Data Structure that will maintain a list of RemoteReference 
	//It needs to be thread safe.
	public Map<String, RemoteRef> registryMap = null; 
	
	public int registryPortNumber;
	public String registryIpAddress;
	

	public RMIRegistry(String ipAddress, int portNumber)
	{
		this.registryMap = Collections.synchronizedMap(new HashMap<String, RemoteRef>());
		this.registryIpAddress = ipAddress;
		this.registryPortNumber = portNumber;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	    ServerSocket rmiListener = null;
	    
	    try {
	    
	    	rmiListener = new ServerSocket(this.registryPortNumber);
	      /* get a new request and start a new handler thread */
	      while (true) {
	        Socket requestSocket = rmiListener.accept();
	        
	        new Thread(new RMIRegisterRequestHandler(requestSocket, this)).start();
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	      e.printStackTrace();
	    } catch (SecurityException e) {
	      e.printStackTrace();
	    } finally 
	    {
	     if(rmiListener != null)
	     {
	      try {
	    	  rmiListener.close();
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	     }
	    }
	}
	
	
	public static void main(String[] args)
	{
		    if (args.length != 2) 
		    {
		      System.err.println("Usage: RMIRegistry <registry_ip> <registry_port>");
		      return;
		    }
		   Thread RMIRegistryThread =  new Thread(new RMIRegistry(args[0].toString(), Integer.parseInt(args[1])));
		   RMIRegistryThread.start();
	}

}
