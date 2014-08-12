/**
 * 
 */
package org.qifeng.sbs.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author jackho
 *
 */
@Entity
@Table(name="USERS")
public class User extends BaseEntity implements UserDetails{
	
	private static final long serialVersionUID = 6311364761937265306L;
	
	private static Logger LOG = LoggerFactory.getLogger(User.class);
	
	@NotNull(message = "error.user.username.null")
	@NotEmpty(message = "error.user.username.empty")
	@Size(max = 50 , message = "error.user.username.max")
	@Column(name = "username" , length = 50)
	private String username;
	
	@NotNull(message = "{error.user.password.null}")
	@NotEmpty(message = "{error.user.password.empty}")
	@Size(max = 50 , message = "{error.user.password.max}")
	@Column(name = "password" , length = 50)
	private String password;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	

	@Transient
	private Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}
	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
        return enabled;
    }
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return this.getEnabled();
	}
	@Override
	public boolean isAccountNonExpired() {
		 //return true = account is valid / not expired
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		//return true = account is not locked
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		//return true = password is valid / not expired
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (enabled != other.enabled)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password
				+ ", enabled=" + enabled + "]";
	}
	
	
}
