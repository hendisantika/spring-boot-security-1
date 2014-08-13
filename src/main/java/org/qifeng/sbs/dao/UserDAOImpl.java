/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.qifeng.sbs.exception.DuplicateUserException;
import org.qifeng.sbs.exception.UserNotFoundException;
import org.qifeng.sbs.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jackho
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {
	
	private static Logger LOG = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired 
	SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User getUser(int userId) throws UserNotFoundException {
		LOG.debug("UserDAOImpl.getUser() - [" + userId + " ]");
		User userObject = (User) getCurrentSession().get(User.class, userId);
		
		if (userObject == null) {
			throw new UserNotFoundException("User id [" + userId +"] not found");
		} else {
			return userObject;
		}
	}

	@Override
	public void addUser(User user) throws DuplicateUserException {
		LOG.debug("UserDAOImpl.save() - ["+ user.getUsername() +"]"); 
		getCurrentSession().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUser(String usersName) throws UserNotFoundException {
		LOG.debug("UserDAOImpl.getUser() - [" + usersName + "]");
		Query query = getCurrentSession().createQuery("from User where username = :usersName ");
		query.setString("usersName", usersName);
		
		LOG.debug(query.toString());
		if (query.list().size() == 0 ) {
			throw new UserNotFoundException("User [" + usersName + "] not found");
		} else {
			LOG.debug("User List Size: " + query.list().size());
			List<User> list = (List<User>)query.list();
	        User userObject = (User) list.get(0);

	        return userObject;
		}
	}

	@Override
	public void updateUser(User user) throws UserNotFoundException {
		User userToUpdate = getUser(user.getId());
		userToUpdate.setUsername(user.getUsername());
		userToUpdate.setPassword(user.getPassword());
		userToUpdate.setEnabled(user.isEnabled());
		userToUpdate.setRole(user.getRole());
		getCurrentSession().update(userToUpdate);
	}

	@Override
	public void deleteUser(int userId) throws UserNotFoundException {
		User user = getUser(userId);
		if ( user == null) {
			throw new UserNotFoundException("User ["+ userId +"] not found");
		} else {
			getCurrentSession().delete(user);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		return getCurrentSession().createQuery("from User").list();
	}
}
