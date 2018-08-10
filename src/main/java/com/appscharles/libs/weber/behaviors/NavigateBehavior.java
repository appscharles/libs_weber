package com.appscharles.libs.weber.behaviors;

import io.webfolder.cdp.session.Session;

/**
 * The type Navigate behavior.
 */
public class NavigateBehavior extends AbstractBehavior {

    private String url;

    /**
     * Instantiates a new Navigate behavior.
     *
     * @param url     the url
     * @param session the session
     */
    public NavigateBehavior(String url, Session session) {
        super(session);
        this.url = url;
    }

    /**
     * Apply.
     */
    public void apply() {
        this.session.navigate(this.url);
        this.session.waitDocumentReady();
    }
}
