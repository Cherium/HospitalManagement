
public class Authority extends User {

	public Authority(String name, String username, String password) {
		super(name, username, password);
		setRole("head");
	}
	
	public String viewStats() {
		return "Viewed stats.\n";
	}

}
