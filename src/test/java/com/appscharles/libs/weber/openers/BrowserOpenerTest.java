package com.appscharles.libs.weber.openers;

import com.appscharles.libs.weber.exceptions.WeberException;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.08.2018
 * Time: 16:13
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class BrowserOpenerTest {

    @Test
    public void shouldRunChrome() throws MalformedURLException, WeberException {
        IBrowserOpener opener = new BrowserOpener(12112,
                new File("E:\\browsers"), "myApp",
                "68.0", new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip"));
        opener.open();
    }
}