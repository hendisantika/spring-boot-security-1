/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.qifeng.sbs.exception.UserNotFoundException;
import org.qifeng.sbs.model.User;

/**
 * @author jackho
 *
 */
public interface UserDAO {
	
	public void addUser(User user);
	
	public User getUser(int userId) throws UserNotFoundException;
	
	public User getUser(String username) throws UserNotFoundException;
	
	public void updateUser(User user) throws UserNotFoundException;
	
	public void deleteUser(int userId) throws UserNotFoundException;
	
	public List<User> getUsers();
	
}
