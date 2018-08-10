package com.appscharles.libs.weber.behaviors;

import io.webfolder.cdp.session.Session;

/**
 * The type Abstract behavior.
 */
public abstract class AbstractBehavior implements IBehavior {

    protected Session session;

    /**
     * Instantiates a new Abstract behavior.
     *
     * @param session the session
     */
    public AbstractBehavior(Session session) {
        this.session = session;
    }
}
