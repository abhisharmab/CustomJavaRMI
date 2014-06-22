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
 * @author abhisheksharma
 *
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
			clientSocket = new Socket(remoteRef.getIp_Address(), remoteRef.getPort());
			
			HelperUtility.sendSignal(clientSocket, invokeSignal);
			
			BaseSignal baseSignal = (BaseSignal) HelperUtility.receiveSignal(clientSocket);
			
			if(baseSignal.getSignalType() == SignalType.InvocationResponse)
			{
				InvocationResponseMessage responseMsg = (InvocationResponseMessage) baseSignal;
				return responseMsg.getReturnObject();
			}
			else 
			{
				return null;
			}
			
		}
		catch (UnknownHostException e) 
		{
		      e.printStackTrace();
		} 
		catch (IOException e) 
		{
		     e.printStackTrace();
		      
		      //TO-DO when this exception comes in I need to go and re-try to get the a new lookup object
		    } 
		finally {
		      if (clientSocket != null) {
		        try 
		        {
		        	clientSocket.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		      }
		    }

	
		return null;
	}

}
