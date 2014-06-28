/**
 * 
 */
package abhi.utility;

/**
 * @author abhisheksharma
 */
 //This class in the Invocation Response Signal sent by the server
// The encapsulates the return result of the Add or the Subtract method that needs to be sent to the client 
// If there is an exception on the Server side then it also packages the Exception message in it. 
 
public class InvocationResponseSignal extends BaseSignal {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	private Object returnObject;
	private boolean isException; //Boolean used to indicate if there was exception on the serverside or what there a successful result.
	private String exceptionMessage;

	public InvocationResponseSignal(boolean exception, String message) {
		this.setException(isException);
		this.setExceptionMessage(message);
		this.signalType = SignalType.InvocationResponse;
	}

	//Valid Response
	public InvocationResponseSignal(Object object)
	{
		this.returnObject = object;
		this.isException = false;
		this.signalType = SignalType.InvocationResponse;
	}
	

	public Object getReturnObject()
	{
		return this.returnObject;
	}

	/**
	 * @return the exceptionMessage
	 */
	public String getExceptionMessage() {
		return exceptionMessage;
	}

	/**
	 * @param exceptionMessage the exceptionMessage to set
	 */
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	/**
	 * @return the isException
	 */
	public boolean isException() {
		return isException;
	}

	/**
	 * @param isException the isException to set
	 */
	public void setException(boolean isException) {
		this.isException = isException;
	}

}
