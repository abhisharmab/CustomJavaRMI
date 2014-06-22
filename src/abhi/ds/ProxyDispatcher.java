/**
 * 
 */
package abhi.ds;

import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author abhisheksharma, dkrew0213
 * Following the guidelines the ProxyDispatcher runs the server-side. 
 * This guy is listening to remote method firing requests from the clients and actually handles the execution of the method.
 * In some ways this is the server that has knowledge of the actually IMPLEMENT of a particular function.
 * It will execute the function and return the result.
 */
public class ProxyDispatcher implements Runnable {
	
	//Map on the server side of the Actual Objects that need to be there
	private Map<String, Object> actualObjects;
	
	//Properties the Dispatcher Needs
	public String rmiRegistryIp = "";

	// the port of the registry server
	public int rmiRegistryPort = 0;
	
	public String dispatcherIpAdress; 
	public int dispatcherPortNumber;  
	

	public ProxyDispatcher()
	{
		this.actualObjects =  Collections.synchronizedMap(new TreeMap<String, Object>());
	}
	
	// Commenting some stuff out to test the connection with the 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		// open connection to the RMI registry
		// register object close the connection
		
		
		ServerSocket dispatcherListener = null;
		if(this.dispatcherPortNumber > 0)
		{
			try
			{
				dispatcherListener = new ServerSocket(this.dispatcherPortNumber);
				setDispatcherIpAddress(InetAddress.getLocalHost().getCanonicalHostName());
				System.out.println("Proxy Dispatcher");
				System.out.println("Address : " +getDispatcherIpAddress());
				System.out.println("Port : " + getDispatcherPortNumber());
				while(true)
				{
					Socket requestSocket = dispatcherListener.accept();
					
					//Start NEW THREAD FOR RequestHandler and Go Back to Listening to Requests
					//Cater to requests in an unblocking way.
					Thread proxyDispatcherRequestHandler = new Thread(new ProxyDispatcherRequestHandler(requestSocket,this));
					proxyDispatcherRequestHandler.start();
					
				}
			}
			catch(IOException e)
			{
				System.out.println("Error occured while opening the Server Socker for the proxy server.");
				System.out.println("Please use a differenet portnumber. ");
				
			}
			
			finally
			{
				if(dispatcherListener != null)
				{
					try {
						dispatcherListener.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}
		
		public static void main(String[] args) {
			if (args.length != 3) {
				System.err.println("Usage: Dispatcher <dispatcher_port> <registry_ip> <registery_port>");
				return;
			}

			ProxyDispatcher proxyDispatcher = new ProxyDispatcher();
			proxyDispatcher.setDispatcherPortNumber(Integer.parseInt(args[0]));
			proxyDispatcher.rmiRegistryIp = args[1];
			proxyDispatcher.rmiRegistryPort = Integer.parseInt(args[2]);

			
			proxyDispatcher.register_To_Rmi();
			// start the listening thread
			new Thread(proxyDispatcher).start();

		}

		public int getDispatcherPortNumber()
		{
			  return this.dispatcherPortNumber;
		}
		  
		public String getDispatcherIpAddress()
		{
			 return this.dispatcherIpAdress;
		}
		public void setDispatcherPortNumber(int portNumber)
		{
			   this.dispatcherPortNumber = portNumber ;
		}
		  
		public void setDispatcherIpAddress(String ipAddress)
		{
			  this.dispatcherIpAdress = ipAddress;
		}
		
		public Object getAppropriateObject(String className)
		{
			if(className ==null || className.isEmpty())
			{
				return null;
				
			}
			
			else
			{
				return this.actualObjects.get(className);
			}
		}
		
		

		private Socket intialize_socket(){
			
			if(this.rmiRegistryIp.isEmpty() || this.rmiRegistryPort == 0){
				return null;
			}
			
			try {
				Socket socket = new Socket(rmiRegistryIp, rmiRegistryPort);
				return socket;
			} catch (UnknownHostException e) {
				System.out.println("UnknownHost, please recheck the Rmi Registry Ip and Port number.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
			
			
		}
		

		
		private void register_To_Rmi(){
			
			Socket socket = intialize_socket();

			// Bind AddSubtract
			if(socket != null){
				try {
					AddandSubtract addandSubtract = new AddandSubtract();
					RemoteRef addSubtract_ref = initialize_RemoteRef(addandSubtract);
					getActualObjects().put(addSubtract_ref.getClass_Name(), addandSubtract);
					
					HelperUtility.sendSignal(socket, new BindSignal(addSubtract_ref));
					Object object = HelperUtility.receiveSignal(socket);
					
					
					if( object instanceof AckSignal){
						System.out.println("AddandSubtract binded.");
					} else if (object instanceof RemoteExceptionSignal){
						RemoteExceptionSignal exception = (RemoteExceptionSignal) object;
						System.out.println("Binding Failed : " + exception.getExpection());
						
						if(exception.getExpection().contains(new String("REBIND"))){
							socket.close();
							socket = intialize_socket();
							HelperUtility.sendSignal(socket, new RebindSignal(addSubtract_ref));
							Object rebind_object = HelperUtility.receiveSignal(socket);
							if( rebind_object instanceof AckSignal){
								System.out.println("Rebind Successed.");
							} else  if (rebind_object instanceof RemoteExceptionSignal){
								System.out.println("Rebinding Failed : " + exception.getExpection());
								System.out.println("No more retry.");
							}							
						}
						
					} 
					socket.close();
				} catch (RuntimeException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
			
		}
		
		private RemoteRef initialize_RemoteRef(Object object){
			
			RemoteRef remoteRegObject = new RemoteRef();
			
			
			if( object instanceof AddandSubtract){
				AddandSubtract addSubtrack = (AddandSubtract) object;
				String register_name = new String("");
				for(Class c : addSubtrack.getClass().getInterfaces()){
					if( !c.getSimpleName().equals("IDistributedObject")){
						register_name = c.getSimpleName();
					}
				}
				remoteRegObject.setRegister_Name(register_name);
				remoteRegObject.setClass_Name(addSubtrack.getClass().getName());
				remoteRegObject.setInterfaces(addSubtrack.getClass().getInterfaces());
			}
			remoteRegObject.setIp_Address(getDispatcherIpAddress());
			remoteRegObject.setPort(getDispatcherPortNumber());
			return remoteRegObject;
		}

		public Map<String, Object> getActualObjects() {
			return actualObjects;
		}

		public void setActualObjects(Map<String, Object> actualObjects) {
			this.actualObjects = actualObjects;
		}

}
	


