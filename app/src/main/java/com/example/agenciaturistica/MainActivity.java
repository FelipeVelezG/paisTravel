package com.example.agenciaturistica;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] tDestino = {"Cartagena", "Santa Marta", "San Andrés","Medellín"};
    String destinoSeleccionado ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instanciar y referenciar los ID´s del archivo xml
        EditText nombre = findViewById(R.id.etnombre);
        Spinner destino = findViewById(R.id.spdestino);
        EditText fechaSalida =findViewById(R.id.etfechaSalida);
        EditText numeroPersonas = findViewById(R.id.etnumeroPersonas);
        RadioButton rb2 = findViewById(R.id.rb2);
        RadioButton rb4 = findViewById(R.id.rb4);
        RadioButton rb6 = findViewById(R.id.rb6);
        Switch tourDias = findViewById(R.id.swtourDias);
        Switch discoteca = findViewById(R.id.swdiscoteca);
        Button calcular = findViewById(R.id.btncalc);
        Button clear = findViewById(R.id.btnclear);
        TextView  totalPagar = findViewById(R.id.tvtotalPagar);



        ArrayAdapter adpDestino = new ArrayAdapter(this,  android.R.layout.simple_list_item_1,tDestino);

        destino.setAdapter(adpDestino);

        destino.setOnItemSelectedListener(this);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nombre.getText().toString().isEmpty()) {
                    if (!fechaSalida.getText().toString().isEmpty()) {
                        if (!numeroPersonas.getText().toString().isEmpty()) {

                            double xnumeroPersonas = parseDouble(numeroPersonas.getText().toString());
                            double tarifaDestino = 0;
                            switch (destinoSeleccionado) {
                                case "Cartagena":
                                    tarifaDestino = 200000;
                                    break;
                                case "Santa Marta":
                                    tarifaDestino = 180000;
                                    break;
                                case "San Andrés":
                                    tarifaDestino = 170000;
                                    break;
                                case "Medellín":
                                    tarifaDestino = 190000;
                                    break;
                            }
                            double descuento = 0;
                            if (parseDouble(numeroPersonas.getText().toString()) > 5) {
                                descuento = 0.1;
                            }

                            if (parseDouble(numeroPersonas.getText().toString()) >= 1 && parseDouble(numeroPersonas.getText().toString()) <= 10) {


                                double nDias = 0;
                                if (rb2.isChecked()) {
                                    nDias = 2;
                                }
                                if (rb4.isChecked()) {
                                    nDias = 4;
                                }
                                if (rb6.isChecked()) {
                                    nDias = 6;
                                }
                                double pagarTour = 0;
                                double pagarDisco = 0;

                                if (tourDias.isChecked()){
                                    pagarTour = 60000 * xnumeroPersonas;
                                }
                                if(discoteca.isChecked()){
                                    pagarDisco = 80000* xnumeroPersonas;
                                }
                                double totalDeuda =((tarifaDestino * xnumeroPersonas) * nDias ) -(((tarifaDestino * xnumeroPersonas) * nDias ) * descuento)+(pagarTour)+(pagarDisco);
                                DecimalFormat valueFormat = new DecimalFormat("###,###,###,###.#");
                                totalPagar.setText(valueFormat.format(totalDeuda));
                            }else{
                                Toast.makeText(getApplicationContext(), "Solo es posible 1 a 10 personas ", Toast.LENGTH_SHORT).show();
                            }



                        } else {
                            Toast.makeText(getApplicationContext(), "Numero de personas obligatorio", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Fecha obligatoria", Toast.LENGTH_SHORT).show();
                    }
                            /*if(!destino.getText().toString().isEmpty()){
                                if(parseDouble(destino.getText().toString()) >= 1000000 && parseDouble(monto.getText().toString()) <= 100000000 ){
                                    //Chequear el tipo de credito seleccionado
                                    double interes = 0;

                                    double xmonto = parseDouble(monto.getText().toString());
                                    double ncuotas = 0;
                                    if (rb12.isChecked()){
                                        ncuotas = 12;
                                    }
                                    if(rb24.isChecked()){
                                        ncuotas = 24;
                                    }
                                    if(rb36.isChecked()){
                                        ncuotas = 36;
                                 }

                                double totalDeuda = xmonto +(xmonto * ncuotas * interes);
                                }
                                else{
                                Toast.makeText(getApplicationContext(),"El monto debe estar de 1 a 100 millones",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Monto obligatoria",Toast.LENGTH_SHORT).show();
                            }*/
                }else {
                    Toast.makeText(getApplicationContext(), "Nombre obligatoria", Toast.LENGTH_SHORT).show();
                }


            }

        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre.setText("");
                fechaSalida.setText("");
                numeroPersonas.setText("");
                tourDias.setChecked(false);
                discoteca.setChecked(false);
                rb2.setChecked(false);
                rb4.setChecked(false);
                rb6.setChecked(false);
                totalPagar.setText("");


            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        destinoSeleccionado = tDestino[position];
        Toast.makeText(getApplicationContext(),tDestino[position],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

    /*destinoSeleccionado = tDestino[position];
        Toast.makeText(getApplicationContext(),tDestino[position],Toast.LENGTH_SHORT).show();*/