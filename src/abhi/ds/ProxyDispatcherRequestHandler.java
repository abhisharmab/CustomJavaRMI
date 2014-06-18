/**
 * 
 */
package abhi.ds;

import java.net.*;

/**
 * @author abhisheksharma
 *This class is the Request Handler for the Dispatcher which handles each request in and individual separate thread. 
 * This means multiple instance of the client will be able to ask for requests without being blocked.
 *
 */
public class ProxyDispatcherRequestHandler implements Runnable { 

	//Properties 
	
	private ProxyDispatcher proxyDispatcher; 
	private Socket requestSocket = null;
	
	
	public ProxyDispatcherRequestHandler(Socket socket, ProxyDispatcher proxyDispatcher)
	{
		this.proxyDispatcher = proxyDispatcher;
		this.requestSocket = socket;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//People will invoke using this i.e. this guy will receive the Invocation Requests 
		//Actually call the method and 
		//Then send appropriate exception or response. 
		
		//@Doug: The STUB on the CLIENT SIDE WILL be WAITING UNTILT HIS GUYS RETURNS>
		
		//So we need 2 types of SIGNALS here which will be implement the ISignal Interface.

	}

}
