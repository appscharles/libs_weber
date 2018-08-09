package com.appscharles.libs.weber.installers.business.configurators;

import java.io.File;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.08.2018
 * Time: 13:20
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class InstallerConfigurator {

    private Boolean test;

    private String stageStylesheetResource;

    private File browserDir;

    private URL browserURL;

    private File browserDownloadDir;

    public InstallerConfigurator(File browserDir, URL browserURL, File browserDownloadDir) {
        this.browserDir = browserDir;
        this.browserURL = browserURL;
        this.browserDownloadDir = browserDownloadDir;
        this.test = false;
    }

    /**
     * Getter for property 'test'.
     *
     * @return Value for property 'test'.
     */
    public Boolean getTest() {
        return test;
    }

    /**
     * Setter for property 'test'.
     *
     * @param test Value to set for property 'test'.
     */
    public void setTest(Boolean test) {
        this.test = test;
    }

    /**
     * Getter for property 'stageStylesheetResource'.
     *
     * @return Value for property 'stageStylesheetResource'.
     */
    public String getStageStylesheetResource() {
        return stageStylesheetResource;
    }

    /**
     * Setter for property 'stageStylesheetResource'.
     *
     * @param stageStylesheetResource Value to set for property 'stageStylesheetResource'.
     */
    public void setStageStylesheetResource(String stageStylesheetResource) {
        this.stageStylesheetResource = stageStylesheetResource;
    }

    /**
     * Getter for property 'browserDir'.
     *
     * @return Value for property 'browserDir'.
     */
    public File getBrowserDir() {
        return browserDir;
    }

    /**
     * Setter for property 'browserDir'.
     *
     * @param browserDir Value to set for property 'browserDir'.
     */
    public void setBrowserDir(File browserDir) {
        this.browserDir = browserDir;
    }

    /**
     * Getter for property 'browserURL'.
     *
     * @return Value for property 'browserURL'.
     */
    public URL getBrowserURL() {
        return browserURL;
    }

    /**
     * Setter for property 'browserURL'.
     *
     * @param browserURL Value to set for property 'browserURL'.
     */
    public void setBrowserURL(URL browserURL) {
        this.browserURL = browserURL;
    }

    /**
     * Getter for property 'browserDownloadDir'.
     *
     * @return Value for property 'browserDownloadDir'.
     */
    public File getBrowserDownloadDir() {
        return browserDownloadDir;
    }

    /**
     * Setter for property 'browserDownloadDir'.
     *
     * @param browserDownloadDir Value to set for property 'browserDownloadDir'.
     */
    public void setBrowserDownloadDir(File browserDownloadDir) {
        this.browserDownloadDir = browserDownloadDir;
    }
}
