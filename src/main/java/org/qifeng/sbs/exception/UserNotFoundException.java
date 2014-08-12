/**
 * 
 */
package org.qifeng.sbs.exception;


/**
 * @author jackho
 *
 */
public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 963968014058758889L;

	public UserNotFoundException() {
		super();
	}
	public UserNotFoundException(String message) {
		super(message);
	}
}

