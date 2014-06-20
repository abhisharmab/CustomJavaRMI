/**
 * 
 */
package abhi.ds;

/**
 * @author abhisheksharma
 *
 */
public class InvocationResponseMessage extends BaseSignal {
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	private Object returnObject;
	private boolean isException;
	private String exceptionMessage;

	//Valid Reponse
	public void InvocationRepsonseMessage(Object object)
	{
		this.returnObject = object;
		this.signalType = SignalType.InvocationResponse;
	}
	
	//Exception Occured..Then send the Exception Message
	public void InvocationRepsonseMessage(Boolean isException, String message)
	{
		this.setException(isException);
		this.setExceptionMessage(message);
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
