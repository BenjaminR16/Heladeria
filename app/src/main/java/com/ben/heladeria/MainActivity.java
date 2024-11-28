package com.ben.heladeria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText vainillaInput, chocolateInput, fresaInput;
    private Spinner recipienteSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        vainillaInput = findViewById(R.id.vainillaInput);
        chocolateInput = findViewById(R.id.chocolateInput);
        fresaInput = findViewById(R.id.fresaInput);
        recipienteSpinner = findViewById(R.id.recipienteSpinner);
        Button generarButton = findViewById(R.id.generarButton);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recipientes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recipienteSpinner.setAdapter(adapter);

        generarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("vainilla", vainillaInput.getText().toString());
                intent.putExtra("chocolate", chocolateInput.getText().toString());
                intent.putExtra("fresa", fresaInput.getText().toString());
                intent.putExtra("recipiente", recipienteSpinner.getSelectedItem().toString());
                startActivity(intent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}