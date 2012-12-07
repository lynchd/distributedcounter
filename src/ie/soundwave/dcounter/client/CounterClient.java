package ie.soundwave.dcounter.client;

import org.apache.log4j.Logger;

public class CounterClient 
{
	private static Logger logger = Logger.getLogger(CounterClient.class);
	
	private static String hostName;
	private static int 	  hostPort;
	private static String replayFile;
	
	/**
	 * Arguments are 
	 *		Host Name
	 *		Host Port
	 *		Replay File (Optional)
	 */
	public static void main(String argsv[]) 
	{
		hostName = argsv[0];
		hostPort = Integer.parseInt(argsv[1]);
		if (argsv.length>2) {
			replayFile = argsv[3];
		}
		logger.info("Distributed Counter Client");
		logger.info("Target Host - " + hostName);
		logger.info("Target Port - " + hostPort);
		logger.info("Replay File - " + replayFile);
		
		if(replayFile!=null) {
			actAsReplayClient();
		}
		else {
			actAsInteractiveClient();
		}
	}
	
	public static void actAsReplayClient() 
	{
		
	}
	
	public static void actAsInteractiveClient() 
	{
		
	}
	
	
}
