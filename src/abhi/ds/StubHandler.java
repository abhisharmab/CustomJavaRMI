/**
 * 
 */
package abhi.ds;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author abhisheksharma
 *
 */
public class StubHandler implements InvocationHandler {

	/* (non-Javadoc)
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	private RemoteRef remoteRef;
	
	public StubHandler(RemoteRef ref)
	{
		this.remoteRef = ref;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		InvokeMethodSignal invokeSignal = new InvokeMethodSignal(remoteRef.getClass_Name(), method.getName(), method.getReturnType().getName(), args);
		
		
		return null;
	}

}
