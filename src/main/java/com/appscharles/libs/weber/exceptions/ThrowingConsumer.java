package com.appscharles.libs.weber.exceptions;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 14.07.2018
 * Time: 11:38
 * Project name: databaser
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
@FunctionalInterface
public interface ThrowingConsumer<E extends WeberException> {
    void accept() throws E, WeberException;
}
