package com.appscharles.libs.weber.tabs;

import com.appscharles.libs.fxer.exceptions.ThrowingConsumer;
import com.appscharles.libs.weber.behaviors.chrome.InputFillBehavior;
import com.appscharles.libs.weber.behaviors.chrome.NavigateBehavior;
import com.appscharles.libs.weber.behaviors.chrome.WaitContentBehavior;
import com.appscharles.libs.weber.behaviors.chrome.WithWaitReloadBehavior;
import com.appscharles.libs.weber.exceptions.WeberException;
import io.webfolder.cdp.session.Session;

/**
 * The type Abstract tab.
 */
public abstract class AbstractTab implements ISessionable, INavigatable, IInputFillable, IWithWaitReloadable, IContentWaitable {


    /**
     * The Session.
     */
    protected Session session;

    /**
     * Instantiates a new Abstract tab.
     *
     * @param session the session
     */
    public AbstractTab(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void navigate(String url) {
        new NavigateBehavior(url, getSession()).apply();
    }

    @Override
    public void inputFill(String value, String selector) throws WeberException {
        new InputFillBehavior(value, selector, getSession()).apply();
    }

    @Override
    public void withWaitReload(ThrowingConsumer<WeberException> consumer, long timeout) throws WeberException {
        new WithWaitReloadBehavior(consumer, timeout, getSession()).apply();
    }

    @Override
    public void waitContent(String content, long timeout) throws WeberException {
        new WaitContentBehavior(content, timeout, getSession()).apply();
    }
}
