/**
 * Copyright Flexpay AB
 */
package com.muksia.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muksia.model.Bookmark;
import com.muksia.repository.BookmarkRepository;

@Service
public class BookmarksService {

	@Autowired
	private BookmarkRepository bookmarkRepository;

	public List<Bookmark> getAllBookmarks() {
		return bookmarkRepository.findAll();
	}

	public void addBookmark(final String link, final String description, final String category) {
		bookmarkRepository.save(new Bookmark(link, description, category));
	}

	public void editBookmark(final String link, final String description, final String category) {
		bookmarkRepository.save(new Bookmark(link, description, category));
	}

}
