package com.appscharles.libs.weber.services;

import com.appscharles.libs.weber.builders.WeberBuilder;
import com.appscharles.libs.weber.exceptions.WeberException;
import io.webfolder.cdp.session.Session;
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
public class WeberTest {

    @Test
    public void shouldOpenBrowser() throws MalformedURLException, WeberException, InterruptedException {
        Weber weber = WeberBuilder.create("myApp", "68.0", new File("E:\\others\\browsers_test"), new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip")).build();
        Session tab = weber.createSession();
        tab.navigate("https://news.ycombinator.com");
        tab.waitDocumentReady();
        Session tab2 = weber.createSession();
        tab2.navigate("https://news.ycombinator.com");
        tab2.waitDocumentReady();
        tab2.close();
        Thread.sleep(4000);
        weber.close();
    }
}