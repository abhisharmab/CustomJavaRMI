/**
 * 
 */
package abhi.ds;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 *This class encapsulated the Remote REFERENCE> 
 *Which is nothing but the MetaData about the Remote Object. This meta-data will be used to basiclaly  create the STUB.
 *This meta is also pretty much registered in the registry
 */
public class RebindSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	private RemoteRef remote_Ref;
	
	public RebindSignal(){
		setSignalType(SignalType.Rebind);
	}

	public RemoteRef getRemote_Ref() {
		return remote_Ref;
	}

	public void setRemote_Ref(RemoteRef remote_Ref) {
		this.remote_Ref = remote_Ref;
	}
	
	


}
