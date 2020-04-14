package com.example.trustvehicleudpgenerator.Helpers;

import android.content.res.Resources;

import com.example.trustvehicleudpgenerator.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TTStatesGenerator {

    Resources res;


    public float[][] parseCSV(){
        /*
        var floatCoordinatesString = coordinatePath.replace("\t","f,")
        var floatCoordinatesStringArray = floatCoordinatesString.split(",").toTypedArray();
        var j=0;
        var floatCoordinatesXY = FloatArray(floatCoordinatesStringArray.size)
        for (key in floatCoordinatesStringArray){
            if (j % 2 == 0){
                floatCoordinatesXY[j] = key.toFloat()*10f//-200f
            }
            else if (j%2==1){
                floatCoordinatesXY[j] = 900f- key.toFloat()*10f//-200f
            }
            j=j+1
        }*/

        String x_pos = res.getResourceEntryName(R.raw.xpos);
        String y_pos = res.getResourceEntryName(R.raw.ypos);
        String truck_theta = res.getResourceEntryName(R.raw.trucktheta);
        String trailer_theta = res.getResourceEntryName(R.raw.trailertheta);

        /*try {


            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            result = new String(b);
        } catch (Exception e) {
            // e.printStackTrace();
            result = "Error: can't show file.";
        }*/


        //TODO: Returns fromString2Floatvalues 4x.... Matrix
        final float[][] floats = new float[4][10];
        return floats;
    }

    public float[] makeStates(){

        //TODO: Returns a 1x4 float everytime it is called
        final float[] floats = new float[30];
        return floats;
    }

}
