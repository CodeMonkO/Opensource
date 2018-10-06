package com.common.event;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.exception.FileOperationException;
import com.common.file.FileOperation;

@Component
public class EventMapper {

	private volatile Map<String, Properties> properties = null;
	
	@Autowired
	private FileOperation fileOperation;
	
	public void createEvent(String filePath, String fileName) throws FileNotFoundException, FileOperationException {
		Properties m = fileOperation.read(filePath+"/"+fileName);
		getProperties().put(fileName, m);
	}

	public void modifyEvent(String filePath, String fileName) throws FileNotFoundException, FileOperationException {
		Properties m = fileOperation.read(filePath+"/"+fileName);
		getProperties().put(fileName, m);
	}
	
	public void deleteEvent(String filePath, String fileName) {

	}
	
	public Map<String, Properties> getProperties() {
		if(properties==null){
			properties = new ConcurrentHashMap<String, Properties>();
		}
		return properties;
	}
	
	protected Object readResolve() 
    { 
        return properties; 
    } 

}
