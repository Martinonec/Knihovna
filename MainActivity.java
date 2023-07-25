package com.example.deniksqllite;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    Intent intent;
    TextView knihaId;
    DataModel dm = new DataModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> knihyHM = dm.dajZaznamy();

        if (knihyHM.size() != 0) {
            ListView lw = getListView();
            lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                //kliknutí na položku seznamu
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    knihaId = (TextView) view.findViewById(R.id.knihaId);
                    String sKnihaId = knihaId.getText().toString();
                    Intent theIntent = new Intent(getApplication(), EditujZaznam.class);
                    theIntent.putExtra("knihaId", sKnihaId);
                    startActivity(theIntent);
                }
            });
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, knihyHM,
                    R.layout.polozka,
                    new String[] {"_id", "autor", "kniha"}, new int[] {R.id.knihaId,
                    R.id.knihaAutor, R.id.knihaNazev});
                    setListAdapter(adapter);
        }
    }
        public void pridejKnihu(View view) {
            Intent theIntent = new Intent(getApplication(), PridajZaznam.class);
            startActivity(theIntent);
        }
    }
