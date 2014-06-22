/**
 * 
 */
package abhi.ds;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.lang.reflect.*;


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
				Object returnValue = null;
				
				if(actualObject!=null)
				{
					try {
						Class<?> actualClass = imSignal.getClassinContext();
						
						
				        Class<?>[] argstype = new Class[imSignal.getArguments().length];
				        for (int i = 0; i < imSignal.getArguments().length; i++) {
				          argstype[i] = imSignal.getArguments()[i].getClass();
				        }
				        
				        Method requestedMethod = actualClass.getMethod(imSignal.getMethodName(), argstype);
				        System.out.println("Invoking the requested method " + actualClass.getName() + "."
				                + requestedMethod.getName() + "() with " + imSignal.getArguments().length + " arguments.");
				        
				        try 
				        {
							returnValue = requestedMethod.invoke(actualObject, imSignal.getArguments());
						} 
				        catch (Exception e)
						{
				        	try 
				        	{
								new ObjectOutputStream(this.requestSocket.getOutputStream()).writeObject(new InvocationResponseMessage(true,"Method Threw an Exception"));
							} 
				        	catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 try {
						 //Everything is SuccessFUL SEND back the response
					      System.out.println("Send back the result by an InvocationResponseMessage.\n");
					      new ObjectOutputStream(this.requestSocket.getOutputStream()).writeObject(new InvocationResponseMessage(returnValue));
					    } catch (IOException e) {
					      e.printStackTrace();
					    }
				}
				else
				{
					try 
					{
						new ObjectOutputStream(this.requestSocket.getOutputStream()).writeObject(new InvocationResponseMessage(true,"Object Not Present Remotely"));
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
			
				
			default:
				break;
		
		}
	}

}
