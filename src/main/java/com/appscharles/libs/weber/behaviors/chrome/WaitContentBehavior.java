package com.appscharles.libs.weber.behaviors.chrome;

import com.appscharles.libs.weber.exceptions.WeberException;
import io.webfolder.cdp.session.Session;

/**
 * The type Wait content behavior.
 */
public class WaitContentBehavior extends AbstractChromeBehavior {

    private String content;

    private long timeout;

    /**
     * Instantiates a new Wait content behavior.
     *
     * @param content the content
     * @param session the session
     */
    public WaitContentBehavior(String content, long timeout, Session session) {
        super(session);
        this.timeout = timeout;
        this.content = content;
    }

    public void apply() throws WeberException {
        long timeout = System.currentTimeMillis() + this.timeout;
        try {
            while (System.currentTimeMillis() < timeout) {
                if (this.session.getContent().contains(this.content)){
                    return;
                }
                Thread.sleep(200);
            }
            throw new WeberException("Timeout wait content " + this.content);
        } catch (InterruptedException e) {
            throw new WeberException(e);
        }
    }

    /**
     * Setter for property 'timeout'.
     *
     * @param timeout Value to set for property 'timeout'.
     */
    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
