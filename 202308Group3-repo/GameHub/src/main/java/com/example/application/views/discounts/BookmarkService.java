package com.example.application.views.discounts;

import com.example.application.data.Bookmark;
import com.example.application.data.User;
import com.example.application.data.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private final UserRepository userRepository;

    public BookmarkService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Bookmark> getCurrentUserBookmarks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String loggedInUsername = authentication.getName();
            User loggedInUser = userRepository.findByUsername(loggedInUsername);
            if (loggedInUser != null) {
                return loggedInUser.getBookmarks();
            }
        }
        return null; // Or handle accordingly if no bookmarks found or user not authenticated
    }

    public boolean removeBookmark(Bookmark bookmarkToRemove) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String loggedInUsername = authentication.getName();
            User loggedInUser = userRepository.findByUsername(loggedInUsername);
            if (loggedInUser != null) {
                List<Bookmark> userBookmarks = loggedInUser.getBookmarks();
                boolean removed = userBookmarks.removeIf(bookmark -> bookmark.getId().equals(bookmarkToRemove.getId()));
                if (removed) {
                    userRepository.save(loggedInUser);
                    return true;
                }
            }
        }
        return false;
    }

}
