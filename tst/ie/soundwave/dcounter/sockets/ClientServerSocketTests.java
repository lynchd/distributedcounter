package ie.soundwave.dcounter.sockets;

import ie.soundwave.dcounter.configuration.Configuration;
import ie.soundwave.dcounter.configuration.NodeConfiguration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import junit.framework.TestCase;

/**
 * An integration test to test both the server and client sockets. 
 * Here we will use an "echo" request handler to repeat a request back
 * to the client. 
 */
public class ClientServerSocketTests extends TestCase
{
	@Rule public ExpectedException exception = ExpectedException.none();
	/**
	 * I like to use descriptive test names as advocated by Roy Osherove
	 * in "The Art of Unit Testing"
	 */
	@Test public void test_ClientAndServer_GivenEverythingOk_EchoRequestInResponse()
		throws Exception
	{
		Configuration configuration = NodeConfiguration.buildDefaultConfiguration();
		
		TCPServer server = new TCPServer(configuration, new EchoRequestHandler());
		server.start();
		/** Spin-waits suck! */
		while(!server.isRunning()) {}
		TCPClient client = new TCPClient(configuration.getHostName(), configuration.getPort());
		String message = "ANYTHING";
		
		String echoResponse = (String)client.executeRequest(message);
		server.stopServer();
		
		assertEquals(message, echoResponse);
	}
	
	@Test public void test_ClientServer_MultipleClients_MultipleEchos() {
		Configuration configuration = NodeConfiguration.buildDefaultConfiguration();
		
		TCPServer server = new TCPServer(configuration, new EchoRequestHandler());
		server.start();
		while(!server.isRunning()) {}
		TCPClient client1 = new TCPClient(configuration.getHostName(), configuration.getPort());
		String message1 = "FIRST";
		TCPClient client2 = new TCPClient(configuration.getHostName(), configuration.getPort());
		String message2= "SECOND";
		TCPClient client3 = new TCPClient(configuration.getHostName(), configuration.getPort());
		String message3 = "THIRD";
		
		String echoResponse1 = (String)client1.executeRequest(message1);
		String echoResponse2 = (String)client2.executeRequest(message2);
		String echoResponse3 = (String)client3.executeRequest(message3);
		server.stopServer();
		
		assertEquals(message1, echoResponse1);
		assertEquals(message2, echoResponse2);
		assertEquals(message3, echoResponse3);
	}
	
	
	@Test public void test_Client_NoServer_ConnectionRefused() {
		Configuration configuration = NodeConfiguration.buildDefaultConfiguration();
		
		TCPClient client = new TCPClient(configuration.getHostName(), configuration.getPort());
		
		exception.expect(RuntimeException.class);
		boolean exceptionThrown =false;
		try {
			client.executeRequest("ANYTHING");
		}
		catch(RuntimeException ex) {
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
	}
}


