package com.ben.heladeria;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText vainillaInput, chocolateInput, fresaInput;
    private Spinner recipienteSpinner;
    private Button generarButton, finalizarPedidoButton;
    private ArrayList<String> helados; // Lista para almacenar los helados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        vainillaInput = findViewById(R.id.vainillaInput);
        chocolateInput = findViewById(R.id.chocolateInput);
        fresaInput = findViewById(R.id.fresaInput);
        recipienteSpinner = findViewById(R.id.recipienteSpinner);
        generarButton = findViewById(R.id.generarButton);
        finalizarPedidoButton = findViewById(R.id.finalizarPedidoButton);

        helados = new ArrayList<>();

        // Configuración del Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.recipientes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recipienteSpinner.setAdapter(adapter);

        // Listener para generar y añadir helados a la lista
        generarButton.setOnClickListener(view -> {
            // Crear un nuevo helado con los datos actuales
            String helado = "Vainilla: " + vainillaInput.getText().toString() +
                    ", Chocolate: " + chocolateInput.getText().toString() +
                    ", Fresa: " + fresaInput.getText().toString() +
                    ", Recipiente: " + recipienteSpinner.getSelectedItem().toString();

            helados.add(helado);

            // Limpiar los campos para crear un nuevo helado
            vainillaInput.setText("");
            chocolateInput.setText("");
            fresaInput.setText("");
        });

        // Listener para mostrar todos los helados en el fragmento
        finalizarPedidoButton.setOnClickListener(view -> {
            BlankFragment fragment = new BlankFragment();

            Bundle args = new Bundle();
            args.putStringArrayList("helados", helados);
            fragment.setArguments(args);

            // Mostrar el fragmento como un Bottom Sheet
            fragment.show(getSupportFragmentManager(), "PedidoFragment");
        });
    }
}
