package com.appscharles.libs.weber.services;

import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.processer.managers.WinKillManager;
import com.appscharles.libs.weber.exceptions.WeberException;

import java.io.File;

/**
 * The type Weber kill service.
 */
public class WeberKillService {

    /**
     * Kill.
     *
     * @param browserDir the browser dir
     * @throws WeberException the weber exception
     */
    public void kill(File browserDir) throws WeberException {
        try {
            new WinKillManager().killCommandLineContains(browserDir.getAbsolutePath().replace("\\", "\\\\"));
        } catch (ProcesserException e) {
            throw new WeberException(e);
        }

    }
}
