package com.muksia.services;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.muksia.model.Bookmark;
import com.muksia.repository.BookmarkRepository;

public class BookmarksServiceTest {

	private final BookmarksService bookmarksService = new BookmarksService();

	@Test
	public void testGetAllBookmarks() throws Exception {
		BookmarkRepository bookmarkRepository = EasyMock.mock(BookmarkRepository.class);
		EasyMock.expect(bookmarkRepository.findAll()).andReturn(mockBookmarkResponse()).times(1);
		EasyMock.replay(bookmarkRepository);
		bookmarksService.setBookmarkRepository(bookmarkRepository);

		List<Bookmark> bookmarkList = bookmarksService.getAllBookmarks();
		Assert.assertEquals(1, bookmarkList.size());
		Assert.assertEquals(mockBookmark(), bookmarkList.get(0));
	}

	@Test
	public void testAddBookmark() throws Exception {
		final Bookmark bookmark = mockBookmark();
		BookmarkRepository bookmarkRepository = EasyMock.mock(BookmarkRepository.class);
		EasyMock.expect(bookmarkRepository.save(bookmark)).andReturn(bookmark).times(1);
		EasyMock.replay(bookmarkRepository);
		bookmarksService.setBookmarkRepository(bookmarkRepository);

		final Bookmark bookmarkResult = bookmarksService.addBookmark(bookmark);
		EasyMock.verify(bookmarkRepository);
		Assert.assertEquals(bookmark, bookmarkResult);
	}

	@Test
	public void testEditBookmark() throws Exception {
		final Bookmark bookmark = mockBookmark();
		BookmarkRepository bookmarkRepository = EasyMock.mock(BookmarkRepository.class);
		EasyMock.expect(bookmarkRepository.save(bookmark)).andReturn(bookmark).times(1);
		final String bookmarkId = "id";
		bookmarkRepository.delete(bookmarkId);
		EasyMock.expectLastCall().once();
		EasyMock.replay(bookmarkRepository);
		bookmarksService.setBookmarkRepository(bookmarkRepository);

		final Bookmark bookmarkResult = bookmarksService.editBookmark(bookmark, bookmarkId);
		EasyMock.verify(bookmarkRepository);
		Assert.assertEquals(bookmark, bookmarkResult);
	}

	@Test
	public void testDeleteBookmark(){
		final Bookmark bookmark = mockBookmark();
		BookmarkRepository bookmarkRepository = EasyMock.mock(BookmarkRepository.class);
		final String bookmarkId = "id";
		bookmarkRepository.delete(bookmarkId);
		EasyMock.expectLastCall().once();
		EasyMock.replay(bookmarkRepository);
		bookmarksService.setBookmarkRepository(bookmarkRepository);

		bookmarksService.deleteBookmark(bookmarkId);
		EasyMock.verify(bookmarkRepository);
	}

	private List<Bookmark> mockBookmarkResponse() {
		final List<Bookmark> bookmarkList = new ArrayList<>();
		bookmarkList.add(mockBookmark());
		return bookmarkList;
	}

	private Bookmark mockBookmark() {
		return new Bookmark("testLink", "testDescription", "testCategory");
	}
}
