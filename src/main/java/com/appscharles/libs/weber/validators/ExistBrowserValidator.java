package com.appscharles.libs.weber.validators;

import java.io.File;

/**
 * The type Exist browser validator.
 */
public class ExistBrowserValidator {

    public static Boolean exist(File browsersDir, String name, String version){
        File browserDir = new File(browsersDir, version + "/" + name);
        return new File(browserDir, "MyChrome.exe").exists();
    }
}
