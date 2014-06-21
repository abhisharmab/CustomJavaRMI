/**
 * 
 */
package abhi.ds;

import java.util.ArrayList;


/**
 * @author abhisheksharma, dkrew0213
 *This class encapsulated the Remote REFERENCE> 
 *Which is nothing but the MetaData about the Remote Object. This meta-data will be used to basiclaly  create the STUB.
 *This meta is also pretty much registered in the registry
 */
public class LookupSignal extends BaseSignal{
	
	private static final long serialVersionUID = 1L;
	
	private String lookup_Name;
	
	public LookupSignal(){
		setSignalType(SignalType.LookUp);
	}
	
	public LookupSignal(String name){
		setSignalType(SignalType.LookUp);
		setLookup_Name(name);
	}

	public String getLookup_Name() {
		return lookup_Name;
	}

	public void setLookup_Name(String lookup_Name) {
		this.lookup_Name = lookup_Name;
	}

	


}
