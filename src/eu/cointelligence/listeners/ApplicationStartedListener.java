package eu.cointelligence.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import eu.cointelligence.controller.log.AuditLog;

@WebListener
public final class ApplicationStartedListener implements ServletContextListener {

	private ServletContext context = null;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();
		AuditLog auditLog = new AuditLog(context);
		context.setAttribute("auditLog", auditLog);
		System.out.println("application started...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {		
		context.removeAttribute("auditLog");
		System.out.println("application stopping...");
	}
}
