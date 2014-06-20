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

	//Exception Occurred..Then send the Exception Message
	public InvocationResponseMessage(boolean exception, String message) {
		// TODO Auto-generated constructor stub
		this.setException(isException);
		this.setExceptionMessage(message);
		this.signalType = SignalType.InvocationResponse;
	}

	//Valid Response
	public InvocationResponseMessage(Object object)
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
