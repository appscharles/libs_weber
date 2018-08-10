package com.appscharles.libs.weber.tabs;

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
public interface IInputFillable {

    void inputFill(String value, String selector) throws WeberException;
}
