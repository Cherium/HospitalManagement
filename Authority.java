
public class Authority extends User {

	public Authority(String name, String username, String password) {
		super(name, username, password);
	}
	
	public String viewStats() {
		return "Viewed stats.\n";
	}

}
