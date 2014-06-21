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
		int registryPort = Integer.parseInt(args[0].trim());
		
	}

}
