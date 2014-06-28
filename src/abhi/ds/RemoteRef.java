/**
 * 
 */
package abhi.utility;

import java.util.ArrayList;

/**
 * @author abhisheksharma, dkrew0213
 *This class encapsulated the Remote REFERENCE> 
 *Which is nothing but the MetaData about the Remote Object. 
 */

//RemoteRef is the meta-data about the Remotely Callable Objects.

public class RemoteRef implements ISignal{
	
	private static final long serialVersionUID = 1L;
	
	private String ip_Address;
	private Integer port;

	private String class_Name;
	private Class[] implementedInterfaces = null;
	
	private String register_Name; //This wil the lookup name as well for the clients to lookup names as well.
	
	public Class[] getInterfaces() {
		return implementedInterfaces;
	}
	
	public void setInterfaces(Class[] implementedInterfaces) {
		this.implementedInterfaces = implementedInterfaces;
	}
	
	public String getClass_Name() {
		return class_Name;
	}
	
	public void setClass_Name(String class_Name) {
		this.class_Name = class_Name;
	}
	
	public String getIp_Address() {
		return ip_Address;
	}
	
	public void setIp_Address(String ip_Address) {
		this.ip_Address = ip_Address;
	}
	public Integer getPort() {
		return port;
	}
	
	public void setPort(Integer port) {
		this.port = port ;
	}

	public String getRegister_Name() {
		return register_Name;
	}

	public void setRegister_Name(String register_Name) {
		this.register_Name = register_Name;
	}


}
