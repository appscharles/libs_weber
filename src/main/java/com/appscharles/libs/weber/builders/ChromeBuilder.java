package com.appscharles.libs.weber.builders;

import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.factories.BrowserFactory;
import com.appscharles.libs.weber.finders.AvailablePortFinder;
import com.appscharles.libs.weber.services.Chrome;
import com.appscharles.libs.weber.services.WeberKillService;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.SessionFactory;

import java.io.File;
import java.net.URL;

/**
 * The type Session factory builder.
 */
public class ChromeBuilder {

    private String name;

    private String version;

    private File browsersDir;

    private URL browserURL;

    private Boolean enableBrowserReplaceExisting;

    private Boolean test;

    private ChromeBuilder() {
        this.enableBrowserReplaceExisting = false;
        this.test = false;
    }

    /**
     * Create session factory builder.
     *
     * @param name        the name
     * @param version     the version
     * @param browsersDir the browsers dir
     * @param browserURL  the browser url
     * @return the session factory builder
     */
    public static ChromeBuilder create(String name, String version, File browsersDir, URL browserURL) {
        ChromeBuilder instance = new ChromeBuilder();
        instance.name = name;
        instance.version = version;
        instance.browsersDir = browsersDir;
        instance.browserURL = browserURL;
        return instance;
    }

    /**
     * Build session factory.
     *
     * @return the session factory
     * @throws WeberException the weber exception
     */
    public Chrome build() throws WeberException {
        File browserDir = new File(this.browsersDir, this.version + "/" + this.name);
        new WeberKillService().kill(browserDir);
        BrowserFactory browserFactory = BrowserFactory.create(this.browserURL, this.browsersDir, this.name, this.version);
        if (this.test){
            browserFactory.enableTest();
        }
        if (this.enableBrowserReplaceExisting) {
            browserFactory.enableReplaceExisting();
        }
        browserFactory.build();
        Launcher launcher = new Launcher(AvailablePortFinder.findBetween(10000, 15000));
         SessionFactory sessionFactory = launcher.launch(new File(browserDir, "MyChrome.exe").toPath());
        return new Chrome(sessionFactory, launcher, browserDir);
    }

    /**
     * Enable browser replace existing session factory builder.
     *
     * @return the session factory builder
     */
    public ChromeBuilder enableBrowserReplaceExisting() {
        this.enableBrowserReplaceExisting = true;
        return this;
    }

    public ChromeBuilder enableTest() {
        this.test = true;
        return this;
    }
}
