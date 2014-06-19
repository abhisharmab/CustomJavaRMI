/**
 * 
 */
package abhi.ds;

import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * @author abhisheksharma, dkrew0213
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
	 * @see java.lang.Runnable#run()ww
	 */
	@Override
	public void run() {
		// TODO 
		//People will come here to register services
		
		//People will come here to do lookup i.e. request the RemoteReference of Objects they are looking for.
		
		//@Doug: So we need those 2 kinds of Signals

			while (true) {
				
				// Make grep take longer so that we don't require extremely large files for interesting results
				try {
					Thread.sleep(500);
					System.out.println("this is the rmi");
				} catch (InterruptedException e) {
					// ignore it
				}
	
			}
		
	}

}
