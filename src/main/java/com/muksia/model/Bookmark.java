/**
 * Copyright Flexpay AB
 */
package com.muksia.model;

import org.springframework.data.annotation.Id;

public class Bookmark {

	@Id
	private String id;

	private String link;

	private String description;

	private String category;

	public Bookmark(final String link, final String description, final String category) {
		this.link = link;
		this.description = description;
		this.category = category;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getCategory() {
		return category;
	}
}
