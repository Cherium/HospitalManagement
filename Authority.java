
public class Authority extends User {

	public Authority(String name, String username, char[] password) {
		super(name, username, password);
		setRole("authority");
		//Originally head, now uses authority
	}

	public String viewStats() {
		return "Viewed stats.\n";
	}

}
