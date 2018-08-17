package com.appscharles.libs.weber.tabs;

import com.appscharles.libs.fxer.exceptions.ThrowingConsumer;
import com.appscharles.libs.weber.exceptions.WeberException;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 10.08.2018
 * Time: 12:57
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public interface IWithWaitReloadable {

    void withWaitReload(ThrowingConsumer<WeberException> consumer, long timeout) throws WeberException;
}
