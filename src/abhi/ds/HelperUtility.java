package abhi.ds;

/* Helper Utility Class hold Systemic constants and Common utility functions that will be used by all other things in the system
 * The sendSignal method is used both by the Process manager and the Worker to establish the Socket Connection with the server socket on the other end
 * and send its messages to them.
 * */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HelperUtility {
	

//Send an object over the Wire	
public static void sendSignal(Socket socket, Object object) throws RuntimeException, IOException
{
	    if (socket == null) {
	        throw new RuntimeException("Invalid Socket.");
	      }
	      ObjectOutputStream objOut = null;
	      try 
	      {
	    	  objOut = new ObjectOutputStream(socket.getOutputStream());
	    	  objOut.writeObject(object);
	      } 
	      catch (IOException e) 
	      {
	        e.printStackTrace();
	        throw e;
	      }
}

//Receive and Object over the Wire
public static Object receiveSignal(Socket socket) throws RuntimeException, IOException{
    if (socket == null) {
      throw new RuntimeException("Invalid Socket.");
    }
    ObjectInputStream objIn = null;
    Object signal = null;
    try 
    {
    	objIn = new ObjectInputStream(socket.getInputStream());
    	signal = objIn.readObject();
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
      throw e;
    } 
    catch (ClassNotFoundException e) 
    {
      e.printStackTrace();
    }

    return signal;
 }


}
