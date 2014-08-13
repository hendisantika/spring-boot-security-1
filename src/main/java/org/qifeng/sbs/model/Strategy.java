package org.qifeng.sbs.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.access.prepost.PreAuthorize;

@Entity
@Table(name="strategy")
public class Strategy extends BaseEntity implements Serializable{
	
	/**
	 * jvm generated serialVersionUID
	 */
	private static final long serialVersionUID = 9081901697325170444L;
	
	@NotNull(message = "{error.strategy.type.null}")
	@NotEmpty(message = "{error.strategy.type.empty}")
	@Size(max = 20, message = "{error.strategy.type.max}")
	@Column(name = "Type" , length = 20)
	private String type;
	
	@NotNull(message = "{error.strategy.name.null}")
	@NotEmpty(message = "{error.strategy.name.empty}")
	@Size(max = 20, message = "{error.strategy.name.max}")
	@Column(name = "Name" , length =20)
	private String name;



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Strategy other = (Strategy) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Strategy [type=" + type + ", name=" + name + ", getId()="
				+ getId() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
