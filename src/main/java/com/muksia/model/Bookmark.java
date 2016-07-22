/**
 * Copyright Flexpay AB
 */
package com.muksia.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bookmark {

	@Id
	private String id;

	@JsonProperty("id")
	private String emberId;

	private String link;

	private String description;

	private String category;


	public String getEmberId() {
		return id;
	}

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

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final Bookmark bookmark = (Bookmark) o;

		if (link != null ? !link.equals(bookmark.link) : bookmark.link != null) {
			return false;
		}
		if (description != null ? !description.equals(bookmark.description) : bookmark.description != null) {
			return false;
		}
		return category != null ? category.equals(bookmark.category) : bookmark.category == null;

	}

	@Override
	public int hashCode() {
		int result = link != null ? link.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (category != null ? category.hashCode() : 0);
		return result;
	}
}
