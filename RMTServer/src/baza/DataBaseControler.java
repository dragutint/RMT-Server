package baza;

import java.util.ArrayList;

public class DataBaseControler {
	public ArrayList<User> users;
	
	public DataBaseControler() {
		users = new ArrayList<User>();
		User jedan = new User("duda", "123");
		users.add(jedan);
		User dva = new User("ivica", "321");
		users.add(dva);
	}

	public ArrayList<User> getUsers() {
		return users;
	}	
	
	public boolean proveriUsera(User u) {
		for (int i = 0; i < users.size(); i++) {
			if(users.get(i).getUser().equals(u.getUser()) && users.get(i).getPass().equals(u.getPass())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean postoji(String name) {
		for(int i = 0; i < users.size(); i++) {
			if(users.get(i).getUser().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void dodaj(User u) {
		users.add(u);
	}
}
