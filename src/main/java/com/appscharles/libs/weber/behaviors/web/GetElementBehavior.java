package com.appscharles.libs.weber.behaviors.web;

import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.extractors.IElementsExtractor;
import javafx.scene.web.WebView;
import org.w3c.dom.Node;

import java.util.List;

/**
 * The type Get element behavior.
 */
public class GetElementBehavior extends AbstractWebBehavior {


    private final IElementsExtractor elementsExtractor;

    private Node result;

    /**
     * Instantiates a new Get element behavior.
     *
     * @param webView           the web view
     * @param elementsExtractor the elements extractor
     */
    public GetElementBehavior(WebView webView, IElementsExtractor elementsExtractor) {
        super(webView);
        this.elementsExtractor = elementsExtractor;
        this.elementsExtractor.setWebView(this.webView);
    }

    public void apply() throws WeberException {
        List<Node> nodes = this.elementsExtractor.getNodes();
        if (nodes.size() == 0){
            throw new WeberException("Not found nodes.");
        }
        this.result = nodes.get(0);
    }

    /**
     * Get result element.
     *
     * @return the element
     */
    public Node getResult(){
        return this.result;
    }
}
