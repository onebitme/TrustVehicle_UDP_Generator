package com.example.trustvehicleudpgenerator.Models;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.trustvehicle.R;


public class CustomAlert extends AlertDialog {
    Context context;
    TextView textViewAlertTitle, textViewAlertMessageSubTitle, textViewAlertMessage;

    public CustomAlert(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alert_custom);
        initViews();
    }

    public void initViews() {
        textViewAlertTitle = findViewById(R.id.textViewAlertTitle);
        textViewAlertMessageSubTitle = findViewById(R.id.textViewAlertMessageSubTitle);
        textViewAlertMessage = findViewById(R.id.textViewAlertMessage);
    }

    public void setTextFields(eState state) {
        if (state == eState.INITIALIZATION) {
            textViewAlertTitle.setText(CONSTANTS.initializationTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.initializationSubTitle);
            textViewAlertMessage.setText(CONSTANTS.initializationAlertMessage);
        } else if (state == eState.APPROACHING) {
            textViewAlertTitle.setText(CONSTANTS.state01AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state01AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state01AlertMessage);
        } else if (state == eState.AVAILABLE_SLOTS_FOUND) {
            textViewAlertTitle.setText(CONSTANTS.state02AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state02AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state02AlertMessage);
        } else if (state == eState.AFTER_SELECT_SLOT) {
            textViewAlertTitle.setText(CONSTANTS.state03AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state03AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state03AlertMessage);
        } else if (state == eState.DRAW_PATH) {
            textViewAlertTitle.setText(CONSTANTS.state04AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state04AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state04AlertMessage);
        } else if (state == eState.TRUCK_MOVING) {
            textViewAlertTitle.setText(CONSTANTS.state05AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state05AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state05AlertMessage);
        } else if (state == eState.TRUCK_WITHIN_PARK) {
            textViewAlertTitle.setText(CONSTANTS.state06AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state06AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state06AlertMessage);
        } else if (state == eState.FINAL) {
            textViewAlertTitle.setText(CONSTANTS.state08AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state08AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state08AlertMessage);
        } else if (state == eState.ERROR_CANNOT_BE_PLANNED) {
            textViewAlertTitle.setText(CONSTANTS.state09AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state09AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state09AlertMessage);
        } else if (state == eState.ERROR_APPROACHING) {
            textViewAlertTitle.setText(CONSTANTS.state10AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state10AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state10AlertMessage);
        } else if (state == eState.ERROR_STARTING_SCREEN) {
            textViewAlertTitle.setText(CONSTANTS.state11AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state11AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state11AlertMessage);
        } else if (state == eState.INTERRUPT_STEERING_WHEEL) {
            textViewAlertTitle.setText(CONSTANTS.state12AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state12AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state12AlertMessage);
        } else if (state == eState.INTERRUPT_BRAKE) {
            textViewAlertTitle.setText(CONSTANTS.state13AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state13AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state13AlertMessage);
        } else if (state == eState.INTERRRUPT_THROTTLE) {
            textViewAlertTitle.setText(CONSTANTS.state14AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state14AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state14AlertMessage);
        } else if (state == eState.VEHICLE_OVERSPEED) {
            textViewAlertTitle.setText(CONSTANTS.state15AlertTitle);
            textViewAlertMessageSubTitle.setText(CONSTANTS.state15AlertSubTitle);
            textViewAlertMessage.setText(CONSTANTS.state15AlertMessage);
        }

    }
}
