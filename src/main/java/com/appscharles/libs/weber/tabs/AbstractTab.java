package com.appscharles.libs.weber.tabs;

import com.appscharles.libs.weber.behaviors.InputFillBehavior;
import com.appscharles.libs.weber.behaviors.NavigateBehavior;
import com.appscharles.libs.weber.behaviors.WithWaitReloadBehavior;
import com.appscharles.libs.weber.exceptions.ThrowingConsumer;
import com.appscharles.libs.weber.exceptions.WeberException;
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
public abstract class AbstractTab implements ISessionable, INavigatable, IInputFillable, IWithWaitReloadable {


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
        new NavigateBehavior(url, getSession()).apply();
    }

    @Override
    public void inputFill(String value, String selector) throws WeberException {
        new InputFillBehavior(value, selector, getSession()).apply();
    }

    @Override
    public void withWaitReload(ThrowingConsumer consumer, long timeout) throws WeberException {
        new WithWaitReloadBehavior(consumer, timeout, getSession()).apply();
    }
}
