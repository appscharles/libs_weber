package com.appscharles.libs.weber.validators;

import java.io.File;

/**
 * The type Downloaded browser validator.
 */
public class DownloadedBrowserValidator {

    /**
     * Exist boolean.
     *
     * @param fileName    the file name
     * @param downloadDir the download dir
     * @return the boolean
     */
    public static boolean exist(File downloadDir, String fileName) {
        return new File(downloadDir, fileName).exists();
    }
}
