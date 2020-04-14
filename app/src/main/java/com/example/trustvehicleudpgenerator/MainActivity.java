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

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    UDPHelper udpHelper;


    Button sensorOkBtn, sensorNOKBtn, algoSetBtn, algoResBtn,
            ORSetBtn,ORResBtn,GearD,GearN,GearR,
            Parking1, Parking2, Parking3, parkingReset,
            speedSet,speedReset, tvInitial, tvGoal, tvRandom;

    EditText editOR, editAlgoStates,editSpeed;
    Repository repository = Repository.getInstance();
    MainActivityViewModel mainActivityViewModel;
    TextView textViewLocalIP, textViewRandPos;

    float truckX,truckY,truckHeading,hitchAngle;
    int truckXint,truckYint,truckHeadingint,hitchAngleint;

    Random r1,r2,r3,r4;

    {
        r1 = new Random();
        r2 = new Random();
        r3 = new Random();
        r4 = new Random();
    }


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

        editAlgoStates = findViewById(R.id.editText);
        editOR = findViewById(R.id.editText2);
        editSpeed = findViewById(R.id.editText3);
        textViewRandPos=findViewById(R.id.randomPos);

        //TrustVehicle Initial and goalpos



    }
    public void onClick(View v) {
        if (v == sensorOkBtn) { //Sensor OK
            System.out.println("On Click Passed");
            mainActivityViewModel.changeOutGoingMessage(9,1);
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
        else if (v == algoSetBtn){
            String algoState = editAlgoStates.getText().toString();
            int algoStateInt = Integer.parseInt(algoState);
            mainActivityViewModel.changeOutGoingMessage(10,algoStateInt);
        }
        else if (v == algoResBtn){
            int algoStateInt = 0;
            mainActivityViewModel.changeOutGoingMessage(10,algoStateInt);
        }
        else if (v == ORSetBtn){
            String ORState = editOR.getText().toString();
            int ORStateInt = Integer.parseInt(ORState);
            mainActivityViewModel.changeOutGoingMessage(11,ORStateInt);
        }
        else if (v == ORResBtn){
            int algoStateInt = 0;
            mainActivityViewModel.changeOutGoingMessage(11,algoStateInt);
        }
        else if (v == Parking1) { //Parking 1
            mainActivityViewModel.changeOutGoingMessage(0, 1);
            Parking1.setBackgroundColor(Color.GREEN);
        }
        else if (v == Parking2) { //Parking 2
            mainActivityViewModel.changeOutGoingMessage(1, 1);
            Parking2.setBackgroundColor(Color.GREEN);
        }
        else if (v == Parking3) { //Parking 3
            mainActivityViewModel.changeOutGoingMessage(2, 1);
            Parking3.setBackgroundColor(Color.GREEN);
        }
        else if (v == Parking3) { //Parking 3
            mainActivityViewModel.changeOutGoingMessage(2, 1);
            sensorOkBtn.setBackgroundColor(Color.GREEN);
        }
        else if (v == parkingReset) { //set all parking to unavailable
            mainActivityViewModel.changeOutGoingMessage(0, 0);
            mainActivityViewModel.changeOutGoingMessage(1, 0);
            mainActivityViewModel.changeOutGoingMessage(2, 0);
            Parking1.setBackgroundColor(Color.GRAY);
            Parking2.setBackgroundColor(Color.GRAY);
            Parking3.setBackgroundColor(Color.GRAY);
        }
        else if (v == speedSet){
            String Speed = editSpeed.getText().toString();
            int VehicleSpeed = Integer.parseInt(Speed);
            mainActivityViewModel.changeOutGoingMessage(12,VehicleSpeed);
        }else if (v==speedReset){
            int VehicleSpeed = 0;
            mainActivityViewModel.changeOutGoingMessage(12,VehicleSpeed);
        }
        else if (v == tvInitial){
            truckX = 135f;
            truckY = 42f;
            truckHeading = 0.01f; //in radians
            hitchAngle = 0.01f; //in radians

            truckXint = Math.round(truckX*10f+65536f);
            truckYint = Math.round(truckY*10f+65536f);
            truckHeadingint = Math.round(truckHeading*1000f+65536f);
            hitchAngleint = Math.round(hitchAngle*10f+65536f);

            mainActivityViewModel.changeOutGoingMessage(5,truckXint);
            mainActivityViewModel.changeOutGoingMessage(6,truckYint);
            mainActivityViewModel.changeOutGoingMessage(7,truckHeadingint);
            mainActivityViewModel.changeOutGoingMessage(8,hitchAngleint);
            textViewRandPos.setText("truckX: "+ truckX + "//truckY: "+ truckY +"//truck heading: " +truckHeading+"//trailer heading: " + hitchAngle);

        }
        else if (v == tvGoal){
            truckX = 85f;
            truckY = 24f;
            truckHeading = 1.5707f; //in radians
            hitchAngle = 1.5707f; //in radians

            truckXint = Math.round(truckX*10f+65536f);
            truckYint = Math.round(truckY*10f+65536f);
            truckHeadingint = Math.round(truckHeading*1000f+65536f);
            hitchAngleint = Math.round(hitchAngle*10f+65536f);

            mainActivityViewModel.changeOutGoingMessage(5,truckXint);
            mainActivityViewModel.changeOutGoingMessage(6,truckYint);
            mainActivityViewModel.changeOutGoingMessage(7,truckHeadingint);
            mainActivityViewModel.changeOutGoingMessage(8,hitchAngleint);
            textViewRandPos.setText("truckX: "+ truckX + "//truckY: "+ truckY +"//truck heading: " +truckHeading+"//trailer heading: " + hitchAngle);

        }
        else if (v == tvRandom){
            truckX = 80f-r1.nextFloat()*(114f-80f);
            truckY = 20f-r2.nextFloat()*(42f-24f);
            truckHeading = 0.01f+(1.5707f-0.01f)*r3.nextFloat(); //in radians
            hitchAngle = 0.01f+(1.5707f-0.01f)*r4.nextFloat(); //in radians

            truckXint = Math.round(truckX*10f+65536f);
            truckYint = Math.round(truckY*10f+65536f);
            truckHeadingint = Math.round(truckHeading*1000f+65536f);
            hitchAngleint = Math.round(hitchAngle*10f+65536f);

            mainActivityViewModel.changeOutGoingMessage(5,truckXint);
            mainActivityViewModel.changeOutGoingMessage(6,truckYint);
            mainActivityViewModel.changeOutGoingMessage(7,truckHeadingint);
            mainActivityViewModel.changeOutGoingMessage(8,hitchAngleint);

            textViewRandPos.setText("truckX: "+ truckX + "//truckY: "+ truckY +"//truck heading: " +truckHeading+"//trailer heading: " + hitchAngle);


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

        Parking1 = findViewById(R.id.park1Available);
        Parking2 = findViewById(R.id.park2Available);
        Parking3 = findViewById(R.id.park3Available);
        parkingReset = findViewById(R.id.parkingReset);

        speedSet = findViewById(R.id.speedSubmit);
        speedReset = findViewById(R.id.speedReset);

        tvInitial = findViewById(R.id.tvInitial);
        tvGoal = findViewById(R.id.tvGoal);
        tvRandom = findViewById(R.id.tvRandom);

        sensorOkBtn.setOnClickListener(this);
        sensorNOKBtn.setOnClickListener(this);
        algoSetBtn.setOnClickListener(this);
        algoResBtn.setOnClickListener(this);
        ORSetBtn.setOnClickListener(this);
        ORResBtn.setOnClickListener(this);
        GearD.setOnClickListener(this);
        GearN.setOnClickListener(this);
        GearR.setOnClickListener(this);
        Parking1.setOnClickListener(this);
        Parking2.setOnClickListener(this);
        Parking3.setOnClickListener(this);
        parkingReset.setOnClickListener(this);
        tvInitial.setOnClickListener(this);
        tvGoal.setOnClickListener(this);
        tvRandom.setOnClickListener(this);

    }


    public void initOtherObjects() {
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        udpHelper = new UDPHelper(this);
    }
}
