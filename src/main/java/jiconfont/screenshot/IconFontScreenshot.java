package jiconfont.screenshot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import jiconfont.IconCode;
import jiconfont.icons.Elusive;
import jiconfont.icons.Entypo;
import jiconfont.icons.FontAwesome;
import jiconfont.icons.GoogleMaterialDesignIcons;
import jiconfont.icons.Iconic;
import jiconfont.javafx.IconFontFX;
import jiconfont.javafx.IconNode;

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

  private void writeImage(Pane iconsPane, String path, String filename) {
    String f = getTargetDir();
    if (path != null) {
      f += path.toLowerCase() + "/";
      File dir = new File(f);
      if (dir.exists() == false) {
        dir.mkdir();
      }
    }
    f += filename.toLowerCase() + ".png";
    new Scene(iconsPane);
    SnapshotParameters parameters = new SnapshotParameters();
    parameters.setFill(Color.TRANSPARENT);
    WritableImage image = iconsPane.snapshot(parameters, null);
    File file = new File(f);
    try {
      ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  private IconNode buildIconNode(IconCode iconCode, Number size, Color fill) {
    IconNode iconNode = new IconNode(iconCode);
    iconNode.setIconSize(size);
    iconNode.setFill(fill);
    return iconNode;
  }

  private void iconScreenshot(IconCode iconCode, String filename, Color color) {
    HBox iconsPane = new HBox();
    iconsPane.setAlignment(Pos.CENTER);
    iconsPane.setBackground(
      new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

    IconNode iconNode = buildIconNode(iconCode, 35, color);
    iconsPane.getChildren().add(iconNode);

    writeImage(iconsPane, null, filename);
  }

  private void createMultipleColors(IconCode iconCode, String filename) {
    HBox iconsPane = new HBox();
    iconsPane.setAlignment(Pos.CENTER);
    iconsPane.setSpacing(5);
    iconsPane.setBackground(
      new Background(new BackgroundFill(Color.WHITE, null, null)));

    IconNode iconNode1 = buildIconNode(iconCode, 30, Color.CORNFLOWERBLUE);
    iconsPane.getChildren().add(iconNode1);

    IconNode iconNode2 = buildIconNode(iconCode, 30, Color.DEEPSKYBLUE);
    iconsPane.getChildren().add(iconNode2);

    IconNode iconNode3 = buildIconNode(iconCode, 30, Color.DARKTURQUOISE);
    iconsPane.getChildren().add(iconNode3);

    IconNode iconNode4 = buildIconNode(iconCode, 30, Color.LIGHTBLUE);
    iconsPane.getChildren().add(iconNode4);

    writeImage(iconsPane, null, filename);
  }

  private void createMultipleSizes(IconCode iconCode, String filename) {
    HBox iconsPane = new HBox();
    iconsPane.setAlignment(Pos.CENTER);
    iconsPane.setSpacing(5);
    iconsPane.setBackground(
      new Background(new BackgroundFill(Color.WHITE, null, null)));

    int size = 10;
    for (int i = 0; i < 5; i++) {
      IconNode iconNode = buildIconNode(iconCode, size, Color.BLACK);
      iconsPane.getChildren().add(iconNode);
      size = size + 10;
    }

    writeImage(iconsPane, null, filename);
  }

  private void iconFontScreenshot(IconCode[] iconCodes, String filename) {
    TilePane iconsPane = new TilePane();
    iconsPane.setAlignment(Pos.CENTER);
    iconsPane.setPrefWidth(650);
    iconsPane.setMaxWidth(650);
    iconsPane.setBackground(
      new Background(new BackgroundFill(Color.WHITE, null, null)));
    iconsPane.setHgap(5);
    iconsPane.setVgap(5);
    for (IconCode icon : iconCodes) {
      IconNode iconNode = buildIconNode(icon, 25, Color.BLACK);
      iconsPane.getChildren().add(iconNode);
    }
    writeImage(iconsPane, null, filename);
  }

  private void iconCatalogScreenshot(IconCode[] iconCodes, String path) {
    path = path.toLowerCase();

    StringBuilder sb = new StringBuilder();
    sb.append("Name | Icon (16, 20, 24, 32)\n");
    sb.append("------------ | -------------\n");

    for (IconCode icon : iconCodes) {
      TilePane iconsPane = new TilePane();
      iconsPane.setAlignment(Pos.CENTER);
      iconsPane.setPrefWidth(150);
      iconsPane.setMaxWidth(150);
      iconsPane.setBackground(
        new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
      iconsPane.setHgap(5);
      iconsPane.setVgap(5);
      IconNode iconNode1 = buildIconNode(icon, 16, Color.BLACK);
      IconNode iconNode2 = buildIconNode(icon, 20, Color.BLACK);
      IconNode iconNode3 = buildIconNode(icon, 24, Color.BLACK);
      IconNode iconNode4 = buildIconNode(icon, 32, Color.BLACK);
      iconsPane.getChildren().addAll(iconNode1, iconNode2, iconNode3,
        iconNode4);
      String filename = icon.name().toLowerCase();
      writeImage(iconsPane, path, filename);

      sb.append(icon.name());
      sb.append(" | ");
      sb.append("![icon](http://jiconfont.github.io/images/");
      sb.append(path);
      sb.append("/");
      sb.append(filename);
      sb.append(".png)");
      sb.append("\n");
    }

    String f = getTargetDir();
    if (path != null) {
      f += path.toLowerCase() + "/";
      File dir = new File(f);
      if (dir.exists() == false) {
        dir.mkdir();
      }
    }
    f += "icontable.txt";

    try {
      File file = new File(f);
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(sb.toString());
      bw.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String getTargetDir() {
    return "./target/images/";
  }

  private void createTargetDir() {
    File dir = new File(getTargetDir());
    if (dir.exists() == false) {
      dir.mkdir();
    }
  }

  @Override
  public void start(Stage stage) {
    createTargetDir();

    IconFontFX.register(GoogleMaterialDesignIcons.getIconFont());
    IconFontFX.register(FontAwesome.getIconFont());
    IconFontFX.register(Iconic.getIconFont());
    IconFontFX.register(Elusive.getIconFont());
    IconFontFX.register(Entypo.getIconFont());

    iconFontScreenshot(GoogleMaterialDesignIcons.values(),
      GoogleMaterialDesignIcons.class.getSimpleName());
    iconFontScreenshot(FontAwesome.values(), FontAwesome.class.getSimpleName());
    iconFontScreenshot(Iconic.values(), Iconic.class.getSimpleName());
    iconFontScreenshot(Elusive.values(), Elusive.class.getSimpleName());
    iconFontScreenshot(Entypo.values(), Entypo.class.getSimpleName());

    iconCatalogScreenshot(GoogleMaterialDesignIcons.values(),
      GoogleMaterialDesignIcons.class.getSimpleName());
    iconCatalogScreenshot(FontAwesome.values(),
      FontAwesome.class.getSimpleName());
    iconCatalogScreenshot(Iconic.values(), Iconic.class.getSimpleName());
    iconCatalogScreenshot(Elusive.values(), Elusive.class.getSimpleName());
    iconCatalogScreenshot(Entypo.values(), Entypo.class.getSimpleName());

    createMultipleSizes(FontAwesome.CHECK, "multiplesizes");

    createMultipleColors(FontAwesome.CHECK, "multiplecolors");

    iconScreenshot(Elusive.HOME_ALT, "home", Color.WHITE);
    iconScreenshot(Elusive.HOME_ALT, "home_hover", Color.WHITESMOKE);

    iconScreenshot(Elusive.GITHUB, "github", Color.WHITE);
    iconScreenshot(Elusive.GITHUB, "github_hover", Color.WHITESMOKE);

    Platform.exit();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
