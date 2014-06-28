/**
 * 
 */
package abhi.utility;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 *This class encapsulated the Remote REFERENCE> 
 *Which is nothing but the MetaData about the Remote Object. This meta-data will be used to basiclaly  create the STUB.
 *This meta is also pretty much registered in the registry
 *This is Acknowledgement Signal for the LookUp request from the Client.
 */
public class AckLookupSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	private RemoteRef remote_Ref;
	
	public AckLookupSignal(){
		setSignalType(SignalType.Ack_Lookup);
	}
	
	public AckLookupSignal(RemoteRef ref){
		setSignalType(SignalType.Ack_Lookup);
		setRemote_Ref(ref);
	}

	public RemoteRef getRemote_Ref() {
		return remote_Ref;
	}

	public void setRemote_Ref(RemoteRef remote_Ref) {
		this.remote_Ref = remote_Ref;
	}
	
	


}
