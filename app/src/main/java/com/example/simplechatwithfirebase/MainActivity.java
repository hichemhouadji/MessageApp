package com.example.simplechatwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtmsg;
    EditText txtnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
txtmsg=(EditText)findViewById(R.id.textMessage);
        txtnumber=(EditText)findViewById(R.id.textPhoneNumber);

    }


    public void btn_send(View view) {
        int permisoinCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

        if (permisoinCheck == PackageManager.PERMISSION_GRANTED) {
            MyMessage();
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 0);
        }

    }

    private void MyMessage() {
        String phoneNumber = txtnumber.getText().toString().trim();
        String message = txtmsg.getText().toString().trim();
        if (!txtnumber.getText().toString().equals("") || !txtmsg.getText().toString().equals("")) {


            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "message sent", Toast.LENGTH_SHORT).show();


        }
        else {
            Toast.makeText(this, " please entre message or number!", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 0:
                if (grantResults.length>=0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    MyMessage();
                }

                else {

                    Toast.makeText(this, " you dont have permisionnes", Toast.LENGTH_SHORT).show();

                }
                break;
        }



    }
}