package com.example.deniksqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

public class EditujZaznam extends Activity {
    EditText autorET;
    EditText knihaET;
    EditText datumET;
    EditText hodnotenieET;

    DataModel dm = new DataModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edituj_zaznam);

        autorET = (EditText) findViewById(R.id.autorET);
        knihaET = (EditText) findViewById(R.id.knihaET);
        datumET = (EditText) findViewById(R.id.datumET);
        hodnotenieET = (EditText) findViewById(R.id.hodnocenieET);

        Intent ii = getIntent();
        String knihaId = ii.getStringExtra("knihaId");
        HashMap<String,String> knihaHM = dm.dajZaznam(knihaId);

        if(knihaHM.size()!=0) {
            autorET.setText(knihaHM.get("autor"));
            knihaET.setText(knihaHM.get("kniha"));
            datumET.setText(knihaHM.get("datum"));
            hodnotenieET.setText(knihaHM.get("hodnoceni"));
        }
    }
    public void editujZaznam (View view){
        HashMap<String,String> dotazHM = new HashMap<String,String>();

        autorET = (EditText) findViewById(R.id.autorET);
        knihaET = (EditText) findViewById(R.id.knihaET);
        datumET = (EditText) findViewById(R.id.datumET);
        hodnotenieET = (EditText) findViewById(R.id.hodnocenieET);

        Intent ii = getIntent();
        String sID = ii.getStringExtra("knihaId");

        dotazHM.put("id", sID);
        dotazHM.put("autor", autorET.getText().toString());
        dotazHM.put("kniha", knihaET.getText().toString());
        dotazHM.put("datum", datumET.getText().toString());
        dotazHM.put("hodnoceni", hodnotenieET.getText().toString());

        dm.aktualizujZaznam(dotazHM);
        this.aktivujMainActivity(view);
    }
    public void vymazZaznam(View view){
        Intent ii = getIntent();
        String sID = ii.getStringExtra("knihaId");
        dm.vymazZaznam(sID);
        this.aktivujMainActivity(view);
    }
    public void aktivujMainActivity(View view) {
        Intent ii = new Intent(getApplication(), MainActivity.class);
        startActivity(ii);
    }
}