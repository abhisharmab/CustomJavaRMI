/**
 * 
 */
package abhi.ds;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.*;

import abhi.ds.BaseSignal.SignalType;


/**
 * @author abhisheksharma, dkrew0213
 *
 *The RMIRequestHandler implements the code for addressing requests made to the RMIRequest Handler.
 * This request handler is spawned for every request that hits the RMI server.
 * 
 * The four kinds of signals that are sent to RMI are:
 * 1. Lookup [from Client]
 * 2. Bind [from ProxyDispatcherServer]
 * 3. Re-bind [from ProxyDispatcherServer]
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

		if(requestSocket.isConnected()){
			
			ObjectInputStream sockIn;
				try {
					sockIn = new ObjectInputStream(requestSocket.getInputStream());
					BaseSignal baseSignal;
					baseSignal = (BaseSignal) sockIn.readObject();
					//Read the signal and switch case for the handler based on the type of Signal.
					
					switch (baseSignal.getSignalType()) {
						case Bind:
							System.out.println("RMI : Executing the Bind request");
							//Call the bind code
							bind((BindSignal) baseSignal);
							break;
							
						case Rebind:
							System.out.println("RMI : Executing the rebind request");
							//Call the Re-bind code
							rebind((RebindSignal) baseSignal);
							break;
							
						case LookUp:
							System.out.println("RMI : Executing the Lookup request");
							//Call the lookup code
							lookup((LookupSignal) baseSignal);
							break;
							
						default:
							break;
					}	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch ( ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
		
		

				
				
	}
	
	private void bind(BindSignal bindSignal){
		RemoteRef remote_Ref = bindSignal.getRemote_Ref();
		
		try {
			// checking whether there is already a bind object
			if(! this.rmiRegistry.getRegistryMap().containsKey(remote_Ref.getRegister_Name())){
				this.rmiRegistry.getRegistryMap().put(remote_Ref.getRegister_Name(), remote_Ref);
				
				
				HelperUtility.sendSignal(requestSocket, new AckSignal());
				System.out.println("Binding Successful.");
			} else {
				System.out.println("Failed : Register name is already in use.");
				HelperUtility.sendSignal(requestSocket, new RemoteExceptionSignal("REBIND : Cannot bind the register name is already in use."));
			}
		} catch (RuntimeException | IOException e) {
			
			System.out.println("Error in the connection.");
		} 
	
		
	
		
	}
	private void rebind(RebindSignal rebindSignal){
		RemoteRef remote_Ref = rebindSignal.getRemote_Ref();
		
		try {
			// Check whether there is a bind object because this is for rebind
			if( this.rmiRegistry.getRegistryMap().containsKey(remote_Ref.getRegister_Name())){
				this.rmiRegistry.getRegistryMap().put(remote_Ref.getRegister_Name(), remote_Ref);
				HelperUtility.sendSignal(requestSocket, new AckSignal());
				System.out.println("Rebinding Successful.");
			} else {
				System.out.println("Failed : Register name does not exist.");
				HelperUtility.sendSignal(requestSocket, new RemoteExceptionSignal("Cannot rebind the register name dose not exist."));
			}
				
		} catch (RuntimeException | IOException e) {
			
			System.out.println("Error in the connection.");
		} 
		
			
	}
	private void lookup(LookupSignal lookupSignal){
		String look_up_name = lookupSignal.getLookup_Name();
		
		try {
			if( this.rmiRegistry.getRegistryMap().containsKey(look_up_name)){
				System.out.println("Lookup Successful.");
				RemoteRef remote_Ref = this.rmiRegistry.getRegistryMap().get(look_up_name);
				
				//Return the RemoteRef to the CLient based on the Remote Ref.
				HelperUtility.sendSignal(requestSocket, new AckLookupSignal(remote_Ref));
				
				
			} else {
				System.out.println("Failed : Register name does not exist.");
				HelperUtility.sendSignal(requestSocket, new RemoteExceptionSignal("There are no remote reference object to look up."));
			}		
		} catch (RuntimeException | IOException e) {
			
			System.out.println("Error in the connection.");
		} 
	
	}

}
