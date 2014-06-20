/**
 * 
 */
package abhi.ds;

/**
 * @author abhisheksharma
 *
 */
public class InvokeMethodSignal extends BaseSignal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String className; 
	
	private String methodName;
	
	private String returnType;
	
	private Object[] arguments;
	
	
	public InvokeMethodSignal(String className, String methodName, String returnType, Object[] arguments)
	{
		this.className = className;
		this.methodName = methodName;
		this.returnType = returnType;
		this.arguments = arguments;
		this.signalType = SignalType.Invoke;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the returnType
	 */
	public String getReturnType() {
		return returnType;
	}

	/**
	 * @param returnType the returnType to set
	 */
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	/**
	 * @return the arguments
	 */
	public Object[] getArguments() {
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	

	
	

}
