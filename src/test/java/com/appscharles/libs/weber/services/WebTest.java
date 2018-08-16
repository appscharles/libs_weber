package com.appscharles.libs.weber.services;

import com.appscharles.libs.weber.WebViewTest;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.extractors.By;
import javafx.scene.web.WebView;
import org.junit.Assert;
import org.junit.Test;
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
    public void shouldOpenFxBrowser() throws WeberException, InterruptedException {
        WebViewTest webViewTest = new WebViewTest();
        webViewTest.launch();
        WebView webView = webViewTest.getWebView();
        Web web = new Web(webView);
        web.navigate("https://allegro.pl.allegrosandbox.pl/myaccount");
        System.out.println("next");
        HTMLInputElement element = (HTMLInputElement) web.getElement(By.id("username"));

        System.out.println("next");
        Assert.assertNotNull(element);
        Thread.sleep(2000);
    }

}