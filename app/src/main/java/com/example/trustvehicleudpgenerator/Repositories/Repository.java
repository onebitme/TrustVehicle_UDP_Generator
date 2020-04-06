package com.example.trustvehicleudpgenerator.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.trustvehicleudpgenerator.Helpers.UDPHelper;
import com.example.trustvehicleudpgenerator.MainActivity;
import com.example.trustvehicleudpgenerator.Models.eConnectionState;
import com.example.trustvehicleudpgenerator.Models.eState;

public class Repository {

    //Singleton Structure
    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    //States
    private MutableLiveData<eState> currentState = new MutableLiveData<>();

    //UDP
    private byte[] incomingMessage;
    private byte[] outgoingMessage;
    private long[] incomingLongMessage;
    private int[] outgoingIntMessage;
    private String receiveAddress;
    private UDPHelper udpHelper;

    //Path
    private float[] pathArray;

    //Position
    private float[] truckPos;
    private MutableLiveData<Boolean> isIncomingMessageChanged = new MutableLiveData<>();

    //States
    public MutableLiveData<eState> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(MutableLiveData<eState> currentState) {
        this.currentState = currentState;
    }

    //Connection Status
    private eConnectionState connectionState;

    //Main Activity
    private MainActivity mainActivity;

    public void setUpdateCurrentState(eState state) {
        currentState.setValue(state);
    }

    public void postUpdateCurrentState(eState state) {
        currentState.postValue(state);
    }

    //UDP

    public byte[] getIncomingMessage() {
        return incomingMessage;
    }

    public void setIncomingMessage(byte[] incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    public byte[] getOutgoingMessage() {
        return outgoingMessage;
    }

    public void setOutgoingMessage(byte[] outgoingMessage) {
        this.outgoingMessage = outgoingMessage;
    }

    public long[] getIncomingLongMessage() {
        return incomingLongMessage;
    }

    public void setIncomingLongMessage(long[] incomingLongMessage) {
        this.incomingLongMessage = incomingLongMessage;
    }

    public int[] getOutgoingIntMessage() {
        return outgoingIntMessage;
    }

    public void setOutgoingIntMessage(int[] outgoingIntMessage) {
        this.outgoingIntMessage = outgoingIntMessage;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public UDPHelper getUdpHelper() {
        return udpHelper;
    }

    public void setUdpHelper(UDPHelper udpHelper) {
        this.udpHelper = udpHelper;
    }

    //Path
    public float[] getPathArray() {
        return pathArray;
    }
    public void setPathArray(float[] pathArray) {
        this.pathArray = pathArray;
    }
    //Position
    public float[] getTruckPos() {
        return truckPos;
    }
    public void setTruckPos(float[] truckPos) {
        this.truckPos = truckPos;
    }

    public MutableLiveData<Boolean> getIsIncomingMessageChanged() {
        return isIncomingMessageChanged;
    }

    public void postUpdateIsIncomingMessageChanged(Boolean isIncomingMessageChanged) {
        this.isIncomingMessageChanged.postValue(isIncomingMessageChanged);
    }

    //Main Activity

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    //Connection Status

    public eConnectionState getConnectionState() {
        return connectionState;
    }

    public void setConnectionState(eConnectionState connectionState) {
        this.connectionState = connectionState;
    }
}
