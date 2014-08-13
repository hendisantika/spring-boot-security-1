/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.qifeng.sbs.exception.DuplicateRoleException;
import org.qifeng.sbs.exception.RoleNotFoundException;
import org.qifeng.sbs.model.Role;

/**
 * @author jackho
 *
 */
public interface RoleDAO {
	public void addRole(Role role) throws DuplicateRoleException;

    public Role getRole(int id) throws RoleNotFoundException;

    public Role getRole(String roleName) throws RoleNotFoundException;

    public void updateRole(Role role) throws RoleNotFoundException;

    public void deleteRole(int id) throws RoleNotFoundException;

    public List<Role> getRoles();
}	
