/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.qifeng.sbs.exception.DuplicatePermissionException;
import org.qifeng.sbs.exception.PermissionNotFoundException;
import org.qifeng.sbs.model.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jackho
 *
 */
public class PermissionDAOImpl implements PermissionDAO {
	
	private static Logger LOG = LoggerFactory.getLogger(PermissionDAOImpl.class);

	@Autowired 
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	

	@Override
	public void addPermission(Permission permission)
			throws DuplicatePermissionException {
		LOG.debug("PermissionDAOImpl.addPermission() - ["+ permission.getPermissionname() +"]  ");
		try {
            // if the permission is not found, then a PermissionNotFoundException is
            // thrown from the getPermission method call, and the new permission will be 
            // added.
            //
            // if the permission is found, then the flow will continue from the getPermission
            // method call and the DuplicatePermissionException will be thrown.
            Permission permCheck = getPermission(permission.getPermissionname());
            String message = "The permission [" + permCheck.getPermissionname() + "] already exists";
            throw new DuplicatePermissionException(message);
        } catch (PermissionNotFoundException e) {
            getCurrentSession().save(permission);
        }
	}


	@Override
	public Permission getPermission(int id) throws PermissionNotFoundException {
		Permission permObject = (Permission) getCurrentSession().get(Permission.class, id);
		if (permObject != null) {
			throw new PermissionNotFoundException("Permission id ["+ id +"] ");
		} else {
			return permObject;
		}
		
	}

	@SuppressWarnings("uncheck")
	@Override
	public Permission getPermission(String permissionName)
			throws PermissionNotFoundException {
		LOG.debug("PermissionDAOImpl.getPermission() - ["+ permissionName +"]  ");
		Query query = getCurrentSession().createQuery("from Permission where permissionname = :permissionName");
		query.setString("permissionName", permissionName);
		
		LOG.debug(query.toString());
		if (query.list().size() == 0) {
			throw new PermissionNotFoundException("Permission ["+ permissionName +"] not found");
		} else {
			LOG.debug("Permission list size: "+ query.list().size());
			List<Permission> list = query.list();
			Permission permObject = list.get(0);
			return permObject;
		}
	}


	@Override
	public void updatePermission(Permission permission)
			throws PermissionNotFoundException {
		Permission permissionToUpdate = getPermission(permission.getId());
		permissionToUpdate.setId(permission.getId());
		permissionToUpdate.setPermissionname(permission.getPermissionname());
		getCurrentSession().update(permissionToUpdate);
	}


	@Override
	public void deletePermission(int id) throws PermissionNotFoundException {
		Permission permission = getPermission(id);
		if (permission != null) {
			getCurrentSession().delete(permission);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Permission> getPermissions() {
		return getCurrentSession().createQuery("from Permission").list();
	}
	
}
