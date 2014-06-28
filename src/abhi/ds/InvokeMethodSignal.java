/**
 * 
 */
package abhi.utility;

/**
 * @author abhisheksharma, dkrew
 */
 // The Invoke Method captures all the information that is send from the Client Side to the server 
 // The invoke signal contains all the information like the Method Name, Parameters, Implementing Class Interface and the 
//  Expected returntype. 

// The client packages all this information and send this to server for remote method invocation.
 
public class InvokeMethodSignal extends BaseSignal {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String className; 
	private String methodName;
	private String returnType;
	private Object[] arguments;
	private Class classinContext;

	
	
	public InvokeMethodSignal(String className, String methodName, String returnType, Object[] arguments, Class classContext)
	{
		this.className = className;
		this.methodName = methodName;
		this.returnType = returnType;
		this.arguments = arguments;
		this.classinContext = classContext;
		this.signalType = SignalType.Invoke;
	}
	
	//The Class we are talking about for this method.
	public void setClassinContext(Class classinContext)
	{
		this.classinContext = classinContext;
	}

	public Class getClassinContext()
	{
		return this.classinContext;
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
