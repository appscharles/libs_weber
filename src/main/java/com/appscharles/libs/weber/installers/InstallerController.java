package com.appscharles.libs.weber.installers;

import com.appscharles.libs.dialoger.factories.ExceptionDialogFactory;
import com.appscharles.libs.fxer.controllers.AbstractStageControllerFX;
import com.appscharles.libs.ioer.downloaders.file.HttpFileDownloader;
import com.appscharles.libs.ioer.downloaders.file.IFileDownloader;
import com.appscharles.libs.ioer.downloaders.file.IStatusProgressable;
import com.appscharles.libs.ioer.exceptions.IoerException;
import com.appscharles.libs.ioer.models.StatusProgress;
import com.appscharles.libs.ioer.services.ZipUnziper;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.installers.business.configurators.InstallerConfigurator;
import com.appscharles.libs.weber.validators.DownloadedBrowserValidator;
import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * IDE Editor: IntelliJ IDEA
 * <p>
 * Date: 03.07.2018
 * Time: 15:53
 * Project name: updater
 *
 * @author Karol Golec karol.itgolo@gmail.com
 */
public class InstallerController extends AbstractStageControllerFX {

    private static final Logger logger = LogManager.getLogger(InstallerController.class);

    private ResourceBundle resourceBundle;

    @FXML
    private Label status;

    @FXML
    private ProgressBar progressBar;

    private InstallerConfigurator installerConfigurator;

    private Boolean completed;

    private WeberException exception;

    private StatusProgress statusProgress;

    public InstallerController(InstallerConfigurator installerConfigurator) {
        this.installerConfigurator = installerConfigurator;
        this.completed = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
        Platform.runLater(() -> {
            this.fXStage.setTitle(this.resourceBundle.getString("stage.title"));
        });
        this.fXStage.setOnHiding((event) -> {
            if (this.completed == false && this.exception == null) {
                this.exception = new WeberException("The browser installation was manually interrupted.");
            }
        });
        this.statusProgress = new StatusProgress();
        this.progressBar.progressProperty().bind(this.statusProgress.progressProperty());
        if (this.installerConfigurator.getBrowserDir().exists() == false){
            this.installerConfigurator.getBrowserDir().mkdirs();
        }
        if (this.installerConfigurator.getBrowserDownloadDir().exists() == false){
            this.installerConfigurator.getBrowserDownloadDir().mkdirs();
        }
    }

    @Override
    public void onShown(WindowEvent event) {
        this.status.setText(this.resourceBundle.getString("view.label.status.downloading_file"));
        String fileName = this.installerConfigurator.getBrowserURL().toString().substring( this.installerConfigurator.getBrowserURL().toString().lastIndexOf('/')+1, this.installerConfigurator.getBrowserURL().toString().length() );
        File zipFile = new File(this.installerConfigurator.getBrowserDownloadDir(),fileName);
        File tempZipFile =  new File(this.installerConfigurator.getBrowserDownloadDir(),new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".temp");
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(()->{
            try {
                if (DownloadedBrowserValidator.exist(this.installerConfigurator.getBrowserDownloadDir(), fileName)){
                    this.statusProgress.setProgress(1.0);
                } else {
                    IFileDownloader downloader = new HttpFileDownloader(this.installerConfigurator.getBrowserURL(), 3);
                    ((IStatusProgressable) downloader).setStatusProgress(this.statusProgress);
                    downloader.download(tempZipFile);
                    Files.move(tempZipFile.toPath(), zipFile.toPath());
                }
                PlatformImpl.runAndWait(()->{
                    this.status.setText(this.resourceBundle.getString("view.label.status.unzip_file"));
                });
                ZipUnziper.unzip(zipFile, this.installerConfigurator.getBrowserDir());
                this.completed = true;
            } catch (IoerException | IOException e) {
                this.exception = new WeberException(e);
                logger.error(e, e);
                PlatformImpl.runAndWait(()->{
                    ExceptionDialogFactory.create(this.resourceBundle.getString("view.dialog.exception.title"), e.getMessage(), e).setIconStageResource("/com/appscharles/libs/weber/installers/InstallerIcon.png").build().showAndWait();
                });
            }
            PlatformImpl.runAndWait(()->{
                this.fXStage.close();
            });
        });
        service.shutdown();
    }

    @FXML
    public void cancel() {
        this.fXStage.close();
    }

    /**
     * Getter for property 'exception'.
     *
     * @return Value for property 'exception'.
     */
    public WeberException getException() {
        return exception;
    }
}
