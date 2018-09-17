package com.appscharles.libs.weber.behaviors.chrome;

import io.webfolder.cdp.session.Session;
import io.webfolder.cdp.session.WaitUntil;

/**
 * The type Navigate behavior.
 */
public class NavigateBehavior extends AbstractChromeBehavior {

    private String url;

    private Integer timeout;

    /**
     * Instantiates a new Navigate behavior.
     *
     * @param url     the url
     * @param session the session
     */
    public NavigateBehavior(String url, Session session) {
        super(session);
        this.timeout = 120 * 1000;
        this.url = url;
    }

    /**
     * Apply.
     */
    public void apply() {
        this.session.navigateAndWait(this.url, WaitUntil.DomReady, this.timeout);
    }
}
