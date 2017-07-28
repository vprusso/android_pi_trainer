package captainhampton.pi.trainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        displayBannerAd();

        TextView textViewAboutParagraph = (TextView) findViewById(R.id.textViewAboutParagraph);
        textViewAboutParagraph.setText(getResources().getString(R.string.about_paragraph));
    }

    public void displayBannerAd() {
        AdView adView = (AdView)findViewById(R.id.adAbout);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);
    }

}
