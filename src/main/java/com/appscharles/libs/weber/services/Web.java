package com.appscharles.libs.weber.services;

import com.appscharles.libs.weber.behaviors.web.GetElementBehavior;
import com.appscharles.libs.weber.behaviors.web.NavigateBehavior;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.extractors.IElementsExtractor;
import javafx.scene.web.WebView;
import org.w3c.dom.Node;

/**
 * The type Web.
 */
public class Web {

    private WebView webView;

    /**
     * Instantiates a new Web.
     *
     * @param webView the web view
     */
    public Web(WebView webView) {
        this.webView = webView;
    }

    /**
     * Navigate.
     *
     * @param url the url
     * @throws WeberException the weber exception
     */
    public void navigate(String url) throws WeberException {
        new NavigateBehavior(url, 90000,  this.webView).apply();
    }

    /**
     * Navigate.
     *
     * @param url     the url
     * @param timeout the timeout
     * @throws WeberException the weber exception
     */
    public void navigate(String url, long timeout) throws WeberException {
        new NavigateBehavior(url, timeout,  this.webView).apply();
    }

    /**
     * Getter for property 'webView'.
     *
     * @return Value for property 'webView'.
     */
    public WebView getWebView() {
        return webView;
    }

    public Node getElement(IElementsExtractor elementsExtractor) throws WeberException {
        GetElementBehavior behavior = new GetElementBehavior(this.webView, elementsExtractor);
        behavior.apply();
        return behavior.getResult();
    }
}
