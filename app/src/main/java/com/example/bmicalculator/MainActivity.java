package com.example.bmicalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edtWei;
    EditText edtHei;
    Button  btnBmi;
    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void calBMI(View view){

        edtWei = findViewById(R.id.edtWei);
        edtHei = findViewById(R.id.edtHei);
        btnBmi = findViewById(R.id.btnBmi);
        status = findViewById(R.id.status);


        String strWei = edtWei.getText().toString();
        String strHei = edtHei.getText().toString();
        if(!strHei.isEmpty() && !strWei.isEmpty()){

            float Wei = Float.parseFloat(edtWei.getText().toString());
            float Hei = Float.parseFloat(edtHei.getText().toString());

//            convert the height meter to the cm
              Hei = Hei/100;
              float bmi = Wei/(Hei * Hei);
              String str;

              if(bmi < 18){
                  Intent obj = new Intent(MainActivity.this,UnderWeight.class);
                  str = String.format("BMI : %.1f\nYou are UnderWeight",bmi);
                  obj.putExtra("data pass",str);
//                  clUnder.setBackgroundColor(getResources().getColor(R.color.colorUnder));
                  startActivity(obj);
              }else if(bmi > 24){
                  Intent obj = new Intent(MainActivity.this, OverWeight.class);
                  str = String.format("BMI : %.1f\nYou are OverWeight",bmi);
                  obj.putExtra("data pass",str);
//                  clOver.setBackgroundColor(getResources().getColor(R.color.colorOver));
                  startActivity(obj);
              }else{
                  Intent obj = new Intent(MainActivity.this, healthy.class);
                  str = String.format("BMI : %.1f\nYou are Healthy",bmi);
                  obj.putExtra("data pass",str);
//                  clHealthy.setBackgroundColor(getResources().getColor(R.color.colorHealthy ));
                  startActivity(obj);
              }

        }else {
            status.setText("Please Enter the Valid Details.");
        }

    }
}