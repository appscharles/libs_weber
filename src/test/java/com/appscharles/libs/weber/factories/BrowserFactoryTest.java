package com.appscharles.libs.weber.factories;

import com.appscharles.libs.weber.TestCase;
import com.appscharles.libs.weber.exceptions.WeberException;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.SessionFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.08.2018
 * Time: 15:41
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class BrowserFactoryTest extends TestCase {

    @Test
    public void shouldInstallBrowser() throws IOException, WeberException {
        File browsersDir = this.temp.newFolder("browsers_dir");
        File browserDir = BrowserFactory.create(
                new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0_test.zip"),
                browsersDir, "default", "68.0").build();
        Assert.assertTrue(new File(browserDir, "MyChrome.exe").exists());
    }

    @Test
    public void shouldOpenBrowser() throws IOException, WeberException {
        File browsersDir = new File("E:\\browsers");//this.temp.newFolder("browsers_dir");
        BrowserFactory.create(
                new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0.zip"),
                browsersDir, "default", "68.0").build();

        Launcher launcher = new Launcher(12112);
        try (SessionFactory factory = launcher.launch(new File("E:\\browsers\\68.0\\myApp", "MyChrome.exe").toPath());
             Session session = factory.create()) {
            session.navigate("https://news.ycombinator.com");
            session.waitDocumentReady();
        }
    }
}