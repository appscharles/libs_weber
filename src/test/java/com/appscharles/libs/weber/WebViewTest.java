package com.appscharles.libs.weber;

import com.sun.javafx.application.PlatformImpl;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 16.08.2018
 * Time: 11:59
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class WebViewTest{

    private WebView webView;

    public WebViewTest() {
        PlatformImpl.startup(()->{});
        PlatformImpl.setImplicitExit(false);
    }

    public void launch() {
       PlatformImpl.runAndWait(()->{
           Stage stage = new Stage();
           stage.setTitle("Web View Test");
           VBox root = new VBox();
           this.webView = new WebView();
           root.getChildren().add(this.webView);
           Scene scene = new Scene(root,900,600);
           stage.setScene(scene);
           stage.show();
       });
    }


    public WebView getWebView(){
        return this.webView;
    }
}
