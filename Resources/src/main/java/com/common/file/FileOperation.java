package com.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

import com.common.exception.FileOperationException;

@Component
public class FileOperation {

	public Properties read(String fileName) throws FileNotFoundException, FileOperationException {
		Properties configProp = new Properties();
		File f = new File(fileName);
		if (f.exists() && f.isFile()) {
			InputStream in = new FileInputStream(fileName);
			try {
				configProp.load(in);
			} catch (IOException e) {
				throw new FileOperationException(e);
			}
		}
		return configProp;
	}
}
