/**
 * 
 */
package org.qifeng.sbs.service;

import java.util.List;

import org.qifeng.sbs.exception.UserNotFoundException;
import org.qifeng.sbs.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author jackho
 *
 */
public interface UserService extends UserDetailsService{
	
	public void addUser(User user);
	
	public User getUser(int userId) throws UserNotFoundException;
	
	public User getUser(String username) throws UserNotFoundException;
	
	public void updateUser(User user) throws UserNotFoundException;
	
	public void deleteUser(int userId) throws UserNotFoundException;
	
	public List<User> getUsers();
}
