package com.appscharles.libs.weber.behaviors;

import com.appscharles.libs.weber.TestCase;
import com.appscharles.libs.weber.builders.ChromeBuilder;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.services.Chrome;
import com.appscharles.libs.weber.tabs.Tab;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.08.2018
 * Time: 13:27
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class InputFillBehaviorTest extends TestCase {

    @Test
    public void shouldFillInput() throws WeberException, IOException {
        WireMockRule httpServer = new WireMockRule(wireMockConfig().dynamicPort());
        addRoute("/InputFillBehavior", readTestResource("com/appscharles/libs/weber/behaviors/InputFillBehavior.html"), httpServer);
        httpServer.start();
        Chrome chrome = ChromeBuilder.create("myApp", "68.0", new File(System.getProperty("java.io.tmpdir"), "browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).enableTest().build();
        Tab tab = chrome.getTab("id");
        tab.navigate("http://localhost:" + httpServer.port() + "/InputFillBehavior");
        tab.inputFill("content", "//input[@name='lname']");
        chrome.close();
        httpServer.stop();
    }
}