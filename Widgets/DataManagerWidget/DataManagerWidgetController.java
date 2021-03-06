package Neuroshop.Widgets.DataManagerWidget;

import Neuroshop.Models.WidgetContainerModel;
import Neuroshop.Models.WidgetModels.DataManagerWidgetModel;

import java.util.Observable;
import java.util.Observer;

public class DataManagerWidgetController implements Observer {

    private DataManagerWidgetModel dataManagerWidgetModel;
    private WidgetContainerModel widgetContainerModel;

    @Override
    public void update(Observable o, Object arg) {
    }

    public void initModel(DataManagerWidgetModel dataManagerWidgetModel, WidgetContainerModel widgetContainerModel) {
        this.dataManagerWidgetModel = dataManagerWidgetModel;
        this.widgetContainerModel = widgetContainerModel;
    }
}
