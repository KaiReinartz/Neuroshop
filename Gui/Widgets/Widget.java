package Neuroshop.Gui.Widgets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Widget {

    private Text label;
    private ImageView thumbnail;
    private Rectangle rectangleBorder;
    private StackPane previewPane;
    private AnchorPane whiteboardPane;

    public Widget(String name, Image thumbnail) {
        this.previewPane = new StackPane();
        this.thumbnail = new ImageView(thumbnail);
        this.label = new Text(name);
        this.rectangleBorder = new Rectangle(250, 200);
        addStyle();

        switch (name) {
            case "neuralNet": {
                NeuronalNetWidget nw = new NeuronalNetWidget();
                whiteboardPane = nw.getMainPane();
            }
            break;
            case "diagram": {
                //whiteboardPane = new DiagramWidget();
                whiteboardPane = new AnchorPane();
                whiteboardPane.getChildren().add(new ImageView(new Image("Neuroshop/Ressources/resultDiagramFull.png")));
            }
        }
        previewPane.setId(name);
        whiteboardPane.setId(name);
    }

    private void addStyle() {
        label.setFont(new Font("Caviar Dreams", 35));
        label.setFill(Color.WHITE);
        label.setOpacity(0);
        label.setDisable(true);
        rectangleBorder.setStrokeWidth(2);
        rectangleBorder.setStroke(Color.BLACK);
        rectangleBorder.setFill(Color.TRANSPARENT);
        rectangleBorder.setFill(Color.web("#222222"));
        rectangleBorder.setOpacity(0);
        rectangleBorder.setOnMouseEntered(event -> {
            rectangleBorder.setOpacity(0.7);
            label.setOpacity(1);
        });
        rectangleBorder.setOnMouseExited(event -> {
            rectangleBorder.setOpacity(0);
            label.setOpacity(0);
        });

        previewPane.getChildren().addAll(thumbnail, rectangleBorder, label);
    }

    public String getName() {
        return label.getText();
    }
    public StackPane getPreviewPane() {
        return previewPane;
    }
    public AnchorPane getWhiteboardPane() {
        return whiteboardPane;
    }
}
