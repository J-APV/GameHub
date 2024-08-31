package com.example.application.views.discounts;

import com.example.application.data.Bookmark;
import com.example.application.views.discounts.BookmarkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookmarkController {
    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @GetMapping("/bookmarks")
    public String displayBookmarks(Model model) {
        List<Bookmark> userBookmarks = bookmarkService.getCurrentUserBookmarks();
        model.addAttribute("bookmarks", userBookmarks);
        return "bookmarks"; // Assuming "bookmarks" is the name of the HTML template to display bookmarks
    }
}
