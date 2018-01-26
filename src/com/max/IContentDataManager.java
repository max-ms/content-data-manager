package com.max;

import java.util.List;

public interface  IContentDataManager {

    List<Playlist> getPlaylists(String contentName, String countryCode);

    /*
    Methods addContent/updateContent/deleteContent illustrate the way to allow clients
    modify the data.
    Similar methods can be exposed to modify prerolls (e.g. addPreroll/updatePreroll/deletePreroll).
     */

    void addContent(Content content);

    void updateContent(Content content);

    void deleteContent(String name);

}
