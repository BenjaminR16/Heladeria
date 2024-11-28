package com.ben.heladeria;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {


    private LinearLayout pedidoLayout;
    private Button finalizarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

         pedidoLayout = findViewById(R.id.pedidoLayout);
         finalizarButton = findViewById(R.id.finalizarButton);

        int vainilla = Integer.parseInt(getIntent().getStringExtra("vainilla"));
        int chocolate = Integer.parseInt(getIntent().getStringExtra("chocolate"));
        int fresa = Integer.parseInt(getIntent().getStringExtra("fresa"));
        String recipiente = getIntent().getStringExtra("recipiente");


        agregarBolas(pedidoLayout, vainilla, "O", "#FFD700");
        agregarBolas(pedidoLayout, chocolate, "O", "#8B4513");
        agregarBolas(pedidoLayout, fresa, "O", "#FF69B4");


        TextView recipienteView = new TextView(this);
        recipienteView.setTextSize(32);
        if (recipiente.equals("Cucurucho")) {
            recipienteView.setText("\\/");
            recipienteView.setTextColor(Color.parseColor("#D2B48C"));
        } else if (recipiente.equals("Cucurucho Choco")) {
            recipienteView.setText("\\/");
            recipienteView.setTextColor(Color.parseColor("#8B4513"));
        } else {
            recipienteView.setText("U");
            recipienteView.setTextColor(Color.parseColor("#000000"));
        }
        pedidoLayout.addView(recipienteView);

        finalizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void agregarBolas(LinearLayout layout, int cantidad, String simbolo, String color) {
        for (int i = 0; i < cantidad; i++) {
            TextView bola = new TextView(this);
            bola.setText(simbolo);
            bola.setTextSize(32);
            bola.setTextColor(Color.parseColor(color));
            layout.addView(bola);
        }
    }
}