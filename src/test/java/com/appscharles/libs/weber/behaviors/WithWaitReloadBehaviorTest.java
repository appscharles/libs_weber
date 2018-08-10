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
 * Time: 15:14
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class WithWaitReloadBehaviorTest extends TestCase {

    @Test
    public void shouldWaitForReloadPage() throws IOException, WeberException, InterruptedException {
        WireMockRule httpServer = new WireMockRule(wireMockConfig().dynamicPort());
        addRoute("/WithWaitReloadBasicBehavior", readTestResource("com/appscharles/libs/weber/behaviors/WithWaitReloadBasicBehavior.html"), httpServer);
        addRoute("/WithWaitReloadSecondaryBehavior", readTestResource("com/appscharles/libs/weber/behaviors/WithWaitReloadSecondaryBehavior.html"), httpServer );
        httpServer.start();
        Chrome chrome = ChromeBuilder.create("myApp", "68.0", new File(System.getProperty("java.io.tmpdir"), "browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).enableTest().build();
        Tab tab = chrome.createTab();
        tab.navigate("http://localhost:" + httpServer.port() + "/WithWaitReloadBasicBehavior");
        tab.withWaitReload(()->{
            tab.getSession().click("//input");
        }, 60000);
        chrome.close();
        httpServer.stop();
    }

    @Test(expected = WeberException.class)
    public void shouldExceptionWithWaitForReloadPage() throws IOException, WeberException, InterruptedException {
        WireMockRule httpServer = new WireMockRule(wireMockConfig().dynamicPort());
        addRoute("/WithWaitReloadBasicBehavior", readTestResource("com/appscharles/libs/weber/behaviors/WithWaitReloadBasicBehavior.html"), httpServer);
        addRoute("/WithWaitReloadSecondaryBehavior", 4000, readTestResource("com/appscharles/libs/weber/behaviors/WithWaitReloadSecondaryBehavior.html"), httpServer );
        httpServer.start();
        Chrome chrome = ChromeBuilder.create("myApp", "68.0", new File(System.getProperty("java.io.tmpdir"), "browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).enableTest().build();
        Tab tab = chrome.createTab();
        tab.navigate("http://localhost:" + httpServer.port() + "/WithWaitReloadBasicBehavior");
        tab.withWaitReload(()->{
            tab.getSession().click("//input");
        }, 2000);
        tab.getSession().close();
        chrome.close();
        httpServer.stop();
    }
}