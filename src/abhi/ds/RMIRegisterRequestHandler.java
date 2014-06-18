/**
 * 
 */
package abhi.ds;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * @author abhisheksharma
 *
 */
public class RMIRegisterRequestHandler implements Runnable {

	private RMIRegistry rmiRegistry = null;
	private Socket requestSocket = null;
	
	public RMIRegisterRequestHandler (Socket requestSocket, RMIRegistry registry)
	{
		this.requestSocket = requestSocket;
		this.rmiRegistry = registry;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO 
		//People will come here to register services
		
		//People will come here to do lookup i.e. request the RemoteReference of Objects they are looking for.
		
		//@Doug: So we need those 2 kinds of Signals

	}

}
