package com.max;

import java.util.List;

public class VideoAttributes {
    public List<String> countries;
    public String language;
    public String aspect;

    public VideoAttributes(List<String> countries, String language, String aspect){
        this.countries = countries;
        this.language = language;
        this.aspect = aspect;
    }
}
