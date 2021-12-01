package hu.petrik.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdatKeresesActivity extends AppCompatActivity {
    private EditText editFozo, editGyumolcs;
    private Button btnKereses, btnVissza;
    private TextView textPalinka;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_kereses);
        init();

        btnKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fozo = editFozo.getText().toString().trim();
                String gyumolcs = editGyumolcs.getText().toString().trim();
                Cursor adat = adatbazis.keres(fozo, gyumolcs);
                StringBuilder stringBuilder = new StringBuilder();

                if (fozo.isEmpty() || gyumolcs.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező", Toast.LENGTH_SHORT).show();
                    textPalinka.setText("");
                } else {
                    if (adat.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "Nincs ilyen pálinka!", Toast.LENGTH_SHORT).show();
                        textPalinka.setText("");
                    } else {
                        while (adat.moveToNext()) {
                            stringBuilder.append("Alkoholtartalom: ").append(adat.getInt(0)).append("%");
                            Toast.makeText(getApplicationContext(), "Sikeres keresés!", Toast.LENGTH_SHORT).show();
                        }
                        textPalinka.setText(stringBuilder.toString());
                    }
                }
            }
        });

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainre = new Intent(AdatKeresesActivity.this, MainActivity.class);
                startActivity(mainre);
                finish();
            }
        });
    }

    private void init() {
        editFozo = findViewById(R.id.edit_keres_fozo);
        editGyumolcs = findViewById(R.id.edit_keres_gyumolcs);
        btnKereses = findViewById(R.id.btn_keres_kereses);
        btnVissza = findViewById(R.id.btn_keres_vissza);
        textPalinka = findViewById(R.id.text_keres_palinka);
        adatbazis = new DBHelper(this);
    }
}