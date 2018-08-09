package com.appscharles.libs.weber.installers;

import com.appscharles.libs.fxer.exceptions.FxerException;
import com.appscharles.libs.fxer.factories.FXStageFactory;
import com.appscharles.libs.fxer.factories.IFXStageFactory;
import com.appscharles.libs.fxer.stages.FXStage;
import com.appscharles.libs.weber.exceptions.WeberException;
import com.appscharles.libs.weber.installers.business.configurators.InstallerConfigurator;
import javafx.application.Platform;

public class Installer {

    public static void launch(InstallerConfigurator installerConfigurator) throws WeberException {
        try {
            InstallerController controller = new InstallerController(installerConfigurator);
            IFXStageFactory stageFactory = new FXStageFactory("/com/appscharles/libs/weber/installers/InstallerView.fxml",
                    "com/appscharles/libs/weber/installers/translations/Installer");
            stageFactory.setIcon("/com/appscharles/libs/weber/installers/InstallerIcon.png");
            if (installerConfigurator.getStageStylesheetResource() != null){
                stageFactory.addStylesheet(installerConfigurator.getStageStylesheetResource());
            }
            stageFactory.setController(controller);
            FXStage stage = stageFactory.create();
            stage.setResizable(false);
            if (installerConfigurator.getTest()) {
                Platform.setImplicitExit(false);
                stage.showFX();
                stage.closeFX();
            } else {
                stage.showAndWaitFX();
            }
            if (controller.getException() != null){
                throw new WeberException(controller.getException());
            }
        } catch (FxerException e) {
            throw new WeberException(e);
        }
    }
}
