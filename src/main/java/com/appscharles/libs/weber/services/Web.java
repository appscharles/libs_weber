package com.appscharles.libs.weber.services;

import com.appscharles.libs.fxer.exceptions.ThrowingOneConsumer;
import com.appscharles.libs.fxer.runners.ThreadPlatform;
import com.appscharles.libs.weber.behaviors.web.GetElementBehavior;
import com.appscharles.libs.weber.behaviors.web.NavigateBehavior;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.extractors.IElementsExtractor;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
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
     * Gets web view.
     *
     * @return the web view
     */
    public WebView getWebView() {
        return webView;
    }

    /**
     * Gets element.
     *
     * @param elementsExtractor the elements extractor
     * @return the element
     * @throws WeberException the weber exception
     */
    public Node getElement(IElementsExtractor elementsExtractor) throws WeberException {
        GetElementBehavior behavior = new GetElementBehavior(this.webView, elementsExtractor);
        behavior.apply();
        return behavior.getResult();
    }

    /**
     * With document.
     *
     * @param consumer the consumer
     * @throws WeberException the weber exception
     */
    public void withDocument(ThrowingOneConsumer<Document, WeberException> consumer) throws WeberException {
        new ThreadPlatform<WeberException>().runAndWait(()->{
            consumer.accept(this.webView.getEngine().getDocument());
        });
    }
}
