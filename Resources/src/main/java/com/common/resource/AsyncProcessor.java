package com.common.resource;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import com.common.event.EventMapper;
import com.common.exception.FileOperationException;

public class AsyncProcessor implements Runnable {

	private String filePath = null;
	private String fileName = null;
	private EventMapper eventMapper = null;
	private volatile boolean isWatching = true;

	public AsyncProcessor(String filePath, String fileName, EventMapper eventMapper) {
		this.filePath = filePath;
		this.fileName = fileName;
		this.eventMapper = eventMapper;
	}

	public void setWatching(boolean isWatching) {
		this.isWatching = isWatching;
	}

	@Override
	public void run() {
		while (isWatching) {
			if (Thread.interrupted()) {
				return;
			}
			Path path = Paths.get(filePath);
			WatchService watcher;
			try {
				watcher = path.getFileSystem().newWatchService();
				path.register(watcher,StandardWatchEventKinds.ENTRY_MODIFY);

				WatchKey watckKey = watcher.take();

				List<WatchEvent<?>> events = watckKey.pollEvents();
				for (WatchEvent event : events) {
					if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
						System.out.println("MODIFIED");
						eventMapper.modifyEvent(filePath, fileName);
					}
				}
				//Thread.sleep(10);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (FileOperationException e) {
				e.printStackTrace();
			}
		}
	}
}