/**
 * 
 */
package org.qifeng.sbs.dao;

import java.util.List;

import org.qifeng.sbs.exception.DuplicatePermissionException;
import org.qifeng.sbs.exception.PermissionNotFoundException;
import org.qifeng.sbs.model.Permission;

/**
 * @author jackho
 *
 */
public interface PermissionDAO {
	
	public void addPermission(Permission permission) throws DuplicatePermissionException;
	 
    public Permission getPermission(int id) throws PermissionNotFoundException;
 
    public Permission getPermission(String permissionName) throws PermissionNotFoundException;
 
    public void updatePermission(Permission permission) throws PermissionNotFoundException;
 
    public void deletePermission(int id) throws PermissionNotFoundException;
  
    public List<Permission> getPermissions();
}
