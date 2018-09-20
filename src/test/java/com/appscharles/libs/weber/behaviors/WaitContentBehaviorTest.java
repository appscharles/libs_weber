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
public class WaitContentBehaviorTest extends TestCase {

    @Test
    public void shouldShowContent() throws WeberException, IOException {
       String route = "/WaitContentBehavior";
        WireMockRule httpServer = new WireMockRule(wireMockConfig().dynamicPort());
        addRoute(route, readTestResource("com/appscharles/libs/weber/behaviors/WaitContentBehavior.html"), httpServer);
        httpServer.start();
        Chrome chrome = ChromeBuilder.create("myApp", "68.0", new File(System.getProperty("java.io.tmpdir"), "browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).enableTest().build();
        Tab tab = chrome.tabs().getDefaultTab();
        tab.navigate("http://localhost:" + httpServer.port() + route);
        tab.waitContent(">contentNew<", 5000);
        chrome.close();
        httpServer.stop();
    }

    @Test(expected = WeberException.class)
    public void shouldExceptionThrow() throws WeberException, IOException, InterruptedException {
        String route = "/WaitContentBehavior";
        WireMockRule httpServer = new WireMockRule(wireMockConfig().dynamicPort());
        addRoute(route, readTestResource("com/appscharles/libs/weber/behaviors/WaitContentBehavior.html"), httpServer);
        httpServer.start();
        Chrome chrome = ChromeBuilder.create("myApp", "68.0", new File(System.getProperty("java.io.tmpdir"), "browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).enableTest().build();
        Tab tab = chrome.tabs().getDefaultTab();
        tab.navigate("http://localhost:" + httpServer.port() + route);
        tab.waitContent(">contentNew<", 1000);
        Thread.sleep(10000);
        chrome.close();
        httpServer.stop();
    }
}