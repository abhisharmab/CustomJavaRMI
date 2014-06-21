/**
 * 
 */
package abhi.ds;

import java.io.IOException;
import java.lang.reflect.*;
import java.lang.reflect.Proxy;
import java.net.*;
import java.util.*;

import abhi.ds.BaseSignal.SignalType;

/**
 * @author abhisheksharma
 *
 */
public  class ProxyStubCompiler {
	
	public static HashMap<String, Object> cachedStubObjects = new HashMap<String,Object>();
	
	
	public static Object giveStub(String interfaceName, Class<?> interfaceClass, String registryIp, int registryPort) throws Exception
	{
		if(cachedStubObjects.containsKey(interfaceName))
			return cachedStubObjects.get(interfaceName);
		else
		{
			try 
			{
				RemoteRef remoteRef = ProxyStubCompiler.getRemoteReferencefromRegistry(interfaceName, registryIp, registryPort);
				
				if(remoteRef != null)
				{
					Object stubObject = ProxyStubCompiler.getStub(remoteRef, interfaceClass);
					cachedStubObjects.put(interfaceName, stubObject);
					return stubObject;
				}
				else
				{
					throw new Exception("RemoteReference Not Available from Registry");
				}
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw e;
			}
			
		}
		
	}
	
	private static Object getStub(RemoteRef remoteRef, Class interfaceClass) 
	{
		// TODO Auto-generated method stub
		
		Object proxyInstance = null;
		
	    proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                 new Class[] { interfaceClass },
                 new StubHandler(remoteRef)); 
		
	    return proxyInstance;
	}
	


	public static RemoteRef getRemoteReferencefromRegistry(String interfaceName, String registryIp, int registryPort) throws Exception
	{
		LookupSignal lookupSignal = new LookupSignal(interfaceName);
		
		Socket clientSideSocket = null;
		try 
		{
			clientSideSocket = new Socket(registryIp, registryPort);

			HelperUtility.sendSignal(clientSideSocket, lookupSignal);

			
			BaseSignal baseSignal = (BaseSignal) HelperUtility.receiveSignal(clientSideSocket);

			if(baseSignal.signalType==SignalType.Ack_Lookup) 
			{
				AckLookupSignal ackSig = (AckLookupSignal) baseSignal;

				return ackSig.getRemote_Ref();
			}
			else
			{
				throw new Exception("No/Bad Response from RMIRegistry");
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
		    } finally {
		      if (clientSideSocket != null) {
		        try 
		        {
		        	clientSideSocket.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		      }
		    }

		return null;
	 }
		
 }
	

