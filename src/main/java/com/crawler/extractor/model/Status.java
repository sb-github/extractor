package com.crawler.extractor.model;

/**
 *
 * @author Yevhenii R
 *
 * @data 05 January 2018
 * *
 *       <p>
 *       Class that represents crawler status;
 */

public enum Status {
    NEW("New"),
    IN_PROCESS("In process"),
    PROCESSED("Processed"),
    STOPPED("Stopped"),
    FAILED("Failed");

    private final String status;

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
		return this.status;
	}
}
