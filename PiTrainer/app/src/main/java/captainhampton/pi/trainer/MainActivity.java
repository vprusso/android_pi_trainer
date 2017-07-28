package captainhampton.pi.trainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button circleButtonTrain, circleButtonMarathon, circleButtonMemorize, circleButtonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayBannerAd();
        setupVariables();
    }

    private void setupVariables() {

        circleButtonTrain = (Button)findViewById(R.id.circleButtonTrain);
        circleButtonTrain.setOnClickListener(this);

        circleButtonMarathon = (Button)findViewById(R.id.circleButtonMarathon);
        circleButtonMarathon.setOnClickListener(this);

        circleButtonMemorize = (Button)findViewById(R.id.circleButtonMemorize);
        circleButtonMemorize.setOnClickListener(this);

        circleButtonAbout = (Button)findViewById(R.id.circleButtonAbout);
        circleButtonAbout.setOnClickListener(this);

    }

    public void onClick(View view) {

        if (circleButtonTrain.isPressed()) {
            Intent trainerIntent = new Intent(MainActivity.this, Train.class);
            startActivity(trainerIntent);
        }

        if (circleButtonMarathon.isPressed()) {
            Intent marathonIntent = new Intent(MainActivity.this, Marathon.class);
            startActivity(marathonIntent);
        }

        if (circleButtonMemorize.isPressed()) {
            Intent memorizeIntent = new Intent(MainActivity.this, Memorize.class);
            startActivity(memorizeIntent);
        }

        if(circleButtonAbout.isPressed()) {
            Intent aboutIntent = new Intent(MainActivity.this, About.class);
            startActivity(aboutIntent);
        }

    }

    public void displayBannerAd() {
        AdView adView = (AdView)findViewById(R.id.mainAdView);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);
    }
}
