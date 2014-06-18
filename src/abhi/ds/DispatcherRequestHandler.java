/**
 * 
 */
package abhi.ds;

import java.net.*;

/**
 * @author abhisheksharma
 *This class is the Request Handler for the Dispatcher which handles each request in and individual separate thread. 
 *
 */
public class DispatcherRequestHandler implements Runnable {

	//Properties 
	
	private ProxyDispatcher proxyDispatcher; 
	
	
	public DispatcherRequestHandler(Socket socket, ProxyDispatcher proxyDispatcher)
	{
		this.proxyDispatcher = proxyDispatcher;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
