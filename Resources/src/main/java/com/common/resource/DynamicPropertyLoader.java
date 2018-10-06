package com.common.resource;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.event.EventMapper;
import com.common.exception.DynamicPropertyLoaderException;
import com.common.exception.FileOperationException;

@Component
public class DynamicPropertyLoader {

	@Autowired
	EventMapper eventMapper;

	private String filePath = null;
	private String fileName = null;

	public DynamicPropertyLoader(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public void init() throws DynamicPropertyLoaderException, FileNotFoundException, FileOperationException {
		eventMapper.createEvent(filePath, fileName);
		AsyncProcessor asyncProcessor = new AsyncProcessor(filePath, fileName, eventMapper);
		Thread t = new Thread(asyncProcessor);
		t.start();
	}

}