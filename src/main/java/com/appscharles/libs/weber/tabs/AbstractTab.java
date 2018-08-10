package com.appscharles.libs.weber.tabs;

import io.webfolder.cdp.session.Session;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.08.2018
 * Time: 12:56
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public abstract class AbstractTab implements ISessionable, INavigatable {


    protected Session session;

    /**
     * Instantiates a new Tab.
     *
     * @param session the session
     */
    public AbstractTab(Session session) {
        this.session = session;
    }

    /**
     * Getter for property 'session'.
     *
     * @return Value for property 'session'.
     */
    public Session getSession() {
        return session;
    }

    @Override
    public void navigate(String url) {
        getSession().navigate(url);
        getSession().waitDocumentReady();
    }
}
