package com.max;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ContentDataManagerFactory {
    /**
     * Gets an implementation of IContentDataManager interface.
     * @param filename
     * @return
     * @throws IOException
     */
    public static IContentDataManager getContentDataManager(String filename) throws IOException {

        /**
         * Default implementation of ContentDataManager assumes everything fits in memory and
         * therefore we can index different content/preroll/videos by name/attributes/etc for
         * faster lookup.
         * This also means we can easily update/delete our data entities directly in memory without
         * needing to re-read the input file.
         *
         * TODO: For large files that don't fit in memory we'll need to use a different approach.
         * First problem is how to read the file fast - we'll need to add file indexing to map
         * different pieces (e.g. contents/prerolls or similar blocks) to file locations.
         *
         * Second problem is if we want to support file modifications (new entries added - e.g.
         * new content/preroll - or existing entries are modified or deleted). To achieve that
         * we could re-read file periodically (checking its last modified date) or relying
         * on a file watcher that triggers re-read.
         */

        return new ContentDataManager(
                ContentDataFactory.getContentData(
                    new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8)));
    }
}
