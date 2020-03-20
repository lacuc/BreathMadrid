package com.sermami.bmad.ui.calculo_co2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sermami.bmad.R;

public class CalculoFragment extends Fragment {

    EditText etkm;
    EditText etconsum;
    RadioButton rbGasolina;
    RadioButton rbDiesel;
    TextView tvfinal;
    Button btnCalcular;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calculo, container, false);

        etkm = root.findViewById(R.id.idEtKm);
        etconsum = root.findViewById(R.id.idEtConsumo);
        rbGasolina = root.findViewById(R.id.radioButton);
        rbDiesel = root.findViewById(R.id.radioButton2);
        tvfinal = root.findViewById(R.id.tvshow);
        btnCalcular = root.findViewById(R.id.btnCalculo);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double litros;
                double emisionesGaso;
                double emisionesDiesel;
                double ckms = Double.parseDouble(etkm.getText().toString());
                double ccons = Double.parseDouble(etconsum.getText().toString());

                if (etkm.toString().isEmpty() || etconsum.toString().isEmpty()) {
                    Toast.makeText(getContext(), "Debe introducir un dato", Toast.LENGTH_LONG).show();
                } else {
                    if (rbGasolina.isChecked()) {
                        litros = (ckms * ccons) / 100;
                        emisionesGaso = litros * 2.196;

                        String text = getResources().getString(R.string.valorConsumo);
                        String text3 = (String.format(text, emisionesGaso));
                        tvfinal.setText(text3);
                        tvfinal.setAlpha(1);

                    } else if (rbDiesel.isChecked()) {
                        litros = (ckms * ccons) / 100;
                        emisionesDiesel = litros * 2.471;

                        String text = getResources().getString(R.string.valorConsumo);
                        String text3 = String.format(text, emisionesDiesel);
                        tvfinal.setText(text3);
                        tvfinal.setAlpha(1);
                    }
                }


            }
        });


        return root;
    }
}