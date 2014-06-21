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
		LookUp,
		Ack_Lookup,
		Acknowledgement
	}
	
	public SignalType signalType;
	
	public SignalType getSignalType() {
		return signalType;
	}

	public void setSignalType(SignalType signalType) {
		this.signalType = signalType;
	}

	public BaseSignal()
	{
		
	}

}
