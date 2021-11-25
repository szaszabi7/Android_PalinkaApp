package hu.petrik.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnFelvetel, btnKeres, btnListaz;
    private TextView textLista;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        textLista.setMovementMethod(new ScrollingMovementMethod());
        btnFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent felvetelre = new Intent(MainActivity.this, AdatFelvetelActivity.class);
                startActivity(felvetelre);
                finish();
            }
        });

        btnKeres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keresesre = new Intent(MainActivity.this, AdatKeresesActivity.class);
                startActivity(keresesre);
                finish();
            }
        });

        btnListaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor adatok = adatbazis.listaz();
                if (adatok.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "Az adatbazisban nincs adat", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (adatok.moveToNext()) {
                        stringBuilder.append("ID: ").append(adatok.getInt(0));
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("Főző: ").append(adatok.getString(1));
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("Gyümölcs: ").append(adatok.getString(2));
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append("Alkohol: ").append(adatok.getInt(3)).append(" %");
                        stringBuilder.append(System.lineSeparator());
                        stringBuilder.append(System.lineSeparator());
                    }
                    textLista.setText(stringBuilder.toString());
                }
            }
        });
    }

    private void init() {
        btnFelvetel = findViewById(R.id.btn_palika_felvetel);
        btnKeres = findViewById(R.id.btn_palika_keres);
        btnListaz = findViewById(R.id.btn_palika_listaz);
        textLista = findViewById(R.id.text_lista);
        adatbazis = new DBHelper(this);
    }
}