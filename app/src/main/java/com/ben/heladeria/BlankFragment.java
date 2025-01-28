package com.ben.heladeria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class BlankFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        LinearLayout heladosContainer = view.findViewById(R.id.heladosContainer);

        Bundle args = getArguments();
        if (args != null) {
            ArrayList<String> helados = args.getStringArrayList("helados");

            if (helados != null) {
                for (String helado : helados) {
                    View heladoView = inflater.inflate(R.layout.item_helado, heladosContainer, false);

                    configurarHelado(heladoView, helado);

                    heladosContainer.addView(heladoView);
                }
            }
        }

        view.findViewById(R.id.finalizarButton).setOnClickListener(v -> dismiss());

        return view;
    }

    private void configurarHelado(View heladoView, String helado) {
        String[] partes = helado.split(", ");
        int vainilla = Integer.parseInt(partes[0].split(": ")[1]);
        int chocolate = Integer.parseInt(partes[1].split(": ")[1]);
        int fresa = Integer.parseInt(partes[2].split(": ")[1]);
        String recipiente = partes[3].split(": ")[1];

        LinearLayout bolasContainer = heladoView.findViewById(R.id.bolasContainer);
        TextView recipienteText = heladoView.findViewById(R.id.recipienteText);

        // Añadir bolas de helado
        añadirBolas(bolasContainer, vainilla, R.color.vainilla);
        añadirBolas(bolasContainer, chocolate, R.color.chocolate);
        añadirBolas(bolasContainer, fresa, R.color.fresa);

        recipienteText.setText(recipiente);
    }

    private void añadirBolas(LinearLayout container, int cantidad, int color) {
        for (int i = 0; i < cantidad; i++) {
            View bola = new View(getContext());
            bola.setLayoutParams(new LinearLayout.LayoutParams(50, 50)); // Tamaño de las bolas
            bola.setBackgroundResource(color); // Color de la bola
            bola.setPadding(8, 8, 8, 8); // Margen entre bolas
            container.addView(bola);
        }
    }
}
