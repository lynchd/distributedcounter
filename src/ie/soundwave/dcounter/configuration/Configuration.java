package ie.soundwave.dcounter.configuration;

public interface Configuration 
{
	public String  getHostName();
	public int 	   getPort();
	public String  getReplicationFileName();
	public int	   getFileReplicationWaitSeconds();
	public int 	   getNetworkReplicationWaitSeconds();
	public String  getServerAndPort();
}
