package com.max;

import java.io.IOException;
import java.nio.file.Paths;

public class RunMe {
    public static void main(String... args) throws IOException {
        IContentDataManager contentMgr = ContentDataManagerFactory.getContentDataManager(
                Paths.get("").toAbsolutePath().toString() + "/input.json");

        for (Playlist p : contentMgr.getPlaylists("MI3", "UK")){
            for (Video v : p.videos) {
                System.out.printf("%s, ", v.name);
            }
            System.out.println();
        }

    }
}
