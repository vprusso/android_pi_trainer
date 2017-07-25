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

    ScrollView scrollViewPi;
    Button buttonStartReading, buttonStopReading;

    TextToSpeech textToSpeech;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorize);
        setupVariables();
    }

    private void setupVariables() {

        textToSpeech = new TextToSpeech(this,this);

        buttonStartReading = (Button)findViewById(R.id.buttonStartReading);
        buttonStartReading.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                speakOut();
            }
        });

        buttonStopReading = (Button)findViewById(R.id.buttonStopReading);
        buttonStopReading.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                textToSpeech.stop();
            }
        });

    }

    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This language is not supported");
            } else {

                if (buttonStartReading.isPressed()) {
                    buttonStartReading.setEnabled(true);
                    speakOut();
                }
            }
        } else {
            Log.e("TTS", "Initialization failed!");
        }

    }


    private void speakOut() {
        textToSpeech.speak(pi, TextToSpeech.QUEUE_FLUSH, null);
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
}
