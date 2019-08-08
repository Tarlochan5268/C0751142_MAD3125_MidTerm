package com.tarlochan.c0751142_mad3125_midterm;

public class Links
{
    String mission_patch;
    String mission_patch_small;
    String article_link;
    String wikipedia;
    String video_link;

    public Links() {
    }

    public Links(String mission_patch, String mission_patch_small, String article_link, String wikipedia, String video_link) {
        this.mission_patch = mission_patch;
        this.mission_patch_small = mission_patch_small;
        this.article_link = article_link;
        this.wikipedia = wikipedia;
        this.video_link = video_link;
    }

    @Override
    public String toString() {
        return "Links{" +
                "mission_patch='" + mission_patch + '\'' +
                ", mission_patch_small='" + mission_patch_small + '\'' +
                ", article_link='" + article_link + '\'' +
                ", wikipedia='" + wikipedia + '\'' +
                ", video_link='" + video_link + '\'' +
                '}';
    }

    public String getMission_patch() {
        return mission_patch;
    }

    public void setMission_patch(String mission_patch) {
        this.mission_patch = mission_patch;
    }

    public String getMission_patch_small() {
        return mission_patch_small;
    }

    public void setMission_patch_small(String mission_patch_small) {
        this.mission_patch_small = mission_patch_small;
    }

    public String getArticle_link() {
        return article_link;
    }

    public void setArticle_link(String article_link) {
        this.article_link = article_link;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }
}
