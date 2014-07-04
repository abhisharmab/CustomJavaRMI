package abhi.ds;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import abhi.ds.BaseSignal.SignalType;

/**
 * @author abhisheksharma, dkrew0213
 * This class implements the RMIRegistry for the RMI system. Registry could run no the chosen machine and 
 * maintains a map of the Remote Reference Object. 
 * These objects are received from the Dispatcher Server.
 * 
 * The RMI also Cater's to Client's requests for Lookups. It packages the Remote References and sends it to the client.
 */
public class RMIRegistry implements Runnable {

	//The registry entries that maintains a list of all the RemoteReference of the Distributed Objects in the System.
	//The Map will be common Data Structure that will maintain a list of RemoteReference 
	//It needs to be thread safe since many servers could request to BIND AND RE_BIND simultaneously
	public Map<String, RemoteRef> registryMap = null; 
	

	public Map<String, RemoteRef> getRegistryMap() {
		return registryMap;
	}

	public void setRegistryMap(Map<String, RemoteRef> registryMap) {
		this.registryMap = registryMap;
	}


	private int registryPortNumber;
	private String registryIpAddress;
	
	//RMI Registry Constructor
	public RMIRegistry( int portNumber)
	{
		this.registryMap = Collections.synchronizedMap(new HashMap<String, RemoteRef>());
		try {
			this.registryIpAddress = InetAddress.getLocalHost().getCanonicalHostName();
			this.registryPortNumber = portNumber;
			System.out.println("RMI Registry Started");
			System.out.println("Registry IP : " + InetAddress.getLocalHost().toString());
			System.out.println("Registry Port : " + registryPortNumber);
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	@Override
	public void run() {
		
	    ServerSocket rmiListener = null;
	    
	    try {
	    
	    	rmiListener = new ServerSocket(this.registryPortNumber);
	      /* get a new request and start a new handler thread */
	    	while (true) {
	    		Socket requestSocket = rmiListener.accept();
	    		
	    		//Spawn a new thread of the RMIRequest Handler this will not block the RMI Registry.
	    		new Thread(new RMIRegisterRequestHandler(requestSocket, this)).start();
	      	}
	    } catch (IOException e) {
	    	System.out.println("Error starting the RMIRegistry. Please confirm the port number is not already in use and try again");
	    } catch (IllegalArgumentException e) {
	    	System.out.println("Error starting the RMIRegistry. Please confirm the port number is not already in use and try again");
	    } catch (SecurityException e) {
	    	System.out.println("Error starting the RMIRegistry. Please confirm the port number is not already in use and try again");
	    } finally {
		     if(rmiListener != null)
		     {
		    	 try {
		    		 rmiListener.close();
		    	 } catch (IOException e) {
		    		 System.out.println("Error occured while closing the RMI socket");
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
	    
		try{
			  Thread RMIRegistryThread =  new Thread(new RMIRegistry(Integer.parseInt(args[0])));
			   
			   //Starting the thread
			   RMIRegistryThread.start();		
		} catch (NumberFormatException e){
			System.out.println("Error in port number, please try again.");
		}
	 
	}

}
