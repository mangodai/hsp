package web.store.message.domain;

public class Message {
	private String mid;
	private String mname;
	private String email;
	private String subject;
	private String text;
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", mname=" + mname + ", email=" + email
				+ ", subject=" + subject + ", text=" + text + "]";
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
