package com.appscharles.libs.weber.services;

import com.appscharles.libs.weber.builders.ChromeBuilder;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.tabs.Tab;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.08.2018
 * Time: 12:04
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ChromeTest {

    @Test
    public void shouldOpenBrowser() throws MalformedURLException, WeberException, InterruptedException {
        Chrome chrome = ChromeBuilder.create("myApp", "68.0", new File(System.getProperty("java.io.tmpdir"), "browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).enableTest().build();
        Tab tab = chrome.createTab();
        tab.getSession().navigate("https://news.ycombinator.com");
        tab.getSession().waitDocumentReady();
        chrome.close();
    }

    @Test
    public void shouldOpenBrowser2() throws MalformedURLException, WeberException, InterruptedException {
        Chrome chrome = ChromeBuilder.create("myApp", "68.0", new File(System.getProperty("java.io.tmpdir"), "browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).enableTest().build();
        Tab tab = chrome.createTab();
        tab.navigate("https://news.ycombinator.com");
        chrome.close();
    }
}