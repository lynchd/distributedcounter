package ie.soundwave.dcounter.configuration;

public class NodeConfiguration implements Configuration 
{
	/**
	 * The alternative to making these private is needless
	 * amounts of boilerplate getters and setters.
	 */
	public int 		fileReplicationWaitSeconds;
	public int 		networkReplicationWaitSeconds;
	public String  	hostName;
	public int 		port;
	public String	replicationFileName;

	public static Configuration buildDefaultConfiguration() 
	{
		NodeConfiguration config = new NodeConfiguration();
		config.fileReplicationWaitSeconds = 1000;
		config.networkReplicationWaitSeconds = 5000;
		config.hostName = "localhost";
		config.port = 5321;
		config.replicationFileName = "/tmp/state";
		return config;
	}

	@Override
	public int getFileReplicationWaitSeconds() {
		return fileReplicationWaitSeconds;
	}

	@Override
	public String getHostName() {
		return hostName;
	}

	@Override
	public int getNetworkReplicationWaitSeconds() {
		return networkReplicationWaitSeconds;
	}

	@Override
	public String getReplicationFileName() {
		return replicationFileName;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getServerAndPort() {
		return hostName + ":" + port;
	}		
}
