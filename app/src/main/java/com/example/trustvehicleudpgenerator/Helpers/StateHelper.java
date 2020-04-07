package com.example.trustvehicleudpgenerator.Helpers;

import android.util.Log;

import com.example.trustvehicleudpgenerator.Models.CONSTANTS;
import com.example.trustvehicleudpgenerator.Models.eState;
import com.example.trustvehicleudpgenerator.Repositories.Repository;

public class StateHelper {
    private static String LOG_TAG = ("OCUL-StateHelper");

    Repository repository = Repository.getInstance();

    public void monitorState() {
        //Decide On Which State We Are On
        long[] messageArray = repository.getIncomingLongMessage();
        //Check For Vehicle Speee
        if (messageArray[12] >100) {
            repository.postUpdateCurrentState(eState.VEHICLE_OVERSPEED);
        } else
            //Check For Override Reasons
            if (messageArray[11] == 1) {
                repository.postUpdateCurrentState(eState.INTERRUPT_STEERING_WHEEL);
            } else if (messageArray[11] == 2) {
                repository.postUpdateCurrentState(eState.INTERRUPT_BRAKE);
            } else if (messageArray[11] == 3) {
                repository.postUpdateCurrentState(eState.INTERRRUPT_THROTTLE);
            } else if (messageArray[11] == 0) {
                //Check For Sensor
                if (messageArray[9] == 0) {
                    repository.postUpdateCurrentState(eState.ERROR_STARTING_SCREEN);
                } else if (messageArray[9] == 1) {
                    //Check For Truck XY
                    if (messageArray[5] > CONSTANTS.maxXValue || messageArray[5] > CONSTANTS.maxYValue) {
                        repository.postUpdateCurrentState(eState.ERROR_APPROACHING);
                    } else {
                        //Check Algo States
                        if (messageArray[10] == 2 || messageArray[10] == 3) {
                            repository.postUpdateCurrentState(eState.TRUCK_MOVING);
                        } else if (messageArray[10] == 0) {
                            //Check For Parking State
                            if (messageArray[0] == 0 && messageArray[1] == 0 && messageArray[2] == 0) {
                                repository.postUpdateCurrentState(eState.APPROACHING);
                            } else {
                                repository.postUpdateCurrentState(eState.AVAILABLE_SLOTS_FOUND);
                            }
                        } else if (messageArray[10] == 1) {
                            //Check If Path Is Empty
                            if (messageArray[14] == 1 || messageArray[14] == -1) {
                                repository.postUpdateCurrentState(eState.DRAW_PATH);
                            } else {
                                repository.postUpdateCurrentState(eState.AFTER_SELECT_SLOT);
                            }
                        } else if (messageArray[10] == 4) {
                            repository.postUpdateCurrentState(eState.TRUCK_WITHIN_PARK);
                        } else if (messageArray[10] == 5) {
                            repository.postUpdateCurrentState(eState.FINAL);
                        } else if (messageArray[10] == 6) {
                            repository.postUpdateCurrentState(eState.ERROR_CANNOT_BE_PLANNED);
                        }
                    }
                }
            }
    }

    float pi = (float) Math.PI;

    public void monitorPosition(){
        long[] messageArray = repository.getIncomingLongMessage();
        float[] posArray = new float[4];

        posArray[0] =  -360f+((messageArray[5]-65536f)/10f)*11f; // Her Bir metre 11 Pixeldir
        System.out.println("X Coordinate:" + ((messageArray[5]-65536f)/10f) + "Pos Array Truck Pixel X: " + posArray[0]);
        posArray[1] =  460f+26f-((messageArray[6]-65536f)/10f)*11f; // Her Bir Metre 11 pixeldir
        System.out.println("Y Coordinate :" + ((messageArray[6]-65536f)/10f) + "Pos Array Truck Pixel Y: " + posArray[1]);
        posArray[2] = 90f-(((((messageArray[7])-65536f)/10f)%(2f*pi))*180f/pi);
        System.out.println("Pos Array Truck Angle :" + posArray[2]);
        posArray[3] = 90f-(((((messageArray[8])-65536f)/10f)%(2f*pi))*180f/pi);
        System.out.println("Pos Array Trailer Angle" + posArray[3]);

        repository.setTruckPos(posArray);
        Log.d(LOG_TAG, "Updated postion changed status: ");


    }

}
