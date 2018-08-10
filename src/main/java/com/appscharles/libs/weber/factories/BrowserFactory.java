package com.appscharles.libs.weber.factories;

import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.installers.Installer;
import com.appscharles.libs.weber.installers.business.configurators.InstallerConfigurator;
import com.appscharles.libs.weber.validators.ExistBrowserValidator;

import java.io.File;
import java.net.URL;

/**
 * The type Browser factory.
 */
public class BrowserFactory {

    private URL browserURL;

    private File browsersDir;

    private String name;

    private String version;

    private Boolean replaceExisting;

    private Boolean test;

    private BrowserFactory() {
        this.replaceExisting = false;
        this.test = false;
    }

    /**
     * Create browser factory.
     *
     * @param browserURL  the browser url
     * @param browsersDir the browsers dir
     * @param name        the name
     * @param version     the version
     * @return the browser factory
     */
    public static BrowserFactory create(URL browserURL, File browsersDir, String name, String version) {
        BrowserFactory instance = new BrowserFactory();
        instance.browserURL = browserURL;
        instance.browsersDir = browsersDir;
        instance.name = name;
        instance.version = version;
        return instance;
    }

    /**
     * Build file.
     *
     * @return the file
     * @throws WeberException the weber exception
     */
    public File build() throws WeberException {
        File browserDir = new File(this.browsersDir, this.version + "/" + this.name);
        if (this.replaceExisting || false == ExistBrowserValidator.exist(this.browsersDir, this.name, this.version)) {
            File browserDownloadDir = new File(this.browsersDir, "download");
            InstallerConfigurator configurator = new InstallerConfigurator(browserDir, this.browserURL, browserDownloadDir);
            configurator.setTest(this.test);
            Installer.launch(configurator);
        }
        return browserDir;
    }

    /**
     * Enable replace existing browser factory.
     *
     * @return the browser factory
     */
    public BrowserFactory enableReplaceExisting() {
        this.replaceExisting = true;
        return this;
    }

    public BrowserFactory enableTest() {
        this.test = true;
        return this;
    }
}
