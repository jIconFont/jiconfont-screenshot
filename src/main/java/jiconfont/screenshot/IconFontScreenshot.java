package jiconfont.screenshot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jiconfont.IconCode;
import jiconfont.icons.FontAwesome;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.icons.Iconic;
import jiconfont.icons.MFGLabs;
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

        new Scene(iconsPane);

        if (true) {
            WritableImage image = iconsPane.snapshot(new SnapshotParameters(), null);
            File file = new File(filename);
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        createIcon(GoogleMaterialDesignIcons.values(), GoogleMaterialDesignIcons.class.getSimpleName().toLowerCase() + ".png");
        createIcon(FontAwesome.values(), FontAwesome.class.getSimpleName().toLowerCase() + ".png");
        createIcon(MFGLabs.values(), MFGLabs.class.getSimpleName().toLowerCase() + ".png");
        createIcon(Iconic.values(), Iconic.class.getSimpleName().toLowerCase() + ".png");

        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
