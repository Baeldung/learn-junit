package com.baeldung.lju;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LifecycleMethodsAndResourcesHandlingUnitTest {

    final static Logger logger = LoggerFactory.getLogger(LifecycleMethodsAndResourcesHandlingUnitTest.class);
    static BufferedReader fileReader;

    //    BufferedReader fileReader;

    @BeforeAll
    static void setupResource() throws Exception {
        InputStream fileStream = LifecycleMethodsAndResourcesHandlingUnitTest.class.getClassLoader()
            .getResourceAsStream("file.txt");
        fileReader = new BufferedReader(new InputStreamReader(fileStream));

        logger.info("static fileReader is ready: {}", fileReader.ready());
    }

    /* @BeforeEach
    void setupUsingResource() throws Exception {
        InputStream fileStream = LifecycleMethodsAndResourcesHandlingUnitTest.class.getClassLoader()
            .getResourceAsStream("file.txt");
        fileReader = new BufferedReader(new InputStreamReader(fileStream));
    
        logger.info("fileReader is ready: {}", fileReader.ready());
    } */

    @AfterEach
    void cleanupResource() throws Exception {
        //        fileReader.close();
        //        logger.info("fileReader is closed");
    }

    @AfterAll
    static void cleanupStaticResource() throws Exception {
        fileReader.close();
        logger.info("static fileReader is closed");
    }

    @Test
    void givenOpenResource_whenReadLines1_thenLineIsLogged() throws Exception {
        for (int i = 0; i < 2; i++) {
            logger.info(fileReader.readLine());
        }
    }

    @Test
    void givenOpenResource_whenReadLines2_thenLineIsLogged() throws Exception {
        for (int i = 0; i < 2; i++) {
            logger.info(fileReader.readLine());
        }
    }
}
