package captainhampton.pi.trainer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class Train extends AppCompatActivity implements OnClickListener {

    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, buttonDecimal;
    EditText editTextPi;
    TextView textViewHighScore, textViewHighestScore, textViewCurrentDigit;
    Vibrator vibrator;

    AdView adView;
    AdRequest adRequest;

    String userInput = "";
    String pi = "3.141592653589793238462643383279502884197169399375105820974944592307816406286208" +
            "998628034825342117067982148086513282306647093844609550582231725359408128481117450284" +
            "102701938521105559644622948954930381964428810975665933446128475648233786783165271201" +
            "909145648566923460348610454326648213393607260249141273724587006";

    int highScore = 0, highestScore = 0, count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.train);
        setupVariables();
        displayBannerAd();
    }

    private void setupVariables() {

        button0 = (Button)findViewById(R.id.button0);
        button0.setOnClickListener(this);

        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);

        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);

        button4 = (Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);

        button5 = (Button)findViewById(R.id.button5);
        button5.setOnClickListener(this);

        button6 = (Button)findViewById(R.id.button6);
        button6.setOnClickListener(this);

        button7 = (Button)findViewById(R.id.button7);
        button7.setOnClickListener(this);

        button8 = (Button)findViewById(R.id.button8);
        button8.setOnClickListener(this);

        button9 = (Button)findViewById(R.id.button9);
        button9.setOnClickListener(this);

        buttonDecimal = (Button)findViewById(R.id.buttonDecimal);
        buttonDecimal.setOnClickListener(this);

        editTextPi = (EditText)findViewById(R.id.editTextPi);
        textViewHighScore = (TextView)findViewById(R.id.textViewHighScore);
        textViewCurrentDigit = (TextView)findViewById(R.id.textViewCurrentDigit);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

    }

    @Override
    public void onClick(View v) {

        if (button0.isPressed()) {
            if (isCorrectInput('0')) {
                userInput += "0";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('0');
            }
        }

        if (button1.isPressed()) {
            if (isCorrectInput('1')) {
                userInput += "1";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('1');
            }
        }

        if (button2.isPressed()) {
            if (isCorrectInput('2')) {
                userInput += "2";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('2');
            }
        }

        if (button3.isPressed()) {
            if (isCorrectInput('3')) {
                userInput += "3";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('3');
            }
        }

        if (button4.isPressed()) {
            if (isCorrectInput('4')) {
                userInput += "4";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('4');
            }
        }

        if (button5.isPressed()) {
            if (isCorrectInput('5')) {
                userInput += "5";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('5');
            }
        }

        if (button6.isPressed()) {
            if (isCorrectInput('6')) {
                userInput += "6";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('6');
            }
        }

        if (button7.isPressed()) {
            if (isCorrectInput('7')) {
                userInput += "7";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('7');
            }
        }

        if (button8.isPressed()) {
            if (isCorrectInput('8')) {
                userInput += "8";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('8');
            }
        }

        if (button9.isPressed()) {
            if (isCorrectInput('9')) {
                userInput += "9";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('9');
            }
        }

        if (buttonDecimal.isPressed()) {
            if (isCorrectInput('.')) {
                userInput += ".";
                editTextPi.setText(userInput);
            } else {
                incorrectInput('.');
            }
        }

        editTextPi.setSelection(editTextPi.getText().length());
    }

    private boolean isCorrectInput(Character piDigit) {

        if (piDigit == pi.charAt(count)) {
            count++;
            textViewCurrentDigit.setText(String.format(Locale.US, "%d", count));
            return true;
        }
        return false;
    }

    private void incorrectInput(Character incorrectDigit) {

        // Vibrate phone to indicate loss.
        vibrator.vibrate(500);
    }


    private void updateHighScore() {

        if (count > highScore) {
            highScore = count;
            textViewHighScore.setText(String.format(Locale.US, "%d", highScore));
        }
    }

    public void displayBannerAd() {
        adView = (AdView)findViewById(R.id.adTrain);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);
    }
}
