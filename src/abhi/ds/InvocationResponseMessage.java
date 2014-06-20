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
	

	public void InvocationRepsonseMessage(Object object)
	{
		this.returnObject = object;
		this.signalType = SignalType.InvocationResponse;
	}
	
	public Object getReturnObject()
	{
		return this.returnObject;
	}

}
