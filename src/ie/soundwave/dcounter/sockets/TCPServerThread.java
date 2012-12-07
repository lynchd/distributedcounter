package ie.soundwave.dcounter.sockets;


import java.net.Socket;
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TCPServerThread extends Thread 
{
    private static Logger logger = Logger.getLogger(TCPServerThread.class);
    
    private Socket 	       socket;
    private RequestHandler requestHandler;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    
    public TCPServerThread(Socket socket, RequestHandler requestHandler) 
    {
        this.socket 		= socket;
        this.requestHandler = requestHandler;
    }

    private void openStreams() throws IOException {
        outStream 	= new ObjectOutputStream(socket.getOutputStream());
        inStream 	= new ObjectInputStream(socket.getInputStream());
    }
    
    private void executeRequestResponseCycle() throws Exception {
        Object request = inStream.readObject();
        logger.debug("Client Request - " + request);
        Object response = requestHandler.handleRequest(request); 
        logger.debug("Response - " +  response);
        outStream.writeObject(response);
    }
    
    private void cleanUp() throws IOException {
        outStream.close();
        inStream.close();
        socket.close();
        System.out.println("Complete");
    }
    
    public void run() 
    {
    	logger.info("Initiaiting ");
        try {
        	openStreams();
        	executeRequestResponseCycle();
        	cleanUp();
        } 
        catch (Exception ex) {
        	logger.error("Error in server thread", ex);
		}
    }
}
