/**
 * 
 */
package abhi.ds;

/**
 * @author abhisheksharma
 *
 */
public class BaseSignal implements ISignal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum SignalType
	{
		Invoke,
		InvocationResponse,
		Exception,
		Bind, 
		Rebind, 
		LookUp
	}
	
	public SignalType signalType;
	
	public BaseSignal()
	{
		
	}

}
