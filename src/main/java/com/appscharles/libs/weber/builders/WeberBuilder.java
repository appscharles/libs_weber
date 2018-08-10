package com.appscharles.libs.weber.builders;

import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.factories.BrowserFactory;
import com.appscharles.libs.weber.finders.AvailablePortFinder;
import com.appscharles.libs.weber.services.Weber;
import com.appscharles.libs.weber.services.WeberKillService;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.SessionFactory;

import java.io.File;
import java.net.URL;

/**
 * The type Session factory builder.
 */
public class WeberBuilder {

    private String name;

    private String version;

    private File browsersDir;

    private URL browserURL;

    private Boolean enableBrowserReplaceExisting;

    private WeberBuilder() {
        this.enableBrowserReplaceExisting = false;
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
    public static WeberBuilder create(String name, String version, File browsersDir, URL browserURL) {
        WeberBuilder instance = new WeberBuilder();
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
    public Weber build() throws WeberException {
        File browserDir = new File(this.browsersDir, this.version + "/" + this.name);
        new WeberKillService().kill(browserDir);
        BrowserFactory browserFactory = BrowserFactory.create(this.browserURL, this.browsersDir, this.name, this.version);
        if (this.enableBrowserReplaceExisting) {
            browserFactory.enableReplaceExisting();
        }
        browserFactory.build();
        Integer port = AvailablePortFinder.findBetween(10000, 15000);
        Launcher launcher = new Launcher(port);
         SessionFactory sessionFactory = launcher.launch(new File(browserDir, "MyChrome.exe").toPath());
        return new Weber(port, sessionFactory, launcher, browserDir);
    }

    /**
     * Enable browser replace existing session factory builder.
     *
     * @return the session factory builder
     */
    public WeberBuilder enableBrowserReplaceExisting() {
        this.enableBrowserReplaceExisting = true;
        return this;
    }
}
