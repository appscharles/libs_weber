package com.appscharles.libs.weber.services;

import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.tabs.Tab;
import io.webfolder.cdp.Launcher;
import io.webfolder.cdp.session.SessionFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Chrome.
 */
public class Chrome {

    private SessionFactory sessionFactory;

    private Launcher launcher;

    private File browserDir;

    private Map<String, Tab> tabs;

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
        this.tabs = new HashMap<>();
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
     * Get tab tab.
     *
     * @param id the id
     * @return the tab
     */
    public Tab getTab(String id){
        synchronized (Chrome.class){
            if (this.tabs.containsKey(id)){
                Tab tab = this.tabs.get(id);
                if (tab.getSession().isConnected() == false){
                    return newTab(id);
                }
                return this.tabs.get(id);
            }
            return newTab(id);
        }
    }

    /**
     * Close tab.
     *
     * @param id the id
     */
    public void closeTab(String id){
        synchronized (Chrome.class){
            if (this.tabs.containsKey(id)){
                Tab tab = this.tabs.get(id);
                if (tab.getSession().isConnected() == false){
                    tab.getSession().close();
                }
            }
        }
    }

    /**
     * Close all tabs.
     */
    public void closeAllTabs(){
        synchronized (Chrome.class){
            for (Map.Entry<String, Tab> tabEntry : this.tabs.entrySet()) {
                if (tabEntry.getValue().getSession().isConnected() == false){
                    tabEntry.getValue().getSession().close();
                }
            }
        }
    }

    private Tab newTab(String id){
        Tab tab =  new Tab(this.sessionFactory.create());
        this.tabs.put(id, tab);
        return tab;
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
     * Gets launcher.
     *
     * @return the launcher
     */
    public Launcher getLauncher() {
        return launcher;
    }

    /**
     * Gets browser dir.
     *
     * @return the browser dir
     */
    public File getBrowserDir() {
        return browserDir;
    }
}
