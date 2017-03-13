package au.gov.nsw.transport.endpoint;

public interface JmsClient {
	public void send(String msg);
	public String receive();
}
