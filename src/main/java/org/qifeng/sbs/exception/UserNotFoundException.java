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
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

