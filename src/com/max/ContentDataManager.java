package com.max;
import java.util.*;

public class ContentDataManager implements IContentDataManager {
    Map<String, Content> contentMap = new HashMap<>();
    Map<String, Preroll> prerollMap = new HashMap<>();
    ContentData data;

    public ContentDataManager(ContentData data){
        this.data = data;

        /*
        Indexing content and preroll by name. This allows to find it quickly.
        However if there's lots of videos associated with every content preroll we
        might also want to index each video based on attributes (country/language/aspect) to
        allow quick access of preroll videos with needed attributes.
         */

        for (Content c : data.content){
            contentMap.put(c.name, c);
        }

        for (Preroll p : data.preroll){
            prerollMap.put(p.name, p);

            /*
            TODO: index all preroll videos by their attributes to allow quick
            search when matching content videos with prerolls.
             */
        }
    }

    public synchronized void addContent(Content content){
        contentMap.put(content.name, content);
    }

    public synchronized void updateContent(Content content){
        if (content.name == null || !contentMap.containsKey(content.name)) return;

        contentMap.put(content.name, content);
    }

    public synchronized void deleteContent(String name){
        if (name == null || !contentMap.containsKey(name)) return;

        contentMap.remove(name);
    }

    private static String hashVideoAttributes(Video v){
        return v.attributes.language + "_" + v.attributes.aspect;
    }

    public synchronized List<Playlist> getPlaylists(String contentName, String countryCode){
        List<Playlist> playlists = new LinkedList<>();

        if (contentName == null || !contentMap.containsKey(contentName) || countryCode == null) {
            return playlists;
        }

        // Getting the content videos
        Content content = contentMap.get(contentName);
        Map<String, List<Video>> hashToVideo = new HashMap<>();
        for (Video v : content.videos){
            if (v.attributes.countries.contains(countryCode)){
                LinkedList list = new LinkedList();
                list.add(v);

                hashToVideo.put(hashVideoAttributes(v), list);
            }
        }

        // Getting the prerolls for each of content videos (with different attributes)
        for (Preroll p : content.preroll) {
            if (!prerollMap.containsKey(p.name)) continue;

            for (Video pv : prerollMap.get(p.name).videos){
                if (!pv.attributes.countries.contains(countryCode)) continue;

                String hash = hashVideoAttributes(pv);
                if (hashToVideo.containsKey(hash)){
                    hashToVideo.get(hash).add(0, pv);

                    playlists.add(new Playlist(hashToVideo.get(hash)));
                }
            }
        }

        return playlists;
    }
}
