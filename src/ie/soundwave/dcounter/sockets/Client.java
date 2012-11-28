package ie.soundwave.dcounter.sockets;

import ie.soundwave.dcounter.configuration.Configuration;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket 				      socket;
    private ObjectOutputStream 	outStream;
    private ObjectInputStream  	inStream;
    private Configuration 		  configuration;
    
    public Client(Configuration configuration) {
    	this.configuration = configuration;
    }
    
    public Object executeRequest(Object request) 
    {
    	Object response;
        try 
        {
        	initializeConnection();
        	flushRequest(request);
        	response = getResponse();
        	closeConnection();
        } 
        catch (Exception ex) {
        	throw new RuntimeException(ex);
        }        
        return response;
    }
    
    private void initializeConnection() 
    	throws Exception
    {
    	 socket	     = new Socket(configuration.getServer(), configuration.getPort());
         outStream 	 = new ObjectOutputStream(socket.getOutputStream());
         inStream 	 = new ObjectInputStream(socket.getInputStream());
    }
    
    private void closeConnection() 
    	throws Exception
    {
        outStream.close();
        inStream.close();
        socket.close();
    }
    
    private void flushRequest(Object request) 
    	throws Exception
    {
    	outStream.writeObject(request);
        outStream.flush();
    }
    
    private Object getResponse() 
    	throws Exception
    {
    	return inStream.readObject();
    }
}