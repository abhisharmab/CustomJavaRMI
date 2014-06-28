/**
 * 
 */
package abhi.utility;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 *This class encapsulated the remote REFERENCE> 
 *Which is nothing but the MetaData about the remote Object. 
 *This meta is also pretty much registered in the registry
 *This Bind Signal is sent by the server to the RMI Registru.
 */
public class BindSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	private RemoteRef remote_Ref;
	
	public BindSignal(){
		setSignalType(SignalType.Bind);
	}

	
	public BindSignal(RemoteRef ref){
		setSignalType(SignalType.Bind);
		setRemote_Ref(ref);
	}

	//Package Remote Ref in the Bind Signal
	public RemoteRef getRemote_Ref() {
		return remote_Ref;
	}

	public void setRemote_Ref(RemoteRef remote_Ref) {
		this.remote_Ref = remote_Ref;
	}
	
	


}
