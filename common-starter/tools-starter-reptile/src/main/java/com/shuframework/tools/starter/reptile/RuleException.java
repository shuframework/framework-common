package com.shuframework.tools.starter.reptile;

/**
 * 异常处理
 * @author shu
 *
 */
public class RuleException extends RuntimeException {

	public RuleException() {
		super();
		
	}

	public RuleException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public RuleException(String message) {
		super(message);
		
	}

	public RuleException(Throwable cause) {
		super(cause);
		
	}

}
