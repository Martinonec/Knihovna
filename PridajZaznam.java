package com.example.deniksqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class PridajZaznam extends Activity {
    EditText autorET;
    EditText knihaET;
    EditText datumET;
    EditText hodnotenieET;

    DataModel dm = new DataModel(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pridej_zaznam);

        autorET = (EditText) findViewById(R.id.autorET);
        knihaET = (EditText) findViewById(R.id.knihaET);
        datumET = (EditText) findViewById(R.id.datumET);
        hodnotenieET = (EditText) findViewById(R.id.hodnocenieET);
    }
    public void pridejZaznam (View view){
        HashMap <String,String> dotazHM = new HashMap<String,String>();

        dotazHM.put("autor", autorET.getText().toString());
        dotazHM.put("kniha", knihaET.getText().toString());
        dotazHM.put("datum", datumET.getText().toString());
        dotazHM.put("hodnocenie", hodnotenieET.getText().toString());

        dm.vlozZaznam(dotazHM);
        // vola MainActivity
        this.aktivujMainActivity(view);
    }
    public void aktivujMainActivity(View view){
        Intent ii = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(ii);
    }
}