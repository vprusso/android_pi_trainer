package captainhampton.pi.trainer;


import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import java.util.Locale;

public class Memorize extends AppCompatActivity implements TextToSpeech.OnInitListener {

    String pi = "3.1415926535897932384626433832795028841971693993";

    ScrollView svPi;
    Button bStartReading, bStopReading;

    TextToSpeech tts;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorize);
        setupVariables();
    }

    private void setupVariables() {

        tts = new TextToSpeech(this,this);

        bStartReading = (Button)findViewById(R.id.bStartReading);
        bStartReading.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                speakOut();
            }
        });

        bStopReading = (Button)findViewById(R.id.bStopReading);
        bStopReading.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                tts.stop();
            }
        });

    }

    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This language is not supported");
            } else {

                if (bStartReading.isPressed()) {
                    bStartReading.setEnabled(true);
                    speakOut();
                }
            }
        } else {
            Log.e("TTS", "Initialization failed!");
        }

    }


    private void speakOut() {
        tts.speak(pi, TextToSpeech.QUEUE_FLUSH, null);
    }

    // To save scroll position of ScrollView when orientation changes.
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray("ARTICLE_SCROLL_POSITION", new int[] { svPi.getScrollX(), svPi.getScrollY() });
    }

    // To restore scroll position of ScrollView when orientation changes.
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final int[] position = savedInstanceState.getIntArray("ARTICLE_SCROLL_POSITION");
        if (position != null)
            svPi.post(new Runnable() {
                public void run() {
                    svPi.scrollTo(position[0], position[1]);
                }
            });
    }
}
