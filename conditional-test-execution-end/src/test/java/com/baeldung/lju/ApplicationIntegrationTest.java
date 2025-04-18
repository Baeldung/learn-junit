package com.baeldung.lju;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@EnabledOnJre(JRE.JAVA_21)
class ApplicationIntegrationTest {

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void mainAppMethodIntegrationTestOnWindows() {
        LjuApp.main(new String[] { "OS_WINDOWS_TEST" });
    }

    @Test
    @EnabledOnOs(value = OS.LINUX, architectures = "x86_64")
    void mainAppMethodIntegrationTestOnLinux() {
        LjuApp.main(new String[] { "OS_LINUX_TEST" });
    }

}