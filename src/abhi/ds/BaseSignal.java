/**
 * 
 */
package abhi.ds;

/**
 * @author abhisheksharma
 * 
 * Base Signal is the parent class and establishes the platform for communicatoin in this distribued system.
 * All the other signals inheret from the based signal. 
 */
public class BaseSignal implements ISignal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Defines all the Signal Types in the system.
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
