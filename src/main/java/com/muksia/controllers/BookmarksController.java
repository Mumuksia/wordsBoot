/**
 * Copyright Flexpay AB
 */
package com.muksia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.muksia.services.BookmarksService;

@RestController
public class BookmarksController {

	@Autowired
	private BookmarksService bookmarksService;

	@RequestMapping("/bookmarks")
	public String getBookmarks() {
		return bookmarksService.getAllBookmarks().toString();
	}


	@RequestMapping(method = RequestMethod.GET, path = "/bookmark/{link}/{descrption}/{category}")
	public String addbookmark(@PathVariable("link") String link, @PathVariable("descrption") String descrption,
							  @PathVariable("category") String category) {
		bookmarksService.addBookmark(link, descrption, category);
		return "ok";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/editBookmark/{link}/{descrption}/{category}/{id}")
	public String editBookmark(@PathVariable("link") String link, @PathVariable("descrption") String descrption,
							   @PathVariable("category") String category,   @PathVariable("id") String id) {
		bookmarksService.editBookmark(link, descrption, category, id);
		return "ok";
	}
}
