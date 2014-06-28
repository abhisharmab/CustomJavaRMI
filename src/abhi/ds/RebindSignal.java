/**
 * 
 */
package abhi.utility;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 * 
 * Inherently this is pretty much just like the BIND SIgnal but we needed another signal to identigy this si re-bind signa;
 * This is used if the server goes down maybe comes back up but now it mayabe running services on another port. 
 * So all the remote-ref information must be sent to the RMIRegistry again so that it can updates it copy of RemoteRef for Clients.
 */
public class RebindSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	//RemoteREf Object to be Stored on the RMIRegistry Side.
	private RemoteRef remote_Ref;
	
	public RebindSignal(){
		setSignalType(SignalType.Rebind);
	}
	
	public RebindSignal(RemoteRef ref){
		setSignalType(SignalType.Rebind);
		setRemote_Ref(ref);
	}
	
	

	public RemoteRef getRemote_Ref() {
		return remote_Ref;
	}

	public void setRemote_Ref(RemoteRef remote_Ref) {
		this.remote_Ref = remote_Ref;
	}
	
	


}
