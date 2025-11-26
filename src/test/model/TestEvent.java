package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code from:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

/**
 * Unit tests for the Event class
 */
public class TestEvent {
    private Event testEvent;
    private Date testDate;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        testEvent = new Event("Sensor open at door"); // (1)
        testDate = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Sensor open at door", testEvent.getDescription());
        assertEquals(testDate, testEvent.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(testDate.toString() + "\n" + "Sensor open at door", testEvent.toString());
    }

    
}
