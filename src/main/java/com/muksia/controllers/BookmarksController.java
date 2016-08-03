/**
 * Copyright Flexpay AB
 */
package com.muksia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.muksia.model.Bookmark;
import com.muksia.services.BookmarksService;

@CrossOrigin
@RestController
@RequestMapping("/bookmarks")
public class BookmarksController {

	@Autowired
	private BookmarksService bookmarksService;


	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public List<Bookmark> getBookmarks() {
		return bookmarksService.getAllBookmarks();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Bookmark addbookmark(@RequestBody Bookmark bookmark) {
		return bookmarksService.addBookmark(bookmark);
	}


	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	@ResponseBody
	public Bookmark editBookmark(@RequestBody Bookmark bookmark, @PathVariable("id") String id) {
		return bookmarksService.editBookmark(bookmark, id);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	@ResponseBody
	public List<Bookmark> deleteBookmark(@PathVariable("id") String id) {
		bookmarksService.deleteBookmark(id);
		return getBookmarks();
	}
}
