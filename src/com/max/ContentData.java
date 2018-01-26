package com.max;

import java.util.List;

public class ContentData {
    List<Content> content;
    List<Preroll> preroll;

    public ContentData(List<Content> content, List<Preroll> preroll){
        this.content = content;
        this.preroll = preroll;
    }
}
