package com.appscharles.libs.weber.services;

import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.weber.WebViewTest;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.extractors.By;
import javafx.scene.web.WebView;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLInputElement;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.08.2018
 * Time: 11:57
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class WebTest {

    @Test
    public void shouldOpenFxBrowser() throws WeberException, InterruptedException, FxerException {
        WebViewTest webViewTest = new WebViewTest();
        webViewTest.launch();
        WebView webView = webViewTest.getWebView();
        Web web = new Web(webView);
        web.navigate("https://allegro.pl.allegrosandbox.pl/myaccount");
        HTMLInputElement element = (HTMLInputElement) web.getElement(By.id("username"));
        element.setValue("example");
        element.setAttribute("placeholder", "example");
        element.setDisabled(true);
        web.withDocument(document -> {
            NodeList nodeList = document.getElementsByTagName("label");
            for (int i = 0; i < nodeList.getLength() ; i++){
                Element label = (Element) nodeList.item(0);
                if (label.hasAttribute("for") && label.getAttribute("for").equals("username")){
                    Element span = (Element) label.getElementsByTagName("span").item(0);
                    span.setTextContent("example");
                }
            }
        });
        Assert.assertNotNull(element);
        Thread.sleep(5000);
    }

}