/**
 * 
 */
package abhi.utility;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 * This is signal that is sent from Client to the Server
 * It captures lookup name information that is needed on the server-side to lookup from the HashMap on the serverside 
 * the lookup name sent by the client is matched against the key on the Hash-Map on the serverside to find the RemoteRef Object.
 */
public class LookupSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	private String lookup_Name;
	
	public LookupSignal(){
		setSignalType(SignalType.LookUp);
	}
	
	public LookupSignal(String name){
		setSignalType(SignalType.LookUp);
		setLookup_Name(name);
	}

	public String getLookup_Name() {
		return lookup_Name;
	}

	public void setLookup_Name(String lookup_Name) {
		this.lookup_Name = lookup_Name;
	}

	


}
