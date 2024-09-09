package com.example.dialogos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt1 = findViewById(R.id.bt1);
        Button bt2 = findViewById(R.id.bt2);
        bt1.setOnClickListener((View.OnClickListener) this);
        bt2.setOnClickListener((View.OnClickListener) this);
        text1= findViewById(R.id.text1);
        //AlertDialog.Builder const1 = new AlertDialog.Builder(peekAvailableContext().getApplicationContext());

    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.bt1:
                Toast.makeText(peekAvailableContext().getApplicationContext(), "Precionaste botón Dialogo 1", Toast.LENGTH_LONG).show();
                AlertDialog.Builder constructorDial = new AlertDialog.Builder(MainActivity.this);
                constructorDial.setIcon(R.mipmap.ic_launcher);
                constructorDial.setMessage("Mensaje del dialogo 1");

                constructorDial.setPositiveButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Presionaste Cancelar del Dialogo 1", Toast.LENGTH_LONG).show();
                    }
                });


                constructorDial.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Presionaste Aceptar del Dialogo 1", Toast.LENGTH_LONG).show();
                    }
                });


                AlertDialog alerta = constructorDial.create();
                alerta.show();


                break;
            case R.id.bt2:
                Toast.makeText(peekAvailableContext().getApplicationContext(), "Precionaste botón Dialogo 2", Toast.LENGTH_LONG).show();
                ProgressDialog dialogoProgeso = new ProgressDialog(MainActivity.this);
                dialogoProgeso.setIcon(R.mipmap.ic_launcher_round);
                dialogoProgeso.setTitle("Dialogo de progreso");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i=0; i<=100;i++){
                            try {
                                Thread.sleep(200);
                                dialogoProgeso.setProgress(i);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        text1.setText("" + dialogoProgeso.getProgress());
                                    }
                                });
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();
                            }
                            if (dialogoProgeso.getProgress()==100){
                                dialogoProgeso.dismiss();
                            }
                        }
                    }
                }).start();

                AlertDialog alerta2;
                alerta2 = new AlertDialog.Builder(MainActivity.this).create();
                alerta2.setTitle("Alerta 2");
                alerta2.show();
                break;

        }
    }
    
}