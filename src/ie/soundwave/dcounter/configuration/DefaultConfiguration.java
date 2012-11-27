package ie.soundwave.dcounter.configuration;

public class DefaultConfiguration 
	implements Configuration
{
	private static DefaultConfiguration defaultConfig;
	
	public static DefaultConfiguration GetInstance() {
		if (defaultConfig==null) {
			defaultConfig = new DefaultConfiguration();
		}
		return defaultConfig;
	}
	public int getPort() {
		return 54321;
	}
	public String  getServer() {
		return "localhost";
	}
	
	@Override
	public String toString() {
		return new String("[Server=" + getServer() + ":" + getPort());
	}
}
