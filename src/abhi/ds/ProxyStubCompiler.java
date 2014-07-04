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
 * @author abhisheksharma, dkrew
 * 
 * This class is the STUB Compiler. Its primary jobs are: 
 * 1. Get Remote-Ref from the RMIRegistry 
 * 2. Generate a Proxy object (runtime-stub) by using reflections and teasing apart the remote reference object to create the STUB.
 * 3. For the Proxy object (stub) it registers a STUB Handler provided in the same package. This Stubhandler will marhsall the information 
 * and wait to accpet an response.
 */
public  class ProxyStubCompiler {
	
	//A local CACHE to maintain the generated STUBS on clientside. So we do not generate STUBs for consecutive calls.
	public static HashMap<String, Object> cachedStubObjects = new HashMap<String,Object>();
	
	// Generate the stub by looking up the interfacenmae from the RMI registry.
	public static Object giveStub(String interfaceName, Class<?> interfaceClass, String registryIp, int registryPort) throws Exception
	{
		if(cachedStubObjects.containsKey(interfaceName)) //if STUB already present just return the cached copy.
			return cachedStubObjects.get(interfaceName);
		else
		{
			try 
			{
				// Asking the RMI registry for the remote referene
				RemoteRef remoteRef = ProxyStubCompiler.getRemoteReferencefromRegistry(interfaceName, registryIp, registryPort);
				
				if(remoteRef != null)
				{
					// Calling the stub compiler to generate stub
					// This method creates a Proxy Object using the proxy class
					Object stubObject = ProxyStubCompiler.getStub(remoteRef, interfaceClass);
					
					// Cached the stub object
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
		
		// Generate a proxy instance giving a reference to the stub handler
	    proxyInstance = Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                 new Class[] { interfaceClass },
                 new StubHandler(remoteRef)); //Registry a common StubHandler for the proxyInstance.
		
	    return proxyInstance;
	}
	


	public static RemoteRef getRemoteReferencefromRegistry(String interfaceName, String registryIp, int registryPort) throws Exception
	{
		LookupSignal lookupSignal = new LookupSignal(interfaceName);
		
		Socket rmiRegisterySocket = null;
		try 
		{
			rmiRegisterySocket = new Socket(registryIp, registryPort);

			//Send the lookupSignal to the RMIRegistry to ask the RMIRegistry to return a Reference.
			HelperUtility.sendSignal(rmiRegisterySocket, lookupSignal);

			//Block until the RMIRegistry replies with an Acknowledge Signal whcih will carry the Remote Ref.
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
	            throw new Exception("Error closing sockets with the registry");
	        }
	      }
	    }

		return null;
	 }
		
 }
	

