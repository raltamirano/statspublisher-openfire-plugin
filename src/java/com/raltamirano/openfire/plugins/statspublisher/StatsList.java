package com.raltamirano.openfire.plugins.statspublisher;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="statistics")
public class StatsList {

	private List<StatsItem> items;
	
	public StatsList() {
		this.items = new ArrayList<StatsItem>();
	}

	@XmlElement(name="statistic")
	public List<StatsItem> getItems() {
		return this.items;
	}
		
	static class StatsItem {
		private String key;
		private double value;
		private String units;
		
		public StatsItem() {
			
		}
		
		public StatsItem(String key, double value, String units) {
			this.key = key;
			this.value = value;
			this.setUnits(units);
		}
		
		@XmlElement(name="metric")
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		
		@XmlElement
		public double getValue() {
			return value;
		}
		public void setValue(double value) {
			this.value = value;
		}

		@XmlElement
		public String getUnits() {
			return units;
		}

		public void setUnits(String units) {
			this.units = units;
		}
	}
}
