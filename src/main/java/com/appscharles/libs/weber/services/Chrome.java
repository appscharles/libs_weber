package com.appscharles.libs.weber.services;

import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.finders.AvailablePortFinder;
import com.appscharles.libs.weber.managers.TabsManager;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.SessionFactory;

import java.io.File;

/**
 * The type Chrome.
 */
public class Chrome {

    private SessionFactory sessionFactory;

    private Launcher launcher;

    private File browserDir;

    private TabsManager tabsManager;


    /**
     * Instantiates a new Chrome.
     *
     * @param sessionFactory the session factory
     * @param launcher       the launcher
     * @param browserDir     the browser dir
     */
    public Chrome(SessionFactory sessionFactory, Launcher launcher, File browserDir) {
        this.sessionFactory = sessionFactory;
        this.launcher = launcher;
        this.browserDir = browserDir;
        this.tabsManager = new TabsManager(this);

    }

    /**
     * Getter for property 'tabsManager'.
     *
     * @return Value for property 'tabsManager'.
     */
    public TabsManager tabs() {
        return this.tabsManager;
    }

    /**
     * Close.
     *
     * @throws WeberException the weber exception
     */
    public void close() throws WeberException {
        this.sessionFactory.close();
        new WeberKillService().kill(this.browserDir);
    }

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Sets session factory.
     *
     * @param sessionFactory the session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets launcher.
     *
     * @return the launcher
     */
    public Launcher getLauncher() {
        return launcher;
    }

    /**
     * Sets launcher.
     *
     * @param launcher the launcher
     */
    public void setLauncher(Launcher launcher) {
        this.launcher = launcher;
    }

    /**
     * Gets browser dir.
     *
     * @return the browser dir
     */
    public File getBrowserDir() {
        return browserDir;
    }

    /**
     * Restart.
     *
     * @throws WeberException the weber exception
     */
    public void restart() throws WeberException {
        this.close();
        this.setLauncher(new Launcher(AvailablePortFinder.findBetween(10000, 15000)));
        this.setSessionFactory(this.getLauncher().launch(new File(this.getBrowserDir(), "MyChrome.exe").toPath()));
    }
}
