/**
 * 
 */
package org.qifeng.sbs.service;

import java.util.List;

import org.qifeng.sbs.dao.RoleDAO;
import org.qifeng.sbs.exception.DuplicateRoleException;
import org.qifeng.sbs.exception.RoleNotFoundException;
import org.qifeng.sbs.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jackho
 *
 */
@Service 
@Transactional
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public void addRole(Role role) throws DuplicateRoleException {
		roleDAO.addRole(role);
	}


	@Override
	public Role getRole(int id) throws RoleNotFoundException {
		return roleDAO.getRole(id); 			
	}


	@Override
	public Role getRole(String rolename) throws RoleNotFoundException {
		return roleDAO.getRole(rolename);
	}


	@Override
	public void updateRole(Role role) throws RoleNotFoundException {
		roleDAO.updateRole(role);
	}

	
	@Override
	public void deleteRole(int id) throws RoleNotFoundException {
		roleDAO.deleteRole(id);
	}

	
	@Override
	public List<Role> getRoles() {
		return roleDAO.getRoles();
	}

}
