package com.appscharles.libs.weber.behaviors;

import com.appscharles.libs.weber.exceptions.WeberException;
import io.webfolder.cdp.session.Session;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.08.2018
 * Time: 13:22
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class InputFillBehavior extends AbstractBehavior {

    private String value;

    private String selector;

    public InputFillBehavior(String value, String selector, Session session) {
        super(session);
        this.value = value;
        this.selector = selector;
    }

    /**
     * Apply.
     */
    public void apply() throws WeberException {
        this.session.setAttribute(this.selector, "value", this.value);
        String checkValue = this.session.getAttribute(this.selector, "value");
        if (checkValue.equals(this.value) == false){
            throw new WeberException(String.format("Value is not equal to value display in input text. {value='%1$s', checkValue='%1$s'}", this.value, checkValue));
        }
    }
}
