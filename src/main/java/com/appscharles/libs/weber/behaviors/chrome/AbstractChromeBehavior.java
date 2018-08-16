package com.appscharles.libs.weber.behaviors.chrome;

import io.webfolder.cdp.session.Session;

/**
 * The type Abstract behavior.
 */
public abstract class AbstractChromeBehavior implements IChromeBehavior {

    protected Session session;

    /**
     * Instantiates a new Abstract behavior.
     *
     * @param session the session
     */
    public AbstractChromeBehavior(Session session) {
        this.session = session;
    }
}
