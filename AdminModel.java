
public class AdminModel extends UserModel {

	public AdminModel(String name, String username, char[] password) {
		super(name, username, password);
		setRole("admin");
		// TODO Auto-generated constructor stub
	}

}
