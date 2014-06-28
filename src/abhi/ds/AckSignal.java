/**
 * 
 */
package abhi.utility;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 *This is the Acknowledgement signal from the Registry for the BIND and ReBind request from the Server. 
 *The registry sends this signal to let the server know that it could take the remote ref and it put it in its map.
 */
public class AckSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	
	public AckSignal(){
		setSignalType(SignalType.Acknowledgement);
	}


}
