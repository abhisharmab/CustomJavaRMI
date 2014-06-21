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
public class RemoteRef implements ISignal{
	
	private static final long serialVersionUID = 1L;
	
	private String ip_Address;
	private Integer port;
	 //private String interface_Name; // We don't need this. because I added the class [] param.
	private String class_Name;
	private Class[] implementedInterfaces = null; //I need this becuase its an input parameter for the Proxy. 
	
	private String register_Name;
	
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
