package com.example.trustvehicleudpgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trustvehicleudpgenerator.Helpers.UDPHelper;
import com.example.trustvehicleudpgenerator.Models.CONSTANTS;
import com.example.trustvehicleudpgenerator.Repositories.Repository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    UDPHelper udpHelper;
    Button button, button2;
    EditText overrideReasons;
    Repository repository = Repository.getInstance();
    MainActivityViewModel mainActivityViewModel;
    TextView textViewLocalIP;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClickListeners();
        initOtherObjects();



        mainActivityViewModel.init();
        mainActivityViewModel.setLocalIp(textViewLocalIP);
        mainActivityViewModel.startUDP(udpHelper);
        mainActivityViewModel.setMainActivity(this);




    }
    public void onClick(View v) {
        if (v == button) { //Sensor OK
            System.out.println("On Click Passed");
            mainActivityViewModel.changeOutGoingMessage(85,20,15);
            //0dan 1 array güncelleyecek position xdeki
            button.setBackgroundColor(Color.GREEN);
        }
        else if (v == button2) { //Sensor NOK
            mainActivityViewModel.changeOutGoingMessage(0,0,0);
            button.setBackgroundColor(Color.GREEN);
        }

    }

    public void setOnClickListeners() {

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }


    public void initOtherObjects() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        udpHelper = new UDPHelper(this);
    }
}
