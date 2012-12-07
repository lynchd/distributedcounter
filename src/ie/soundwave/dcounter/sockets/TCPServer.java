package ie.soundwave.dcounter.sockets;

import ie.soundwave.dcounter.configuration.Configuration;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;

import org.apache.log4j.Logger;
import java.io.IOException;

public class TCPServer extends Thread 
{
    private static Logger logger = Logger.getLogger(TCPServerThread.class);
    
    private Configuration 	  configuration;
    private ServerSocket  	  serverSocket; 
    private RequestHandler	  requestHandler;
    private boolean 	      isRunning;
    
    public TCPServer(Configuration config, RequestHandler requestHandler) {
    	configuration 		= config;
    	isRunning 			= false;
    	this.requestHandler = requestHandler;
    }
	 
    public void run() {
        try {
        	startServer();
        	blockReceiveAndDelegate();
        } 
        catch (IOException ex) {
            logger.error("Server error", ex);        	
        }
        finally {
        	serverSocket 	= null;
        	isRunning 		= false;
        }
        System.out.println("Stopped");
    }
    
    public void stopServer() {
    	logger.info("Stopping server");
        try 
        {
            if (serverSocket != null)
                serverSocket.close();
        } catch (IOException ex) 
        {
        	logger.error("Error Stopping server", ex);
        }
    }
    
    public boolean isRunning() {
    	return isRunning;
    }
    
    public void startServer() 
    	throws IOException
    {
    	logger.info("Request-Response server starting with configuration " + configuration);
    	serverSocket = new ServerSocket(configuration.getPort());
        isRunning = true;
        logger.info("Server Started.");
    }
    
    /**
     * It's not a great idea to simply create a new thread per request, typically we may have
     * a managed thread pool here. For simplicity, will stick with this model for now. 
     */
    private void blockReceiveAndDelegate() 
    	throws IOException
    {
    	while (isRunning) {
    		Socket socket;
    		try {
    			socket = serverSocket.accept(); 
    		}
    		catch(SocketException ex) {
    			logger.info("Server has stopped listening, socket has probably been closed.");
    			return;
    		}
    	    new TCPServerThread(socket, requestHandler).start();
    	}
    }
}
