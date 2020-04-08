package com.example.trustvehicleudpgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trustvehicleudpgenerator.Helpers.UDPHelper;
import com.example.trustvehicleudpgenerator.Repositories.Repository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    UDPHelper udpHelper;
    Button sensorOkBtn, sensorNOKBtn, algoSetBtn, algoResBtn,
            ORSetBtn,ORResBtn,GearD,GearN,GearR;
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
        if (v == sensorOkBtn) { //Sensor OK
            System.out.println("On Click Passed");
            mainActivityViewModel.changeOutGoingMessage(9,2815);
            //0dan 1 array güncelleyecek position xdeki
            sensorOkBtn.setBackgroundColor(Color.GREEN);
        }
        else if (v == sensorNOKBtn) { //Sensor NOK
            mainActivityViewModel.changeOutGoingMessage(9,0);
            sensorOkBtn.setBackgroundColor(Color.GREEN);
        }
        else if (v == GearD){
            mainActivityViewModel.changeOutGoingMessage(13,1);
        }
        else if (v == GearN){
            mainActivityViewModel.changeOutGoingMessage(13,0);
        }
        else if (v == GearR){
            mainActivityViewModel.changeOutGoingMessage(13,2);
        }

    }

    public void setOnClickListeners() {

        sensorOkBtn = findViewById(R.id.sensorOK);
        sensorNOKBtn = findViewById(R.id.sensorNOK);
        algoSetBtn = findViewById(R.id.algoSubmit);
        algoResBtn = findViewById(R.id.algoReset);
        ORSetBtn = findViewById(R.id.overrideSubmit);
        ORResBtn = findViewById(R.id.overrideReset);
        GearD = findViewById(R.id.gearD);
        GearN = findViewById(R.id.gearN);
        GearR = findViewById(R.id.gearR);

        sensorOkBtn.setOnClickListener(this);
        sensorNOKBtn.setOnClickListener(this);
        algoSetBtn.setOnClickListener(this);
        algoResBtn.setOnClickListener(this);
        ORSetBtn.setOnClickListener(this);
        ORResBtn.setOnClickListener(this);
        GearD.setOnClickListener(this);
        GearN.setOnClickListener(this);
        GearR.setOnClickListener(this);

    }


    public void initOtherObjects() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        udpHelper = new UDPHelper(this);
    }
}
