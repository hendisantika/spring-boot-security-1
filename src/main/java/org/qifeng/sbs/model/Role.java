/**
 * 
 */
package org.qifeng.sbs.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name="ROLES")
public class Role extends BaseEntity implements Serializable , GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4905213715625460748L;

	private static Logger LOG = LoggerFactory.getLogger(Role.class);
	
	@NotNull(message = "{error.roles.role.null}")
	@NotEmpty(message = "{error.roles.role.empty}")
	@Size(max = 50 , message = "{error.roles.role.max}")
	@Column(name = "rolename" , length = 50)
	private String rolename;
	
	

	// user_roles
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")}
	)
	private Set<User> userRoles;
	// role_permissions
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions",
			joinColumns = { @JoinColumn(name = "role_id" , referencedColumnName = "id")},
			inverseJoinColumns = { @JoinColumn(name = "permission_id", referencedColumnName = "id")}
	)
	
	
	private Set<Permission> permissions;
	
	@Override
	public String getAuthority() {
		return getRolename();
	}
	
	/**
	 * @return the permissions
	 */
	public Set<Permission> getPermissions() {
		return permissions;
	}

	/**
	 * @param permissions the permissions to set
	 */
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the rolename
	 */
	public String getRolename() {
		return rolename;
	}

	/**
	 * @param rolename the rolename to set
	 */
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	/**
	 * @return the userRoles
	 */
	public Set<User> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(Set<User> userRoles) {
		this.userRoles = userRoles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((rolename == null) ? 0 : rolename.hashCode());
		result = prime * result
				+ ((userRoles == null) ? 0 : userRoles.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		if (userRoles == null) {
			if (other.userRoles != null)
				return false;
		} else if (!userRoles.equals(other.userRoles))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Role [rolename=" + rolename + ", userRoles=" + userRoles + "]";
	}

	
	
	
	
}
