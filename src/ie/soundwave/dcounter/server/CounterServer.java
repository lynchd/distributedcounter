/**
 * Used to fan concurrent client requests into sequential
 * transformations of state. There are a number of benefits here. 
 * 
 * I have already established in the documentation that increments, 
 * decrements and merges do not need to be totally ordered, so order of
 * arrival is irrelevant. Therefore this queue can be used as a means
 * of decreasing mutation latency by taking processing off the critical
 * request/response path. 
 * 
 * Also, by using a single consumer of this queue to manage the state, I 
 * can make all mutation operations lock and contention free.
 * 
 * Note - Operations result in mutations, therefore queries 
 *        will not appear on this queue, but rather occur directly
 **/
public class CounterServer {
    private BlockingQueue<Operation> operationsQueue;
    private Server                   tcpServer;
    private Configuration            configuration;
    private State                    nodeState;
    private Replication              diskReplication;
    private NetworkReplication       networkReplication;
    
    public start() 
    {
    }
    
    public stop()
    {
    }   
}