package com.example.trustvehicleudpgenerator.Helpers;

import android.util.Log;

import com.example.trustvehicleudpgenerator.MainActivity;
import com.example.trustvehicleudpgenerator.Models.CONSTANTS;
import com.example.trustvehicleudpgenerator.Models.eConnectionState;
import com.example.trustvehicleudpgenerator.Repositories.Repository;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPHelper extends Thread {
    private static String LOG_TAG = ("Trust-UDPHelper");
    private DatagramSocket socket;
    private DatagramPacket packetReceived, packetSent;
    private MainActivity mainActivity;

    private int receivePort, sendPort;
    private String receiveAdress, sendAddress;
    private Repository repository=Repository.getInstance();
    private byte[] incomingMessage, outgoingMessage;
    private MessageDecodeHelper messageDecodeHelper=new MessageDecodeHelper();
    private byte[] previousIncomingMessage = new byte[CONSTANTS.incomingMessageSize];
    private byte[] previousOutgoingMessage = new byte[CONSTANTS.outgoingMessageSize];
    private StateHelper stateHelper=new StateHelper();


    public UDPHelper(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        super.run();
        updateUDPData();
        packetSent = new DatagramPacket(outgoingMessage, outgoingMessage.length);
        packetReceived = new DatagramPacket(incomingMessage, CONSTANTS.incomingMessageSize);
        disconnectFromSocket();
        while (true) {
            updateUDPData();
            if (socket == null) {
                repository.setConnectionState(eConnectionState.NOT_CONNECTED);
                Log.d(LOG_TAG, "Socket is null. Creating new socket");
                createSocket();
            } else if (socket.isConnected()) {
                repository.setConnectionState(eConnectionState.CONNECTED);

                if (outgoingMessage != null) {
                    previousOutgoingMessage = Repository.getInstance().getOutgoingMessage();
                    sendData();
                } else {
                    Log.w(LOG_TAG, "Outgoing Message has not yet been set!");
                    packetSent = new DatagramPacket(outgoingMessage, outgoingMessage.length);
                }

                if (incomingMessage != null) {
                    receiveData();
                } else {
                    Log.w(LOG_TAG, "Incoming Message has not yet been set!");
                    packetReceived = new DatagramPacket(incomingMessage, incomingMessage.length);
                }

            }else{
                Log.w(LOG_TAG,"Socket is not connected. Restarting socket");
                disconnectFromSocket();
            }
        }


    }

    private void createSocket() {
        try {
            socket = new DatagramSocket(receivePort, InetAddress.getByName(receiveAdress));

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in creating socket: " + e.getMessage());
            repository.setConnectionState(eConnectionState.NOT_CONNECTED);
            return;
        }
        try {
            socket.setReuseAddress(true);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Can't set address reusable: " + e.getMessage());
            repository.setConnectionState(eConnectionState.NOT_CONNECTED);
        }
        try {
            socket.connect(InetAddress.getByName(sendAddress), sendPort);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Can't connect socket: " + e.getMessage());
            repository.setConnectionState(eConnectionState.NOT_CONNECTED);
        }
        try {
            socket.setSoTimeout(5000);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Can't set timeout: " + e.getMessage());
            repository.setConnectionState(eConnectionState.NOT_CONNECTED);
        }
        try {
            socket.setBroadcast(false);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Can't set broadcast: " + e.getMessage());
            repository.setConnectionState(eConnectionState.NOT_CONNECTED);
        }
    }

    private void sendData() {

        try {
            socket.send(packetSent);
            if (isChanged(previousOutgoingMessage, outgoingMessage)) {
                packetSent.setData(outgoingMessage);
                Log.i(LOG_TAG, "Sent data is: " + Arrays.toString(packetSent.getData()));
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in sending packet: " + e.getMessage());
            socket.disconnect();
            socket.close();
            socket = null;
        }
    }

    private void receiveData() {
        try {
            socket.receive(packetReceived);
            repository.setIncomingMessage(packetReceived.getData());

            if (isChanged(previousIncomingMessage, repository.getIncomingMessage())) {
                Log.i(LOG_TAG, "Received data size: "+messageDecodeHelper.convertReceivedArray(packetReceived.getData()).length+" array: "+ Arrays.toString(messageDecodeHelper.convertReceivedArray(packetReceived.getData())));
                repository.setIncomingLongMessage(messageDecodeHelper.convertReceivedArray(packetReceived.getData()));
                messageDecodeHelper.updatePathArray(repository.getIncomingLongMessage());
                stateHelper.monitorState();
                stateHelper.monitorPosition();
                repository.postUpdateIsIncomingMessageChanged(true);
            }

        } catch (Exception e) {
            Log.e(LOG_TAG, "Error in receiving packet: " + e.getMessage());
            repository.setConnectionState(eConnectionState.NOT_CONNECTED);
            socket.disconnect();
            socket.close();
            socket = null;
        }
    }

    private void makeEqual(byte[] a1, byte[] a2) {
        if (a1.length == a2.length) {
            System.arraycopy(a2, 0, a1, 0, a1.length);
        }
    }

    private boolean isChanged(byte[] beforeArray, byte[] afterArray) {
        boolean isDifferent = false;
        if (Arrays.toString(beforeArray).equals(Arrays.toString(afterArray))) {
            isDifferent = false;
        } else if (!Arrays.toString(beforeArray).equals(Arrays.toString(afterArray))) {
            makeEqual(beforeArray, afterArray);
            isDifferent = true;
        }
        return isDifferent;
    }

    public void updateUDPData() {

        if (Repository.getInstance().getOutgoingMessage() != null) {
            outgoingMessage = Repository.getInstance().getOutgoingMessage();
        } else {
            Log.w(LOG_TAG, "Outgoing Message is not yet set. Setting up an empty array as placeholder.");
            outgoingMessage = new byte[CONSTANTS.outgoingMessageSize];
            Repository.getInstance().setOutgoingMessage(outgoingMessage);
        }

        if (Repository.getInstance().getIncomingMessage() != null) {
            incomingMessage = Repository.getInstance().getIncomingMessage();
        } else {
            Log.w(LOG_TAG, "No message received yet");
            incomingMessage=new byte[CONSTANTS.incomingMessageSize];
            repository.setIncomingMessage(incomingMessage);
        }

        if (Repository.getInstance().getOutgoingMessage()!=null){
            outgoingMessage = Repository.getInstance().getOutgoingMessage();
        } else {
            Log.w(LOG_TAG, "Send Message is not yet set");
            outgoingMessage=new byte[CONSTANTS.outgoingMessageSize];
            repository.setOutgoingMessage(outgoingMessage);
        }
        receivePort = CONSTANTS.receivePort;
        sendPort = CONSTANTS.sendPort;
        sendAddress = CONSTANTS.sendAddress;

        if (Repository.getInstance().getReceiveAddress() != null) {
            receiveAdress = Repository.getInstance().getReceiveAddress();
        } else {
            Log.w(LOG_TAG, "Local adress is not yet set");
        }
    }

    private void disconnectFromSocket() {
        if (socket != null) {
            socket.disconnect();
            socket.close();
        } else {
            Log.e(LOG_TAG, "Socket is null cannot disconnect.");
        }

    }


}
