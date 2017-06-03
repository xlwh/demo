package models;

import java.util.List;

public interface IUserOperation {
	public User selectUserByID(int id);
	public List<User> selectUsers(String userName);
	public void addUser(User user);
}
