package jiconfont.screenshot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jiconfont.IconCode;
import jiconfont.icons.*;
import jiconfont.javafx.IconBuilderFX;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


/**
 * Copyright (c) 2016 jIconFont <BR>
 * <BR>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:<BR>
 * <BR>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.<BR>
 * <BR>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class IconFontScreenshot extends Application {

    private void writeImage(Pane iconsPane, String filename) {
        filename = "./target/" + filename.toLowerCase() + ".png";
        new Scene(iconsPane);
        WritableImage image = iconsPane.snapshot(new SnapshotParameters(), null);
        File file = new File(filename);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createIcon(IconCode iconCode, String filename) {
        HBox iconsPane = new HBox();
        iconsPane.setAlignment(Pos.CENTER);
        iconsPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        Label label = IconBuilderFX.newIcon(iconCode).setSize(25).setColor(Color.CORNFLOWERBLUE).buildLabel();
        iconsPane.getChildren().add(label);

        writeImage(iconsPane, filename);
    }

    private void createMultipleColors(IconCode iconCode, String filename) {
        HBox iconsPane = new HBox();
        iconsPane.setAlignment(Pos.CENTER);
        iconsPane.setSpacing(5);
        iconsPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        Label label1 = IconBuilderFX.newIcon(iconCode).setSize(30).setColor(Color.CORNFLOWERBLUE).buildLabel();
        iconsPane.getChildren().add(label1);

        Label label2 = IconBuilderFX.newIcon(iconCode).setSize(30).setColor(Color.DEEPSKYBLUE).buildLabel();
        iconsPane.getChildren().add(label2);

        Label label3 = IconBuilderFX.newIcon(iconCode).setSize(30).setColor(Color.DARKTURQUOISE).buildLabel();
        iconsPane.getChildren().add(label3);

        Label label4 = IconBuilderFX.newIcon(iconCode).setSize(30).setColor(Color.LIGHTBLUE).buildLabel();
        iconsPane.getChildren().add(label4);

        writeImage(iconsPane, filename);
    }

    private void createMultipleSizes(IconCode iconCode, String filename) {
        HBox iconsPane = new HBox();
        iconsPane.setAlignment(Pos.CENTER);
        iconsPane.setSpacing(5);
        iconsPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        int size = 10;
        for (int i = 0; i < 5; i++) {
            Label label = IconBuilderFX.newIcon(iconCode).setSize(size).setColor(Color.BLACK).buildLabel();
            iconsPane.getChildren().add(label);
            size = size + 10;
        }

        writeImage(iconsPane, filename);
    }

    private void createIcon(IconCode[] iconCodes, String filename) {
        TilePane iconsPane = new TilePane();
        iconsPane.setAlignment(Pos.CENTER);
        iconsPane.setPrefWidth(650);
        iconsPane.setMaxWidth(650);
        iconsPane.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        iconsPane.setHgap(5);
        iconsPane.setVgap(5);
        for (IconCode icon : iconCodes) {
            Label label = IconBuilderFX.newIcon(icon).setSize(25).setColor(Color.BLACK).buildLabel();
            iconsPane.getChildren().add(label);
        }

        writeImage(iconsPane, filename);
    }

    @Override
    public void start(Stage stage) {
        createIcon(GoogleMaterialDesignIcons.values(), GoogleMaterialDesignIcons.class.getSimpleName());
        createIcon(FontAwesome.values(), FontAwesome.class.getSimpleName());
        createIcon(Iconic.values(), Iconic.class.getSimpleName());
        createIcon(Elusive.values(), Elusive.class.getSimpleName());
        createIcon(Entypo.values(), Entypo.class.getSimpleName());
        createMultipleSizes(FontAwesome.CHECK, "multiplesizes");
        createMultipleColors(FontAwesome.CHECK, "multiplecolors");
        createIcon(Elusive.LINKEDIN, "linkedin");
        createIcon(Elusive.GITHUB, "github");
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
