package ie.soundwave.dcounter.server.api;

public interface CounterService {
	public CounterResponse increment();
	public CounterResponse decrement();
	public CounterResponse get();
}
