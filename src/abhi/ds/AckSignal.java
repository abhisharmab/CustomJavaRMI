/**
 * 
 */
package abhi.ds;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 *This class encapsulated the remote REFERENCE> 
 *Which is nothing but the MetaData about the remote Object. This meta-data will be used to basiclaly  create the STUB.
 *This meta is also pretty much registered in the registry
 */
public class AckSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	
	public AckSignal(){
		setSignalType(SignalType.Acknowledgement);
	}


}
