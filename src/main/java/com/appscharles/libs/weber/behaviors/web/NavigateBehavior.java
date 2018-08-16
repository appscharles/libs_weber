package com.appscharles.libs.weber.behaviors.web;

import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.runners.PlatformRunner;
import com.appscharles.libs.weber.exceptions.WeberException;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Navigate behavior.
 */
public class NavigateBehavior extends AbstractWebBehavior {

    private String url;

    private long timeout;

    /**
     * Instantiates a new Navigate behavior.
     *
     * @param url     the url
     * @param webView the web view
     */
    public NavigateBehavior(String url, long timeout, WebView webView) {
        super(webView);
        this.url = url;
        this.timeout = timeout;
    }

    public void apply() throws WeberException {
        try {
            BooleanProperty loaded = new SimpleBooleanProperty(false);
            ExecutorService service = Executors.newSingleThreadExecutor();
            ObjectProperty<Exception> error = new SimpleObjectProperty<>();
            service.submit(() -> {
                try {
                    PlatformRunner.runAndWait(()->{
                        try {
                            WebEngine webEngine = this.webView.getEngine();
                            ChangeListener<Worker.State> changeListener = (args, oldVal, newVal) -> {
                                if (newVal == Worker.State.SUCCEEDED) {
                                    loaded.setValue(true);
                                }
                            };
                            webEngine.getLoadWorker().stateProperty().addListener(changeListener);
                            this.webView.getEngine().load(this.url);
                        } catch (Exception e) {
                            error.setValue(e);
                        }
                    });
                } catch (FxerException e) {
                    error.setValue(e);
                }
            });
            service.shutdown();
            long timeout = System.currentTimeMillis() + this.timeout;
            while (System.currentTimeMillis() < timeout) {
                if (loaded.getValue()) {
                    if (error.get() != null) {
                        throw new WeberException(error.get());
                    }
                    return;
                }
                Thread.sleep(100);
            }
            throw new FxerException("Timeout navigate url.");
        } catch (FxerException | InterruptedException e) {
            throw new WeberException(e);
        }
    }
}
