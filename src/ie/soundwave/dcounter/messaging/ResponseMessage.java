package ie.soundwave.dcounter.messaging;

public class ResponseMessage 
{
	private ActionResult result;
	private String message;
	private int value;
	
	public ResponseMessage(ActionResult query, String message, int value) {
		this.result=query;
		this.message=message;
		this.value=value;
	}
	
	public ActionResult getResult() {
		return result;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public boolean isGood() {
		return result==ActionResult.SUCCESS;
	}
}
