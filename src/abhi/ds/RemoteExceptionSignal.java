/**
 * 
 */
package abhi.utility;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 *
 *This class is a GENERIC class for capturing all the Exception Messages.
 *On occurance of an Exception this message is passed around. 
 */
public class RemoteExceptionSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	private String expection;
	
	public RemoteExceptionSignal(){
		setSignalType(SignalType.Exception);
	}

	//Exception of the message of whatever failed.
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
