package com.common.exception;

public class FileOperationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4812139468122679169L;

	public FileOperationException(Exception e) {
		super(e);
	}
}