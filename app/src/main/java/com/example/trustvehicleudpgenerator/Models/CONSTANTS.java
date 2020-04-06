package com.example.trustvehicleudpgenerator.Models;

public class CONSTANTS {


    //UDP Values
    public static int receivePort =43442;
    //public static int sendPort = 43445;
    //public static String sendAddress="192.168.140.99";
    //TODO: Macbook Pro - Packetsender Test
    public static int sendPort = 54620;
    public static String sendAddress="192.168.1.23";
    public static int incomingMessageSize=856;
    public static int outgoingMessageSize=856;
    public static int startOfPathValues=14;

    //State Evaluation
    public static final int maxXValue =35000;
    public static final int maxYValue =35000;

    //Vehicle Speed Limit
    public static final short vehicleSpeedLimit = 10; //km/s

    //Initialization
    public static String initializationTitle ="Automated Driving System Initialized";
    public static String initializationSubTitle ="SUBTITLE_00";
    public static String initializationAlertMessage ="MESSAGE_00";
    //State 01
    public static String state01AlertTitle ="Vehicle is Within Parking Area";
    public static String state01AlertSubTitle="Searching for Available Parking Slots";
    public static String state01AlertMessage="Please wait";
    //State 02
    public static String state02AlertTitle ="Vehicle is within Parking Area";
    public static String state02AlertSubTitle="Available Slots Found";
    public static String state02AlertMessage="Make a selection";
    //State 03
    public static String state03AlertTitle ="Automated Parking Will Start";
    public static String state03AlertSubTitle="Release the Brake Pedal";
    public static String state03AlertMessage="";
    //State 04
    public static String state04AlertTitle ="Automated Parking Will Start";
    public static String state04AlertSubTitle="Planned Path";
    public static String state04AlertMessage="MESSAGE_02";
    //State 05
    public static String state05AlertTitle ="Automated Parking Started";
    public static String state05AlertSubTitle="Please Watch Around for Safety";
    public static String state05AlertMessage="MESSAGE_02";
    //State 06
    public static String state06AlertTitle ="Vehicle is within Park";
    public static String state06AlertSubTitle="Stopping...";
    public static String state06AlertMessage="MESSAGE_02";
    //State 07
    public static String state07AlertTitle ="Vehicle is within Park";
    public static String state07AlertSubTitle="You May Apply Handbrake";
    public static String state07AlertMessage="MESSAGE_02";
    //State 08
    public static String state08AlertTitle ="Autonomous Parking Completed";
    public static String state08AlertSubTitle="";
    public static String state08AlertMessage="MESSAGE_02";
    //State 09
    public static String state09AlertTitle ="Automated Parking Will Start";
    public static String state09AlertSubTitle="Cannot Plan Path, Please Relocate the Vehicle";
    public static String state09AlertMessage="MESSAGE_02";
    //State 10
    public static String state10AlertTitle ="Vehicle is out of Parking Area";
    public static String state10AlertSubTitle="Please Relocate the Vehicle";
    public static String state10AlertMessage="MESSAGE_02";
    //State 11
    public static String state11AlertTitle ="Vehicle Status is Not OK";
    public static String state11AlertSubTitle="Please Check the Sensor and System Status";
    public static String state11AlertMessage="MESSAGE_02";
    //State 12
    public static String state12AlertTitle ="AD Interrupt";
    public static String state12AlertSubTitle="Steering Wheel Interrupt by the Driver";
    public static String state12AlertMessage="MESSAGE_02";

    //State 13
    public static String state13AlertTitle ="AD Interrupt";
    public static String state13AlertSubTitle="Brake Pedal Interrupt by the Driver";
    public static String state13AlertMessage="MESSAGE_02";

    //State 14
    public static String state14AlertTitle ="AD Interrupt";
    public static String state14AlertSubTitle="Throttle Pedal Interrupt by the Driver";
    public static String state14AlertMessage="MESSAGE_02";

    //State 15
    public static String state15AlertTitle ="Vehicle Speed is Higher Than Expected";
    public static String state15AlertSubTitle="Please Check the Sensor and System Status";
    public static String state15AlertMessage="MESSAGE_02";



}
