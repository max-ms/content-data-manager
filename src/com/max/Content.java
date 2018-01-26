package com.max;

import java.util.List;

public class Content {
    public String name;
    public List<Preroll> preroll;
    public List<Video> videos;

    public Content(String name, List<Preroll> preroll, List<Video> videos){
        this.name = name;
        this.preroll = preroll;
        this.videos = videos;
    }
}
