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
	
	public static HashMap<String, RemoteRef> cachedRemoteRef = new HashMap<String,RemoteRef>();
	
	
	public static Object giveStub(String interfaceName, String registryIp, int registryPort)
	{
		return new Object();
	}
	
	public static RemoteRef getRemoteReferencefromRegistry(String interfaceName, String registryIp, int registryPort) throws Exception
	{
		//LookUpSignal lookupSignal = new LookUpSignal(interfaceName);
		
		Socket clientSideSocket = new Socket(registryIp, registryPort);
		
		//HelperUtility.sendSignal(clientSideSocket, lookupSignal);
		
		BaseSignal baseSignal = (BaseSignal) HelperUtility.receiveSignal(clientSideSocket);
		
		if(baseSignal.signalType==SignalType.LookUp) //Abhi Change this Type
		{
			//AckLookupSignal ackSig = (AckLookupSignal) baseSignal;
			
			//return ackSig.remoteRef;
		}
		else
		{
			throw new Exception("No/Bad Response from RMIRegistry");
		}
		return null;
	}
	
	

}
