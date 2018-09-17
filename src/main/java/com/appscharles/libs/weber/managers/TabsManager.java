package com.appscharles.libs.weber.managers;

import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.services.Chrome;
import com.appscharles.libs.weber.tabs.Tab;
import io.webfolder.cdp.exception.CdpException;

import java.util.HashMap;
import java.util.Map;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 17.09.2018
 * Time: 17:26
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class TabsManager {


    private Map<String, Tab> tabs;

    private Chrome chrome;

    public TabsManager(Chrome chrome) {
        this.chrome = chrome;
        this.tabs = new HashMap<>();
    }

    public Tab getDefaultTab() throws WeberException {
        synchronized (Chrome.class){
            if (this.tabs.containsKey("default")){
                Tab tab = this.tabs.get("default");
                if (tab.getSession().isConnected() == false){
                    return newTab("default");
                }
                return this.tabs.get("default");
            }
            return newTab("default");
        }
    }

    /**
     * Get tab tab.
     *
     * @param id the id
     * @return the tab
     */
    public Tab getTab(String id) throws WeberException {
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

    public void closeDefaultTab(){
        synchronized (Chrome.class){
            if (this.tabs.containsKey("default")){
                Tab tab = this.tabs.get("default");
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

    private Tab newTab(String id) throws WeberException {
        try {
            Tab tab =  new Tab(this.chrome.getSessionFactory().create());
            this.tabs.put(id, tab);
            return tab;
        } catch (CdpException e) {
            if (e.getMessage().contains("WebSocket connection is not alive")){
                this.chrome.restart();
                return newTab(id);
            } else {
                throw new WeberException(e);
            }
        }
    }


}
