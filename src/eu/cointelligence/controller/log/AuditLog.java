package eu.cointelligence.controller.log;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.ServletContext;


public class AuditLog {
	
	private ServletContext context;

	public AuditLog(ServletContext context) {
		this.context = context;
	}
	
	public boolean log(String operation, String user, boolean status, String ip) {
		return log(operation, user, status, ip, null);
	}

	public synchronized boolean log(String operation, String user, boolean status, String ip, String message) {
		String auditLogPath = context.getRealPath("/WEB-INF/logs/audit.log");
		System.out.println("auditlog " + auditLogPath);
		FileWriter fileWriter = null;
		File auditLog = new File(auditLogPath);
		try {
			if (!auditLog.exists()) {
				auditLog.getParentFile().mkdirs();
				auditLog.createNewFile();
			}
			fileWriter = new FileWriter(auditLogPath, true);
			fileWriter.write("Operation " + operation + " for user " + user + " - " + (status?"SUCCESS":"FAILED") + " from ip " + ip +  " at " + new Date().toString() + (message != null?" reason" + message + " " : "") + "\n");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public synchronized LinkedList<String[]> getAuditLogsForUser(String username) {
		String auditLogPath = context.getRealPath("/WEB-INF/logs/audit.log");
		BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(auditLogPath);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			LinkedList<String[]> list = new LinkedList<String[]>();
			while (line != null) {
				String[] l = line.split(" ");
				if (l[4].trim().equals(username)) {
					String result[] = new String[] {
							l[1], l[4], l[6], l[9]
					};
					
					list.add(result);
				}
				line = bufferedReader.readLine();
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
