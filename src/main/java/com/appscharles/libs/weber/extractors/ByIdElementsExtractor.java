package com.appscharles.libs.weber.extractors;

import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.runners.PlatformRunner;
import com.appscharles.libs.weber.exceptions.WeberException;
import javafx.scene.web.WebView;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.08.2018
 * Time: 15:39
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class ByIdElementsExtractor implements IElementsExtractor {

    private String id;

    private WebView webView;

    public ByIdElementsExtractor(String id) {
        this.id = id;
    }

    @Override
    public List<Node> getNodes() throws WeberException {
        List<Node> nodes = new ArrayList<>();
        try {
            PlatformRunner.runAndWait(()->{
                nodes.add(this.webView.getEngine().getDocument().getElementById(this.id));
            });
        } catch (FxerException e) {
            throw new WeberException(e);
        }
        return nodes;
    }

    @Override
    public void setWebView(WebView webView) {
        this.webView = webView;
    }
}
