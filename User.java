import jdk.internal.jshell.tool.resources.l10n;

public class User {
	private String name;
	private String role;
	private String username;
	private String password;
	private Database dbase;
	
	public User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}

	public String toString() {
		StringBuilder bob = new StringBuilder();
		bob.append("Name: ");
		bob.append(getName());
		bob.append("\n");
		bob.append("Role: ");
		bob.append(getRole());
		bob.append("\n");
		bob.append("Username: " + getUsername() + " Password: " + getPassword());
		bob.append("\n");
		return bob.toString();	
	}
}
