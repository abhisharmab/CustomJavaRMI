/**
 * 
 */
package abhi.ds;

import java.io.IOException;
import java.io.ObjectInputStream;
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
		if(this.requestSocket == null){
			try {
				throw new Exception("Socket is invalid. Problem occured");
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
			
		BaseSignal signal = null;
		
		try 
		{
			ObjectInputStream objStream = new ObjectInputStream(this.requestSocket.getInputStream());
			signal = (BaseSignal) objStream.readObject();
			SignalHandler(signal);
			objStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SignalHandler(BaseSignal baseSignal)
	{
		switch(baseSignal.signalType)
		{
		
			case Invoke:
				
				InvokeMethodSignal imSignal = (InvokeMethodSignal) baseSignal;
				
				Object actualObject = this.proxyDispatcher.getAppropriateObject(imSignal.getClassName());
				
				break;
			
				
			default:
				break;
		
		}
	}

}
