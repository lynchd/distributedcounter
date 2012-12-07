package ie.soundwave.dcounter.server;

import ie.soundwave.dcounter.configuration.Configuration;
import ie.soundwave.dcounter.server.operations.Operation;
import ie.soundwave.dcounter.server.operations.OperationConsumer;
import ie.soundwave.dcounter.server.replicate.DiskReplication;
import ie.soundwave.dcounter.server.replicate.NetworkReplication;
import ie.soundwave.dcounter.server.replicate.Replication;
import ie.soundwave.dcounter.server.state.Node;
import ie.soundwave.dcounter.server.state.NodeState;
import ie.soundwave.dcounter.sockets.RequestHandler;
import ie.soundwave.dcounter.sockets.TCPServer;
import ie.soundwave.dcounter.sockets.TCPServerThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

public class CounterServer extends Thread 
{
    /**
     * Single threaded fan-in queue for operations that 
     * mutate state. Queries happen directly, and do not 
     * appear in this queue.
     */
	private BlockingQueue<Operation> operationsQueue;
    private OperationConsumer		 operationsConsumer;
    /**
     * TCPServer that handles multiple concurrent clients.
     */
    private TCPServer                tcpServer;
    /**
     * Interesting internal node state.
     */
    private NodeState                nodeState;
    /**
     * Replicating to disk periodically protects us from 
     * local failure, particularly for cases in which we have
     * not or cannot replicate out across the network (e.g. 
     * there are no other nodes in the cluster)
     */
    private Replication              diskReplication;
    /**
     * Replicating out across the cluster not 
     * only protects us from failure, but facilitates eventual
     * consistency across the cluster.
     */
    private Replication		         networkReplication;
    /**
     * Actual translation of user actions into requests/responses
     */
    private RequestHandler			 clientRequestHandler;
    
    private boolean isRunning = false;
    
    private static Logger logger = Logger.getLogger(TCPServerThread.class);
    
    public CounterServer(Configuration configuration) {
    	nodeState		     = new NodeState(new Node(configuration.getHostName(),configuration.getPort()));
    	operationsQueue      = new LinkedBlockingQueue<Operation>();
    	operationsConsumer   = new OperationConsumer(operationsQueue, nodeState);
    	clientRequestHandler = new ServerRequestHandler(operationsQueue, nodeState);
    	tcpServer		     = new TCPServer(configuration, clientRequestHandler);
    	diskReplication		 = new DiskReplication(configuration.getFileReplicationWaitSeconds(),
    											   configuration.getReplicationFileName());
    	diskReplication.setState(nodeState);
    	networkReplication	 = new NetworkReplication(configuration.getNetworkReplicationWaitSeconds());
    	networkReplication.setState(nodeState);
    }
    
    public void startServer() {
    	try {
    		logger.info("Registering graceful shutdown hook");
    		Runtime.getRuntime().addShutdownHook(new Thread() {
    			public void run() { 
    				stopServer();
    		    }
    		}); 
    		logger.info("Starting CounterServer.");
    		operationsConsumer.startConsuming();
    		diskReplication.startReplication();
    		tcpServer.startServer();
    		networkReplication.startReplication();
    		isRunning=true;
    		start();
    		logger.info("CounterServer Started.");
    	}
    	catch(Exception ex) {
    		logger.error("Unable to start CounterServer", ex);
    	}
    }
    
    public void stopServer() {
    	try {
    		operationsConsumer.stopConsuming();
    		diskReplication.flush();
    		networkReplication.flush();
    		diskReplication.stopReplication();
    		networkReplication.stopReplication();
    		tcpServer.stopServer();
    		join();
    	}
    	catch(Exception ex) {
    		logger.error("Exception while gracefully trying to shut down server", ex);
    	}

    }
    
    public void run() {
		while(isRunning) {
			/** Spin - see the shutdown in startServer hook for graceful shutdown on CTRL+C */
    	}
    }	
    
    /**
     * Arguments are 
     * 		Configuration Filename
     */
    public static void main(String argsv[]) {
    	
    }
}