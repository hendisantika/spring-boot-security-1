/**
 * 
 */
package org.qifeng.sbs.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author jackho
 *
 */
@Entity
@Table(name = "PERMISSIONS")
public class Permission extends BaseEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 957481236753472335L;

	private static Logger LOG = LoggerFactory.getLogger(Permission.class);
	
	@NotNull(message = "{error.permission.permissionname.null}")
	@NotEmpty(message = "{error.permission.permissinname.empty}")
	@Size(max = 50 , message = "{permission.permission.role.max}")
	@Column(name = "permissionname", length = 50)
	private String permissionname;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions",
			joinColumns = {@JoinColumn(name  = "permission_id",referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
	)
	private Set<Role> permRoles;
	
	@Override
	public String getAuthority() {
		return permissionname;
	}
	
	
	/**
	 * @return the permissionname
	 */
	public String getPermissionname() {
		return permissionname;
	}




	/**
	 * @param permissionname the permissionname to set
	 */
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}




	/**
	 * @return the permRoles
	 */
	public Set<Role> getPermRoles() {
		return permRoles;
	}




	/**
	 * @param permRoles the permRoles to set
	 */
	public void setPermRoles(Set<Role> permRoles) {
		this.permRoles = permRoles;
	}


	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((permRoles == null) ? 0 : permRoles.hashCode());
		result = prime * result
				+ ((permissionname == null) ? 0 : permissionname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (permRoles == null) {
			if (other.permRoles != null)
				return false;
		} else if (!permRoles.equals(other.permRoles))
			return false;
		if (permissionname == null) {
			if (other.permissionname != null)
				return false;
		} else if (!permissionname.equals(other.permissionname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permission [permissionname=" + permissionname + ", permRoles="
				+ permRoles + "]";
	}
	
	

}
