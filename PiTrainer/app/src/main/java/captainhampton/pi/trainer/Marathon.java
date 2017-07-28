package captainhampton.pi.trainer;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class Marathon extends AppCompatActivity implements OnClickListener {

    Button button0, button1, button2, button3, button4, button5,
            button6, button7, button8, button9, buttonDecimal;
    EditText editTextPi;
    TextView textViewHighScore, textViewHighestScore, textViewCurrentDigit;
    Vibrator vibrator;

    AdView adView;
    AdRequest adRequest;

    //Facebook fb;
    //AsyncFacebookRunner asyncRunner;

    SharedPreferences sp;

    final Context context = this;

    String userInput = "";
    String pi;
    int highScore = 0, highestScore = 0, count = 0;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marathon);
        setupVariables();
        displayBannerAd();
    }

    private void setupVariables() {

        //String APP_ID = getString(R.string.APP_ID);
        //Utility.fb = new Facebook(APP_ID);
        //Utility.asyncRunner = new AsyncFacebookRunner(fb);

        sp = getPreferences(MODE_PRIVATE);
        String access_token = sp.getString("access_token", null);
        long expires = sp.getLong("access_expires", 0);

//        if (access_token != null) {
//            Utility.fb.setAccessToken(access_token);
//        }
//
//        if (expires != 0) {
//            Utility.fb.setAccessExpires(expires);
//        }

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
        //textViewHighestScore = (TextView)findViewById(R.id.textViewHighestScore);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        pi = getResources().getString(R.string.pi_digits);

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
        pi = pi.replaceAll("\\s+","");
        if (piDigit == pi.charAt(count)) {
            count++;
            textViewCurrentDigit.setText(String.format(Locale.US, "%d", count));
            return true;
        }
        return false;
    }

    private void incorrectInput(Character incorrectDigit) {
        pi = pi.replaceAll("\\s+","");
        // Vibrate phone to indicate loss.
        vibrator.vibrate(500);


        // Display dialog allowing user to try again.
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        alertDialogBuilder.setTitle("Try Again?");
        alertDialogBuilder.setMessage("Score: "+count+"\n" + "You entered "+ incorrectDigit +", instead of " + pi.charAt(count)  + " \n").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                updateHighScore();
                editTextPi.setText("");
                userInput = "";
                count = 0;
            }
        }).setNeutralButton("Post to Facebook", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                editTextPi.setText("");
                userInput = "";
                int fbCount = count;
                count = 0;

//                // Post score to High Score to Facebook
//                if (Utility.fb.isSessionValid()) {
//                    //button close our session - log out of facebook
//                    try {
//                        Utility.fb.logout(getApplicationContext());
//                    } catch (MalformedURLException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                } else {
//                    // login to facebook
//
//
//                    Bundle params = new Bundle();
//                    params.putString("name", "Download Pi Trainer");
//                    params.putString("caption", "Pi Trainer - by captainhampton");
//                    params.putString("description", "Was able to recite "+Integer.toString(fbCount)+" digits of Pi. Wow! You must get all the ladies!");
//                    params.putString("link", "https://play.google.com/store/apps/details?id=captainhampton.pi.trainer&hl=en");
//                    params.putString("picture", "https://lh5.ggpht.com/hA5Q_91wpB9ibgA9-MdxlWwFaU8PjS5Jwm_yVZ6vShqLB4cSa5GM0jWnOjM_QaBgU7M=w124");
//
//
//                    Utility.fb.dialog(Marathon.this, "feed", params, new DialogListener() {
//
//                        public void onComplete(Bundle values) {
//                            Editor editor = sp.edit();
//                            editor.putString("access_token", Utility.fb.getAccessToken());
//                            editor.putLong("access_expires", Utility.fb.getAccessExpires());
//                            editor.commit();
//                        }
//
//                        public void onFacebookError(FacebookError e) {
//                            //Toast.makeText(Marathon.this, "fbError", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        public void onError(DialogError e) {
//                            //Toast.makeText(Marathon.this, "onError", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        public void onCancel() {
//                            //Toast.makeText(Marathon.this, "onCancel", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    });
//                }
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Marathon.this.finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // TODO Auto-generated method stub
//        super.onActivityResult(requestCode, resultCode, data);
//        Utility.fb.authorizeCallback(requestCode, resultCode, data);
//    }

    private void updateHighScore() {

        if (count > highScore) {
            highScore = count;
            textViewHighScore.setText(String.format(Locale.US, "%d", highScore));
        }
    }

    public void displayBannerAd() {
        adView = (AdView)findViewById(R.id.adMarathon);
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);
    }
}
