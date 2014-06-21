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
public class RemoteExceptionSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	private String expection;
	
	public RemoteExceptionSignal(){
		setSignalType(SignalType.Exception);
	}

	public RemoteExceptionSignal(String e){
		setSignalType(SignalType.Exception);
		setExpection(e);
	}
	
	public String getExpection() {
		return expection;
	}

	public void setExpection(String expection) {
		this.expection = expection;
	}

	
	


}
