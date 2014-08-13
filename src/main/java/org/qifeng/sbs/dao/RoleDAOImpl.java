/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.qifeng.sbs.exception.DuplicateRoleException;
import org.qifeng.sbs.exception.RoleNotFoundException;
import org.qifeng.sbs.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author jackho
 *
 */
@Repository
public class RoleDAOImpl implements RoleDAO{

	private static Logger LOG = LoggerFactory.getLogger(RoleDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addRole(Role role) throws DuplicateRoleException {
		LOG.debug("RoleDAOImpl.addRole() - [" + role.getRolename() + "]");
	 try{
		// if the role is not found, then a RoleNotFoundException is
        // thrown from the getRole method call, and the new role will be 
        // added.
        //
        // if the role is found, then the flow will continue from the getRole
        // method call and the DuplicateRoleException will be thrown.
        Role roleCheck = getRole(role.getRolename());
        String message = "The role [" + roleCheck.getRolename() + "] already exists";
        throw new DuplicateRoleException(message);
	 } catch (RoleNotFoundException e) {
		 getCurrentSession().save(role);
	 }
	}
	
	@Override
	public Role getRole(int id) throws RoleNotFoundException {
		Role role = (Role) getCurrentSession().get(Role.class, id);
		if (role == null) {
			throw new RoleNotFoundException("Role id ["+ id +"] not found ");
		} else {
			return role;
		}
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Role getRole(String roleName) throws RoleNotFoundException {
		LOG.debug("RoleDAOImpl.getRole() - ["+ roleName +"]  ");
		Query query = getCurrentSession().createQuery("from Role where rolename = :roleName");
		query.setString("roleName", roleName);
		
		LOG.debug(query.toString());
		if (query.list().size() == 0) {
			throw new RoleNotFoundException("Role ["+ roleName +"] not found");
		} else {
			LOG.debug("Role list size: " + query.list().size());
			List<Role> list = query.list();
			Role role = list.get(0);
			return role;
		}
	}

	
	@Override
	public void updateRole(Role role) throws RoleNotFoundException {
		Role roleToUpdate = getRole(role.getId());
		roleToUpdate.setId(role.getId());
		roleToUpdate.setRolename(role.getRolename());
	    getCurrentSession().update(roleToUpdate);
	}

	
	@Override
	public void deleteRole(int id) throws RoleNotFoundException {
		Role role = getRole(id);
		if ( role != null) {
			getCurrentSession().delete(role);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> getRoles() {
		return getCurrentSession().createQuery("from Role").list();
	}

}
