package spamchecker;

public class EmailBody {
	private String body;

	/**
     * Constructs an EmailBody object with the given body text.
     * @param body The body of the email.
     */
    public EmailBody(String body) {
        this.body = body;
    }
    /**
     * Returns the body of the email.
     * @return The body text.
     */
    public String getBody() {
        return body;
    }
}