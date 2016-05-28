package com.example.android.music;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Activity responsible for rendering the music pages
 *
 * <p>
 *   Given a page number renders the page. Provides ways to render next and previous pages.
 *   Keeps in-memory store to save last view page no.
 * </p>
 */
public class MusicActivity extends AppCompatActivity {

    public static final String STORENAME = "musicStore";
    public static final String KEYNAME = "pageNo";

    TextView titleTv;
    TextView descTv;
    ImageView albumIv;
    Button videoBtn;
    Button nextBtn;
    Button prevBtn;
    int pageNo;
    PageItem[] pageItems;

    SharedPreferences pref;

    /**
     * Initializes the variables and renders a page.
     * Provides on click listeners for next page, previous page and launching music video
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.music_page);

        int savedPageNo = getIntent().getIntExtra(KEYNAME, 0);

        pref = getSharedPreferences(STORENAME, MODE_PRIVATE);
        initsubview(savedPageNo);

        initItemList();

        bindView(pageItems[savedPageNo]);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNextPage();
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayPrevPage();
            }
        });

        videoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchVideo();
            }
        });
    }

    /**
     * Used when user returns to the app and wants to continue from the page he left on
     * @param context
     * @param pagenumber
     */
    public static void startYourself(Context context, int pagenumber) {
        Intent intent = new Intent(context, MusicActivity.class);
        intent.putExtra(KEYNAME, pagenumber);
        context.startActivity(intent);
    }

    /**
     * Initializes all the different Pageitems that can be rendered
     */
    private void initItemList() {
        PageItem deathMetal = new PageItem(R.string.death_metal, R.string.death_metal_string, R.string.death_metal_video, R.drawable.belakor);
        PageItem thrashMetal = new PageItem(R.string.thrash_metal, R.string.thrash_metal_string, R.string.thrash_metal_video, R.drawable.megadeth);
        PageItem heavyMetal = new PageItem(R.string.heavy_metal, R.string.heavy_metal_string, R.string.heavy_metal_video, R.drawable.ironmaiden);
        PageItem blackMetal = new PageItem(R.string.black_metal, R.string.black_metal_string, R.string.black_metal_video, R.drawable.immortal);
        PageItem doomMetal = new PageItem(R.string.doom_metal, R.string.doom_metal_string, R.string.doom_metal_video, R.drawable.sabbath);
        PageItem powerMetal = new PageItem(R.string.power_metal, R.string.power_metal_string, R.string.power_metal_video, R.drawable.powerwolf);

        pageItems = new PageItem[]{heavyMetal, doomMetal, thrashMetal, deathMetal, blackMetal, powerMetal};
    }

    /**
     * Renders the current page
     * @param pageItem
     */
    private void bindView(final PageItem pageItem) {
        titleTv.setText(pageItem.getTitle());
        descTv.setText(pageItem.getDescription());
        albumIv.setImageResource(pageItem.getImageResource());
    }

    /**
     * Displays next page
     */
    void displayNextPage() {
        if (pageNo < pageItems.length-1 ) {
            bindView(pageItems[++pageNo]);
            pref.edit().putInt(KEYNAME, pageNo).apply();
        }
    }

    /**
     * Displays the previous page
     */
    void displayPrevPage() {
        if (pageNo > 0) {
            bindView(pageItems[--pageNo]);
            pref.edit().putInt(KEYNAME, pageNo).apply();
        }
    }

    /**
     * Intent to launch video of music
     */
    void launchVideo() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(pageItems[pageNo].getYoutubeUrl())));
        startActivity(intent);
    }

    /**
     * Initializes all the views on the page
     * @param savedPageNo
     */
    void initsubview(int savedPageNo) {
        View root = findViewById(R.id.container);
        titleTv = (TextView) root.findViewById(R.id.title);
        descTv = (TextView) root.findViewById(R.id.description);
        albumIv = (ImageView) root.findViewById(R.id.image);
        videoBtn = (Button) root.findViewById(R.id.btn_video);
        nextBtn = (Button) root.findViewById(R.id.btn_next);
        prevBtn = (Button) root.findViewById(R.id.btn_previous);
        pageNo = savedPageNo;
    }

}
