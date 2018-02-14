package com.epam.brest.course;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.*;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    //changing the output stream
    @Before
    public void changeOutStream() {
        PrintStream printStream = new PrintStream(outContent);
        System.setOut(printStream);
    }

    //returning the output stream
    @After
    public void returnOutStream() { //
        System.setOut(System.out);
    }

    //a simple test with streams
    @Test
    public void main() {
        App.main(null);
        assertEquals("Hello world", outContent.toString());
    }
}