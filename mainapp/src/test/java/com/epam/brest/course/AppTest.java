package com.epam.brest.course;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

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
    public void returnOutStream() {
        System.setOut(System.out);
    }

    //a simple test with streams
    @Test
    public void main() throws SQLException, ClassNotFoundException {
        App.main(null);
        assertEquals("Hello world", outContent.toString());
    }

}