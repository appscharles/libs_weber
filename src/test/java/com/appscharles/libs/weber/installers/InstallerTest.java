package com.appscharles.libs.weber.installers;

import com.appscharles.libs.weber.TestCase;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.installers.business.configurators.InstallerConfigurator;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.08.2018
 * Time: 13:36
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class InstallerTest extends TestCase {

    @Test
    public void shouldRunInstaller() throws WeberException, IOException {
        File browserDir = this.temp.newFolder("browser_dir");
        File downloadDir = this.temp.newFolder("download_dir");
        URL browserURL = new URL("https://bitbucket.org/appscharles/resources_java/downloads/Chrome_68.0_test.zip");
        InstallerConfigurator configurator = new InstallerConfigurator(browserDir, browserURL, downloadDir);
        configurator.setTest(true);
        Installer.launch(configurator);
    }
}