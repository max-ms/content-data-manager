package com.max;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ContentDataManagerTest {
    private ContentData testContentData;

    public ContentDataManagerTest() throws IOException {
            Path currentRelativePath = Paths.get("");

            testContentData = ContentDataFactory.getContentData(
                    new String(
                            Files.readAllBytes(
                                    Paths.get(
                                            currentRelativePath.toAbsolutePath().toString() + "/input.json"))));
    }

    @Test
    public void ContentDataManager_InitializeNoExceptions() throws Exception {
        new ContentDataManager(testContentData);
    }

    @Test
    public void getPlaylists_NoPlaylistsFound() throws Exception {
        ContentDataManager obj = new ContentDataManager(testContentData);
        List<Playlist> result = obj.getPlaylists("MI3", "US");

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void getPlaylists_OnePlaylistFound() throws Exception {
        ContentDataManager obj = new ContentDataManager(testContentData);
        List<Playlist> result = obj.getPlaylists("MI3", "CA");

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2, result.get(0).videos.size());
        Assert.assertEquals("V5", result.get(0).videos.get(0).name);
        Assert.assertEquals("V1", result.get(0).videos.get(1).name);
    }

    @Test
    public void getPlaylists_MultiplePlaylistsFound() throws Exception {
        ContentDataManager obj = new ContentDataManager(testContentData);
        List<Playlist> result = obj.getPlaylists("MI3", "UK");

        Assert.assertEquals(2, result.size());
    }


}