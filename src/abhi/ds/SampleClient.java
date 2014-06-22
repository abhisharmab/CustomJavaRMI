/**
 * 
 */
package abhi.ds;

/**
 * @author abhisheksharma
 *
 */
public class SampleClient {
	
	public static void main(String[] args)
	{
		if(args.length !=2)
		{
			System.err.println("Usage: TestClient <registry_ip> <registry_port>");
		    return; 	
		}
		
		String registryIpAddr = args[0].trim();
		int registryPort = Integer.parseInt(args[1].trim());

		IAddandSubtract addSubtractObject;
		try 
		{
			addSubtractObject = (IAddandSubtract) ProxyStubCompiler.giveStub("IAddandSubtract", IAddandSubtract.class, registryIpAddr, registryPort);
			int sum = addSubtractObject.Add(8, 9);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}

	}

}
