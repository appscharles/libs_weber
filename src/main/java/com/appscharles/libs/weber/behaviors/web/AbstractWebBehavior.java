package com.appscharles.libs.weber.behaviors.web;

import javafx.scene.web.WebView;

/**
 * The type Abstract web behavior.
 */
public abstract class AbstractWebBehavior implements IWebBehavior {

    /**
     * The Web view.
     */
    protected WebView webView;

    /**
     * Instantiates a new Abstract web behavior.
     *
     * @param webView the web view
     */
    public AbstractWebBehavior(WebView webView) {
        this.webView = webView;
    }
}
