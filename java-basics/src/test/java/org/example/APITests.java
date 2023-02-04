package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class APITests {

    JSONPlaceholderAPI api = JSONPlaceholderAPI.getInstance();

    @Test
    public void testGetPhotos() throws IOException {
        var photos = api.getPhotos().execute().body();
        assertNotNull(photos);
        assertEquals(5000, photos.size(), "There should be 5000 photos");

        var photo = photos.get(0);
        assertEquals(1, photo.getAlbumId());
        assertEquals(1, photo.getId());
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", photo.getTitle());

    }

    @Test
    public void testGetPosts() throws IOException {
        var posts = api.getPosts().execute().body();
        assertNotNull(posts);
        assertEquals(100, posts.size(), "There should be 100 posts");
    }
}
