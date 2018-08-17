package com.appscharles.libs.weber.behaviors.chrome;

import com.appscharles.libs.fxer.exceptions.ThrowingConsumer;
import com.appscharles.libs.weber.exceptions.WeberException;
import io.webfolder.cdp.command.Network;
import io.webfolder.cdp.event.network.LoadingFinished;
import io.webfolder.cdp.session.Session;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

import static io.webfolder.cdp.event.Events.NetworkLoadingFinished;
import static io.webfolder.cdp.event.Events.NetworkRequestWillBeSent;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.08.2018
 * Time: 13:22
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class WithWaitReloadBehavior extends AbstractChromeBehavior {

    private final ThrowingConsumer<WeberException> consumer;

    private final long timeout;

    public WithWaitReloadBehavior(ThrowingConsumer<WeberException> consumer, long timeout, Session session) {
        super(session);
        this.consumer = consumer;
        this.timeout = timeout;
    }

    /**
     * Apply.
     */
    public void apply() throws WeberException {
        final String basicLoaderId = this.session.getCommand().getPage().getFrameTree().getFrame().getLoaderId();
        Network network = this.session.getCommand().getNetwork();
        network.enable();
        ObjectProperty<String> secondaryLoaderId = new SimpleObjectProperty<>();
        BooleanProperty requestSent = new SimpleBooleanProperty(false);
        this.session.addEventListener((e, d) -> {
            if (NetworkRequestWillBeSent.equals(e)) {
                requestSent.setValue(true);
            } else if (NetworkLoadingFinished.equals(e)) {
                if (requestSent.getValue()){
                    secondaryLoaderId.setValue(((LoadingFinished) d).getRequestId());
                }
            }
        });
        this.consumer.accept();
        Long timeout = System.currentTimeMillis() + this.timeout;
        while (System.currentTimeMillis() < timeout) {
            if (secondaryLoaderId.get() != null && secondaryLoaderId.get().equals(basicLoaderId) == false){
                return;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
               throw new WeberException(e);
            }
        }
        throw new WeberException("Timeout reload after apply consumer.");
    }
}
