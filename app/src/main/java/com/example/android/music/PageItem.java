package com.example.android.music;

/**
 * POJO class for items required to render a music page
 *<p>
 * title has the title of the page, description has description about the kind of music
 * youtubeUrl has the youtube url of a sample song, imageResource has drawable resource id.
 *</p>
 *
 */
public class PageItem {
    private final int title;
    private final int description;
    private final int youtubeUrl;
    private final int imageResource;

    public PageItem(int title, int description, int youtubeUrl, int imageResource) {
        this.title = title;
        this.description = description;
        this.youtubeUrl = youtubeUrl;
        this.imageResource = imageResource;
    }

    public int getTitle() {
        return title;
    }

    public int getDescription() {
        return description;
    }

    public int getYoutubeUrl() {
        return youtubeUrl;
    }

    public int getImageResource() {
        return imageResource;
    }
}
