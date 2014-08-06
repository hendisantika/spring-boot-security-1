/**
 * 
 */
package org.qifeng.sbs;

import org.qifeng.sbs.bean.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jackho
 *
 */
@Configuration
public class BeanConfig {
	
	@Bean
	public TestBean testBean() {
		return new TestBean();
	}
}
