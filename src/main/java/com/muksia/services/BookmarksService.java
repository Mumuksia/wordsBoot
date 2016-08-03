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

	public Bookmark addBookmark(final Bookmark bookmark) {
		return bookmarkRepository.save(bookmark);
	}

	public Bookmark editBookmark(final Bookmark bookmark, final String id) {
		bookmarkRepository.delete(id);
		return bookmarkRepository.save(bookmark);
	}

	public void deleteBookmark(final String bookmarkId) {
		bookmarkRepository.delete(bookmarkId);
	}

	public void setBookmarkRepository(final BookmarkRepository bookmarkRepository) {
		this.bookmarkRepository = bookmarkRepository;
	}

}
