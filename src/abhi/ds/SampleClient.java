/**
 * 
 */
package abhi.ds;

/**
 * @author abhisheksharma, dkrew
 * 
 * SampleClient (this class, is a sample test client which makes REMOTE REFERENCE CALL)
 * From an Application Programmer's perspective we try to provide as much as transperency as possible.
 * The user just asking from a STUB with the giving a IAdd interface and then just call the ADD METHOD UPON it. 
 * For an Application programmer it just seems like he is calling the method locally. 
 * We did not wrap the giveSTUB() method just for the sake for clarity for grading but it could be abstracted from the App. programmers as well.
 */
public class SampleClient {
	
	public static void main(String[] args)
	{
		if(args.length !=2)
		{
			System.err.println("Usage: SampleClient <registry_ip> <registry_port>");
		    return; 	
		}
		
		String registryIpAddr = args[0].trim();
		int registryPort = Integer.parseInt(args[1].trim());

		// Doing Add
		IAdd addObject;
		try 
		{
			
			addObject = (IAdd) ProxyStubCompiler.giveStub("IAdd", IAdd.class, registryIpAddr, registryPort);
			//We did not wrap the giveSTUB() method just for the sake for clarity for grading but it could be abstracted from the App. programmers as well.
			
			System.out.println("\nAdding: 8 with 9.");
			int sum = addObject.Add(8, 9); //Call a Remote Method Add with the Respective Parameters.
			System.out.println(sum); 
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			System.err.println();
			System.err.println(e.getMessage());
		}
		
		// Doing subtract
		ISubtract subObject;
		try 
		{
			subObject = (ISubtract) ProxyStubCompiler.giveStub("ISubtract", ISubtract.class, registryIpAddr, registryPort);
			//We did not wrap the giveSTUB() method just for the sake for clarity for grading but it could be abstracted from the App. programmers as well.
			System.out.println("\nSubtract: 8 with 9.");
			int sum = subObject.Subtract(8, 9); //Call a Remote Method Subtract with the Respective Parameters.
			System.out.println(sum);
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			System.err.println();
			System.err.println(e.getMessage());
		}


	}

}
