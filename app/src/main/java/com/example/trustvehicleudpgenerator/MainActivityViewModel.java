package com.example.trustvehicleudpgenerator;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.trustvehicleudpgenerator.Helpers.UDPHelper;
import com.example.trustvehicleudpgenerator.Models.eState;
import com.example.trustvehicleudpgenerator.Repositories.Repository;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivityViewModel extends AndroidViewModel {
    private static String LOG_TAG = "OCUL - MainActivityViewModel";
    private Repository repository = Repository.getInstance();
    //private CustomAlert alert;
    private Context context;
    private MainActivity mainActivity;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public void init() {
        getLocalIpAddress();
        updateCurrentState(eState.INITIALIZATION);
    }

    public MutableLiveData<eState> getCurrentState() {
        return repository.getCurrentState();
    }

    public void updateCurrentState(eState state) {
        repository.setUpdateCurrentState(state);
    }


    public void getLocalIpAddress() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (enumNetworkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = enumNetworkInterfaces
                        .nextElement();
                Enumeration<InetAddress> enumInetAddress = networkInterface
                        .getInetAddresses();
                while (enumInetAddress.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddress.nextElement();

                    if (inetAddress.isSiteLocalAddress()) {
                        ip += inetAddress.getHostAddress();
                    }

                }

            }

        } catch (SocketException e) {
            e.printStackTrace();
            ip += "Something Wrong! " + e.toString() + "\n";
        }
        repository.setReceiveAddress(ip);
    }

    public void startUDP(UDPHelper udpHelper) {
        udpHelper.start();
    }

    public void setLocalIp(TextView textView) {
        if (repository.getReceiveAddress() != null) {
            textView.setText(repository.getReceiveAddress());
        } else {
            textView.setText("Local IP N/A");
        }
    }

    public void changeOutGoingMessage(int pos, int a) {
        repository.getOutgoingMessage()[pos] = (byte) a;
    }



    public MutableLiveData<Boolean> isIncomingMessageChanged() {
        return repository.getIsIncomingMessageChanged();
    }

    public void setMainActivity(MainActivity activity) {
        repository.setMainActivity(activity);
    }


}



