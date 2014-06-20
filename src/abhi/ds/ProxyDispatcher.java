/**
 * 
 */
package abhi.ds;

import java.io.IOException;
import java.net.*;

/**
 * @author abhisheksharma, dkrew0213
 * Following the guidelines the ProxyDispatcher runs the server-sider. 
 * This guy is listening to remote method firing requests from the clients and actually handles the execution of the method.
 * In some ways this is the server that has knowledge of the actually IMPLEMENT of a particular function.
 * It will execute the function and return the result.
 */
public class ProxyDispatcher implements Runnable {
	
	//Properties the Dispatcher Needs
	public String rmiRegistryIp = "";

	// the port of the registry server
	public int rmiRegistryPort = 0;
	
	public String dispatcherIpAdress; 
	public int dispatcherPortNumber;  
	

	public ProxyDispatcher()
	{
		
	}
	
	// Commenting some stuff out to test the connection with the 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		// open connection to the RMI registry
		// register object close the connectionsvs
		
		
		ServerSocket dispatcherListener = null;
		if(this.dispatcherPortNumber > 0)
		{
			try
			{
				dispatcherListener = new ServerSocket(this.dispatcherPortNumber);

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
				System.err.print(e.getStackTrace().toString());
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

}
	


