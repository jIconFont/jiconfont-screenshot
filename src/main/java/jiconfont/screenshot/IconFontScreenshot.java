package jiconfont.screenshot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
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

  private IconNode buildIconNode(IconCode iconCode, Number size, Paint fill) {
    IconNode iconNode = new IconNode(iconCode);
    iconNode.setIconSize(size);
    iconNode.setFill(fill);
    return iconNode;
  }

  private void iconScreenshot(IconCode iconCode, String filename, Paint fill) {
    HBox iconsPane = new HBox();
    iconsPane.setAlignment(Pos.CENTER);
    iconsPane.setBackground(
      new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

    IconNode iconNode = buildIconNode(iconCode, 35, fill);
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

  private void iconCheatSheet(IconCode[] iconCodes, String name) {

    name = name.toLowerCase();

    StringBuilder cheatsheet = new StringBuilder();
    cheatsheet
      .append("<TABLE width=\"100%\" cellpadding=\"5\" cellspacing=\"0\">\n");
    cheatsheet.append("<TR>");
    cheatsheet.append(
      "<TH width='50%'>Name</TH><TH width='50%'>Icon (16, 20, 24, 32)</TH>");
    cheatsheet.append("</TR>\n");

    boolean odd = true;
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
      writeImage(iconsPane, name, filename);

      if (odd) {
        cheatsheet.append("<TR class='tr1'>");
      }
      else {
        cheatsheet.append("<TR class='tr2'>");
      }
      odd = !odd;
      cheatsheet.append("<TD width='50%'>");
      cheatsheet.append(icon.name());
      cheatsheet.append("</TD>");
      cheatsheet.append("<TD width='50%'>");
      cheatsheet.append("<img src='images/");
      cheatsheet.append(name);
      cheatsheet.append("/");
      cheatsheet.append(filename);
      cheatsheet.append(".png'/>");
      cheatsheet.append("</TD>");
      cheatsheet.append("</TR>\n");
    }
    cheatsheet.append("</TABLE>\n");

    try {
      Properties properties = new Properties();
      properties.load(IconFontScreenshot.class
        .getResourceAsStream("/" + name + ".properties"));
      String links = new String(Files.readAllBytes(Paths.get(
        IconFontScreenshot.class.getResource("/" + name + ".links").toURI())));
      String template =
        new String(Files.readAllBytes(Paths.get(IconFontScreenshot.class
          .getResource("/iconscheatsheet.html").toURI())));
      template =
        template.replaceFirst("REPLACE_CHEATSHEET", cheatsheet.toString());
      template = template.replaceFirst("REPLACE_LINKS", links);
      template =
        template.replaceAll("REPLACE_TITLE", properties.getProperty("title"));
      template = template.replaceAll("REPLACE_DOWNLOAD_LINK",
        properties.getProperty("download_link"));
      template = template.replaceAll("REPLACE_ARTIFACTID",
        properties.getProperty("artifactId"));
      template = template.replaceAll("REPLACE_VERSION",
        properties.getProperty("version"));

      File htmlFile = new File(getTargetDir() + name + ".html");
      if (!htmlFile.exists()) {
        htmlFile.createNewFile();
      }

      FileWriter fw = new FileWriter(htmlFile.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(template);
      bw.close();
    }
    catch (Exception e) {
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

    iconCheatSheet(GoogleMaterialDesignIcons.values(),
      GoogleMaterialDesignIcons.class.getSimpleName());
    iconCheatSheet(Elusive.values(), Elusive.class.getSimpleName());
    iconCheatSheet(Entypo.values(), Entypo.class.getSimpleName());
    iconCheatSheet(FontAwesome.values(), FontAwesome.class.getSimpleName());
    iconCheatSheet(Iconic.values(), Iconic.class.getSimpleName());

    createMultipleSizes(FontAwesome.CHECK, "multiplesizes");

    createMultipleColors(FontAwesome.CHECK, "multiplecolors");

    Stop[] stops =
      new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.DARKSEAGREEN) };
    LinearGradient fill =
      new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE, stops);

    iconScreenshot(Elusive.HOME_ALT, "home", Color.WHITE);
    iconScreenshot(Elusive.HOME_ALT, "home_hover", fill);

    iconScreenshot(Elusive.GITHUB, "github", Color.WHITE);
    iconScreenshot(Elusive.GITHUB, "github_hover", fill);

    iconScreenshot(Elusive.DOWNLOAD, "download", Color.WHITE);
    iconScreenshot(Elusive.DOWNLOAD, "download_hover", fill);

    Platform.exit();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
