package com.example.vmp;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1052);

        }
    }


    @Override
   public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            public void onClick(View v) {
                AlertDialog.Builder aBuilder = new AlertDialog.Builder(MainActivity.this);
                aBuilder.setMessage("При вводе данных информация в полях очистится");
                aBuilder.setCancelable(false);
                aBuilder.setPositiveButton("Да", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick (DialogInterface dialogInterface, int i){
                      writeFinal();
                    }
                });
                aBuilder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                    AlertDialog alert = aBuilder.create();
                    alert.setTitle("Ввести данные?");
                    alert.show();

            }
            public void writeFinal(){
                final EditText editText = findViewById(R.id.ID1);
                String name = editText.getText().toString(); // Присвоение переменной значение поля
                String output = "\",\"p1_fio\":\""+editText.getText().toString();
                int year = Calendar.getInstance().get(Calendar.YEAR);
                editText.setText("");
                EditText editText1 = findViewById(R.id.ID2);
                output = output + "\",\"p2_dr\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID3);
                output = output + "\",\"p22_sex\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID4);
                output = output + "\",\"p3_addr_reg\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID5);
                output = output + "\",\"p4_addr_live\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID6);
                output = output + "\",\"p5_doc\":\"Паспорт гражданина РФ " + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID7);
                output = output + "\",\"p6_oms\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID8);
                output = output + "\",\"p7_snils\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID9);
                output = output + "\",\"p8_lgota\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID10);
                output = output + "\",\"p9_social_grupp\":\"" + editText1.getText().toString();
                editText1.setText("");
                editText1 = findViewById(R.id.ID11);
                output = output + "\",\"p10_phone\":\"" + editText1.getText().toString();
                editText1.setText("");



                if (!Environment.getExternalStorageState().equals(
                        Environment.MEDIA_MOUNTED)) {
                    return;
                }
                // получаем путь к SD
                File sdPath = Environment.getExternalStorageDirectory();
                // добавляем свой каталог к пути
                sdPath = new File(sdPath.getAbsolutePath() + "/" + "Download");
                // создаем каталог
                sdPath.mkdirs();
                // формируем объект File, который содержит путь к файлу
                File sdFile = new File(sdPath, name+".json");
                try {
                    // открываем поток для записи
                    BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
                    int month = java.util.Calendar.getInstance().get(Calendar.MONTH);
                    month++;
                    bw.write("{\"p00_dd\":\""+ Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "\",\"p01_mm\":\"" + month + "\",\"p02_yyyy\":\"" + Calendar.getInstance().get(Calendar.YEAR) + "" +output+"\",\"p11_diag_osn\":\"\",\"p12_profile\":\"\",\"p13_vmp_group_num\":\"\",\"p14_vmp_vid_code\":\"\",\"p15_vmp_vid_name\":\"\",\"p16_diag_code_mkb10\":\"\",\"p17_mo_name\":\"\",\"p18_lech_prof_mer\":\"\",\"p19_lab_issl\":\"\",\"p20_instr_issl\":\"\",\"p21_ill_history\":\"\"}");        // пишем данные
                    // закрываем поток
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        });
    }


}
