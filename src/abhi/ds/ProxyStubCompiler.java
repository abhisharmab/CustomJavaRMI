/**
 * 
 */
package abhi.ds;

import java.io.EOFException;
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
		
		Socket rmiRegisterySocket = null;
		try 
		{
			rmiRegisterySocket = new Socket(registryIp, registryPort);

			HelperUtility.sendSignal(rmiRegisterySocket, lookupSignal);

			
			BaseSignal baseSignal = (BaseSignal) HelperUtility.receiveSignal(rmiRegisterySocket);

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
		catch (UnknownHostException | ConnectException e) 
		{
			System.out.println("Error while connecting to the RMI registry.");
			System.out.println("Please check the RMI registry IP and the Port number.");
		} 
		catch (IOException e) 
		{
			throw new Exception("No/Bad Connection.");
	    }
		finally 
		{
	      if (rmiRegisterySocket != null) {
	        try 
	        {
	        	rmiRegisterySocket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	      }
	    }

		return null;
	 }
		
 }
	

