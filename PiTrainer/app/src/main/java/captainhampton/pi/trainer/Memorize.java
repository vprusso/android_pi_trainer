package captainhampton.pi.trainer;


import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class Memorize extends AppCompatActivity {

    String pi;
    ScrollView scrollViewPi;
    TextToSpeech textToSpeech;

    AdView adView;
    AdRequest adRequest;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorize);
        setupVariables();
        displayBannerAd();
    }

    private void setupVariables() {
        pi = getResources().getString(R.string.pi_digits);
    }

    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    // To save scroll position of ScrollView when orientation changes.
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("ARTICLE_SCROLL_POSITION", new int[] { scrollViewPi.getScrollX(), scrollViewPi.getScrollY() });
    }

    // To restore scroll position of ScrollView when orientation changes.
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final int[] position = savedInstanceState.getIntArray("ARTICLE_SCROLL_POSITION");
        if (position != null)
            scrollViewPi.post(new Runnable() {
                public void run() {
                    scrollViewPi.scrollTo(position[0], position[1]);
                }
            });
    }

    public void displayBannerAd() {
        adView = (AdView)findViewById(R.id.adMemorize);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);
    }
}
