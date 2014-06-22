package abhi.ds;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author abhisheksharma, dkrew0213
 */
public class RMIRegistry implements Runnable {

	//The registry entries that maintains a list of all the RemoteReference of the Distributed Objects in the System.
	//The Map will be common Data Structure that will maintain a list of RemoteReference 
	//It needs to be thread safe.
	public Map<String, RemoteRef> registryMap = null; 
	

	public Map<String, RemoteRef> getRegistryMap() {
		return registryMap;
	}

	public void setRegistryMap(Map<String, RemoteRef> registryMap) {
		this.registryMap = registryMap;
	}


	private int registryPortNumber;
	private String registryIpAddress;
	

	public RMIRegistry( int portNumber)
	{
		this.registryMap = Collections.synchronizedMap(new HashMap<String, RemoteRef>());
		try {
			this.registryIpAddress = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.registryPortNumber = portNumber;
		
		System.out.println("RMI Registry Started");
		System.out.println("Registry IP : " + registryIpAddress);
		System.out.println("Registry Port : " + registryPortNumber);
		
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
	    } finally {
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
	


	public int getRegistryPortNumber() {
		return registryPortNumber;
	}

	public void setRegistryPortNumber(int registryPortNumber) {
		this.registryPortNumber = registryPortNumber;
	}

	public String getRegistryIpAddress() {
		return registryIpAddress;
	}

	public void setRegistryIpAddress(String registryIpAddress) {
		this.registryIpAddress = registryIpAddress;
	}

	
	public static void main(String[] args)
	{
		if (args.length != 1) 
	    {
	   
	      System.err.println("Usage: RMIRegistry <registry_port>");
	      return;
	    }
	    
	   Thread RMIRegistryThread =  new Thread(new RMIRegistry(Integer.parseInt(args[0])));
	   RMIRegistryThread.start();
	}

}
