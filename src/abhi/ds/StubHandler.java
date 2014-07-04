/**
 * 
 */
package abhi.ds;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
import java.net.UnknownHostException;

import abhi.ds.BaseSignal.SignalType;

/**
 * @author abhisheksharma, dkrew
 * STUBHANDLER is the common handler class that Handles the relaying of messages to the server-side for calling the Remote Temperature. 
 * Each time a method is called upon the STUB object all the meta-data is package and the 
 * "INVOKE" method of this handler is called. 
 * 
 * StubHandler packages the data into a InvokeMethodSignal with the method name, parameters and marhsalls all this info to the server. 
 * Waits for the repsonse from the server and unpacks the information (exception or results) and carefully passes it to the client.
 */
public class StubHandler implements InvocationHandler {

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	private RemoteRef remoteRef;
	
	public StubHandler(RemoteRef ref)
	{
		this.remoteRef = ref;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Socket clientSocket = null;
		
		InvokeMethodSignal invokeSignal = new InvokeMethodSignal(remoteRef.getClass_Name(), method.getName(), method.getReturnType().getName(), args, method.getDeclaringClass());
		
		try
		{
			// Connecting to the server
			clientSocket = new Socket(remoteRef.getIp_Address(), remoteRef.getPort());
			
			// packing signal and sending it
			HelperUtility.sendSignal(clientSocket, invokeSignal);
			
			// Receive signal back from client
			BaseSignal baseSignal = (BaseSignal) HelperUtility.receiveSignal(clientSocket);
			
			if(baseSignal.getSignalType() == SignalType.InvocationResponse)
			{
				InvocationResponseSignal responseMsg = (InvocationResponseSignal) baseSignal;
				
				//Check if there was any Exception
				if(responseMsg.getReturnObject() == null)
					throw new Exception("Sever returned NULL. Request un-successful");
				
				//Check if there was any Exception
				if(responseMsg.isException())
					return new Exception(responseMsg.getExceptionMessage());
				
				//Successfully execution; return result to the client.
				else
				{
					return responseMsg.getReturnObject();
				}
			}
			else 
			{
				return null;
			}
			
		}
		catch (UnknownHostException e) 
		{
		      throw new Exception("Could not connect to the Server. Server might be dead or please try again");
		} 
		catch (IOException e) 
		{
		     throw new Exception("Error occured on the socket. I/O stream could not be established or accessed");

		    } 
		finally {
		      if (clientSocket != null) {
		        try 
		        {
		        	clientSocket.close();
		        } catch (IOException e) {
		        	throw new Exception("Could not close socket connection with the Server");
		        }
		      }
		    }
	}

}
