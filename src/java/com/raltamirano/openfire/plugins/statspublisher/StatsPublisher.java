package com.raltamirano.openfire.plugins.statspublisher;

import java.io.File;
import java.util.Date;

import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;

public class StatsPublisher implements Plugin {
	
	public void initializePlugin(PluginManager pluginManager, File pluginsDirectory) {
		System.out.println("StatsPublisher initialized @ " + new Date());		
	}

	public void destroyPlugin() {
		System.out.println("StatsPublisher destroyed @ " + new Date());		
	}
}
