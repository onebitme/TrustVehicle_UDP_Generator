package com.example.trustvehicleudpgenerator.Helpers;

import com.example.trustvehicleudpgenerator.Models.CONSTANTS;
import com.example.trustvehicleudpgenerator.Repositories.Repository;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class MessageDecodeHelper {
    private Repository repository = Repository.getInstance();
    private float[] pathArray = new float[CONSTANTS.incomingMessageSize - CONSTANTS.startOfPathValues]; //Create an empty array equal to the size of expected path array

    public long[] convertReceivedArray(byte[] receivedMessage) {
        IntBuffer intBuffer =
                ByteBuffer.wrap(receivedMessage)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .asIntBuffer();
        int[] array = new int[intBuffer.remaining()];
        intBuffer.get(array);

        long[] longArray = new long[array.length];

        for (int i = 0; i < longArray.length; i++) {
            longArray[i] = array[i] & 0x00000000ffffffffL;
        }

        return longArray;
    }

    public void updatePathArray(long[] incomingMessage) {
        //Take part of incomingmessage and convert it to float in order to create path array
        int messageLength = incomingMessage.length;
        int startHere = 14;
        long [] dummyArray = incomingMessage;
        int forwardCounter = 0; // Number for Forwards Maneuvers
        int reverseCounter = 0; // Number for Backwards Maneuvers*/
        float[] maneuverArray = new float[incomingMessage.length-startHere];
        int k = 0;
        for (int i = startHere; i < messageLength; i++) {
            /*maneuverArray = new long [(forwardCounter+reverseCounter)*32];

            if (incomingMessage[i] == -1){
                reverseCounter = reverseCounter + 1;
            }
            else if (incomingMessage[i] == 1){
                forwardCounter = forwardCounter+1;
            }
            else if (incomingMessage[i]!= 0
                    && incomingMessage[i]!=-1
                    && incomingMessage[i]!=1){
                maneuverArray[k] = incomingMessage[i];
                k = k+1;*/
            if (k%2==0){
                maneuverArray[k] = incomingMessage[i]-0f;
            }
            else if (k%2==1){
                maneuverArray[k] = 250f-incomingMessage[i];
            }
            k=k+1;
        }
        repository.setPathArray(maneuverArray);
    }

    public byte[] convertArrayToBeSent(int[] messageToBeSent) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(messageToBeSent.length * 4);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(messageToBeSent);

        return byteBuffer.array();
    }


}
