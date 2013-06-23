package com.raltamirano.openfire.plugins.statspublisher;

import java.util.Date;
import java.util.Map.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jivesoftware.openfire.stats.Statistic;
import org.jivesoftware.openfire.stats.StatisticsManager;
import org.jivesoftware.util.JiveGlobals;

@Path("statspublisher-openfire-plugin/")
public class StatsService {

	@Path(value="stats")
	@GET
	@Produces(value={MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAllStats() {
		return getStatistics();
	}
	
	@Path(value="stats/json")
	@GET
	@Produces(value=MediaType.APPLICATION_JSON)
	public Response getAllStatsJson() {
		return getStatistics();
	}
		
	@Path(value="stats/xml")
	@GET
	@Produces(value=MediaType.APPLICATION_XML)
	public Response getAllStatsXml() {				
		return getStatistics();	
	}
	
	private Response getStatistics() {
		StatsList values = new StatsList();
		for (Entry<String, Statistic>  statsEntry : StatisticsManager.getInstance().getAllStatistics()) {
			values.getItems().add(new StatsList.StatsItem(statsEntry.getKey(), statsEntry.getValue().sample(), statsEntry.getValue().getUnits()));
		}		
		
		final long expiryOffset = JiveGlobals.getLongProperty("statspublisher.stats.cacheTimeMillis", 30 * 1000); // Default cache time: 30 seconds
		final long currentTime = System.currentTimeMillis();
		
		return Response
				.ok(values)
				.lastModified(new Date(currentTime))
				.expires(new Date(currentTime+expiryOffset))
				.build();
	}	
}
