package reply.service;

import java.util.Map;

public class ModifyReplyRequest {
	private String userId;
	private int replyNo;
	private String body;
	
	public String getUserId() {
		return userId;
	}
	public int getReplyNo() {
		return replyNo;
	}
	public String getBody() {
		return body;
	}
	
	public ModifyReplyRequest(String userId, int replyNo, String body) {
		super();
		this.userId = userId;
		this.replyNo = replyNo;
		this.body = body;
	}
	
	public void validate(Map<String, Boolean> errors) {
		if (body == null || body.trim().isEmpty()) {
			errors.put("body", true);
		}
	}
	
}
