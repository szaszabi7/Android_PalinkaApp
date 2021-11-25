package hu.petrik.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity {
    private EditText editFozo, editGyumolcs, editAlkohol;
    private Button btnRogzit, btnVissza;
    private DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);
        init();

        btnRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fozo = editFozo.getText().toString().trim();
                String gyumolcs = editGyumolcs.getText().toString().trim();
                String alkoholString = editAlkohol.getText().toString().trim();
                if (fozo.isEmpty() || gyumolcs.isEmpty() || alkoholString.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Minden mező kitöltése kötelező",
                            Toast.LENGTH_SHORT).show();
                } else{
                    try {
                        int alkohol = Integer.parseInt(alkoholString);
                        if (alkohol < 1 || alkohol > 80){
                            Toast.makeText(getApplicationContext(),
                                    "Az alkoholtartalomnak 1 és 80 közötti számnak kell lennie",
                                    Toast.LENGTH_SHORT).show();
                        } else{
                            if (adatbazis.rogzites(fozo, gyumolcs, alkohol)){
                                Toast.makeText(getApplicationContext(), "Sikeres rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            } else{
                                Toast.makeText(getApplicationContext(), "Sikeretelen rögzítés",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (NumberFormatException ex) {
                        Toast.makeText(getApplicationContext(), "Az alkoholtartalomnak számnak kell lennie",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainre = new Intent(AdatFelvetelActivity.this, MainActivity.class);
                startActivity(mainre);
                finish();
            }
        });
    }

    private void init() {
        editFozo = findViewById(R.id.edit_felvetel_Fozo);
        editGyumolcs = findViewById(R.id.edit_felvetel_Gyumolcs);
        editAlkohol = findViewById(R.id.edit_felvetel_Alkohol);
        btnRogzit = findViewById(R.id.btn_felvetel_rogzit);
        btnVissza = findViewById(R.id.btn_felvetel_vissza);
        adatbazis = new DBHelper(this);
    }
}