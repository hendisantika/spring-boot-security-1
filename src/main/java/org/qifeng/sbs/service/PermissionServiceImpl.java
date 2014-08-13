/**
 * 
 */
package org.qifeng.sbs.service;

import java.util.List;

import org.qifeng.sbs.dao.PermissionDAO;
import org.qifeng.sbs.exception.DuplicatePermissionException;
import org.qifeng.sbs.exception.PermissionNotFoundException;
import org.qifeng.sbs.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jackho
 *
 */
public class PermissionServiceImpl implements PermissionService {

	@Autowired
    private PermissionDAO permissionDAO;
 
    @Override
    public void addPermission(Permission permission) throws DuplicatePermissionException {
        permissionDAO.addPermission(permission);
    }
 
    @Override
    public Permission getPermission(int id) throws PermissionNotFoundException {
        return permissionDAO.getPermission(id);
    }
 
    @Override
    public Permission getPermission(String permissionname) throws PermissionNotFoundException {
        return permissionDAO.getPermission(permissionname);
    }
 
    @Override
    public void updatePermission(Permission permission) throws PermissionNotFoundException {
        permissionDAO.updatePermission(permission);
    }
 
    @Override
    public void deletePermission(int id) throws PermissionNotFoundException {
        permissionDAO.deletePermission(id);
    }
 
    @Override
    public List<Permission> getPermissions() {
        return permissionDAO.getPermissions();
    }

}
