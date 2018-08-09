package com.appscharles.libs.weber.openers;

import com.appscharles.libs.processer.callers.CommanderCaller;
import com.appscharles.libs.processer.callers.CommanderResult;
import com.appscharles.libs.processer.callers.ICommanderCaller;
import com.appscharles.libs.processer.exceptions.ProcesserException;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.factories.BrowserFactory;
import com.appscharles.libs.weber.validators.ExistBrowserValidator;

import java.io.File;
import java.net.URL;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 09.08.2018
 * Time: 15:57
 * Project name: weber
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class BrowserOpener implements IBrowserOpener {

    private Integer remotePort;

    private File browsersDir;

    private String name;

    private String version;

    private URL browserURL;

    public BrowserOpener(Integer remotePort, File browsersDir, String name, String version, URL browserURL) {
        this.remotePort = remotePort;
        this.browsersDir = browsersDir;
        this.name = name;
        this.version = version;
        this.browserURL = browserURL;
    }

    @Override
    public void open() throws WeberException {
        try {
            File browserDir = new File(this.browsersDir, this.version + "/" + this.name);
            if (false == ExistBrowserValidator.exist(this.browsersDir, this.name, this.version)){
                BrowserFactory.create(this.browserURL, browsersDir, this.name, this.version).build();
            }
            File chromeExecutable = new File(browserDir, "MyChrome.exe");
            ICommanderCaller caller = new CommanderCaller();
            CommanderResult result = caller.call(chromeExecutable.getAbsolutePath() + " --remote-debugging-port=" + this.remotePort);
            if (result.isError()){
                throw new WeberException(result.getOutput());
            }
        } catch (ProcesserException e) {
            throw new WeberException(e);
        }
    }
}
