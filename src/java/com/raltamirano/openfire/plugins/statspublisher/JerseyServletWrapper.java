package com.raltamirano.openfire.plugins.statspublisher;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jivesoftware.admin.AuthCheckFilter;

import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.spi.container.servlet.ServletContainer;

/**
 * Auto configured Jersey Servlet. All Resource classes should be at the same package or deeper.
 * @author Dmitriy Savascool
 */
public class JerseyServletWrapper extends ServletContainer {
	 
	private static final long serialVersionUID = 1L;

	private static final String SERVLET_URL = "statspublisher-openfire-plugin/*";
	private static final String SCAN_PACKAGE_KEY = "com.sun.jersey.config.property.packages";
	private static final String SCAN_PACKAGE_DEFAULT = JerseyServletWrapper.class.getPackage().getName();

	private static final String RESOURCE_CONFIG_CLASS_KEY = "com.sun.jersey.config.property.resourceConfigClass";
	private static final String RESOURCE_CONFIG_CLASS = "com.sun.jersey.api.core.PackagesResourceConfig";

	private static Map<String, Object> config;
	private static PackagesResourceConfig prc;

	static {
		config = new HashMap<String, Object>();
		config.put(RESOURCE_CONFIG_CLASS_KEY, RESOURCE_CONFIG_CLASS);
		config.put(SCAN_PACKAGE_KEY, SCAN_PACKAGE_DEFAULT);
		
		prc = new PackagesResourceConfig(SCAN_PACKAGE_DEFAULT);
		prc.setPropertiesAndFeatures(config);
		prc.getClasses().add(StatsService.class);
	}

	public JerseyServletWrapper() {
		super(prc);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		AuthCheckFilter.addExclude(SERVLET_URL);
	}

	@Override
	public void destroy() {
		super.destroy();
		AuthCheckFilter.removeExclude(SERVLET_URL);
	}
	
	@Override
	public void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}
}