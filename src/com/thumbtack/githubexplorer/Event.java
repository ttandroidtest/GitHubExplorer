package com.thumbtack.githubexplorer;

public class Event {
	private String eventType;
	private String repoName;

	public Event(String eventType, String repoName) {
		this.eventType = eventType;
		this.repoName = repoName;
	}
	
	public class Builder {
		private String eventType;
		private String repoName;
		
		public Builder() {
			
		}
		public Builder setEventType(String type) {
			eventType = type;
			return this;
		}
		
		public Builder setRepoName(String name) {
			repoName = name;
			return this;
		}
		
		public Event create() {
			return new Event(eventType, repoName);
		}
	}
}
