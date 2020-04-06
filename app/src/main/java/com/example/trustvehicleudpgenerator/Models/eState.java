package com.example.trustvehicleudpgenerator.Models;

public enum eState {
    INITIALIZATION, // Initialization State
    APPROACHING, // Approaching State
    AVAILABLE_SLOTS_FOUND, // Available Slots Found State
    AFTER_SELECT_SLOT, // After Select Slot State
    DRAW_PATH, // Draw Path Things State
    TRUCK_MOVING, // Truck Moving State
    TRUCK_WITHIN_PARK, // Truck Got Within Park
    FINAL, // Final

    // Error States

    ERROR_CANNOT_BE_PLANNED, // Cannot Be Planned
    ERROR_APPROACHING, // Approaching Error
    ERROR_STARTING_SCREEN, // Starting Screen Error


    // Interrupts
    INTERRUPT_STEERING_WHEEL, // Steering Wheel Interrupt
    INTERRUPT_BRAKE, // Brake Interrupt
    INTERRRUPT_THROTTLE, // Throttle Interrupt

    VEHICLE_OVERSPEED, // Vehicle Overspeed

}
