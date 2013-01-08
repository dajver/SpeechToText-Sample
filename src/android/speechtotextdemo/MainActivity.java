package android.speechtotextdemo;

import java.util.ArrayList;
 
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
 
    protected static final int RESULT_SPEECH = 1;
 
    private ImageButton btnSpeak; //создаем объект кнопки
    private TextView txtText; //создаем объект нашего текствью
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        txtText = (TextView) findViewById(R.id.txtText); //определяем их
 
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak); // -//-
 
        btnSpeak.setOnClickListener(new View.OnClickListener() { //создаем листенера для определения  
                                                                 //нажатия кнопки
 
            @Override
            public void onClick(View v) {
 
                Intent intent = new Intent(
                        RecognizerIntent.ACTION_RECOGNIZE_SPEECH);    //и вот тут собственно запускаем наш 
                                                                      //интент с параметрами "голос в текст"
 
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");   //задаем язык, если  
                                                                                   //русский значит ru-RU
 
                try {
                    startActivityForResult(intent, RESULT_SPEECH); //запускаем наш интент
                    txtText.setText(""); //обнуляем то что находится в текствью.
                } catch (ActivityNotFoundException a) {
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Opps! Your device doesn't support Speech to Text",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
 
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
 
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 
        switch (requestCode) {
        case RESULT_SPEECH: {
            if (resultCode == RESULT_OK && null != data) {
 
                ArrayList<String> text = data
                        .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS); //получаем что нам вернул интент
 
                txtText.setText(text.get(0)); и записываем в текствью
            }
            break;
        }
 
        }
    }
}