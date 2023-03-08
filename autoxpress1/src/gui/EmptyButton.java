package gui;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

public class EmptyButton<S> extends TableCell<S, Void> {

    private final Button emptyButton = new Button(" ");

    public void EmptyButtonTableCell() {
        emptyButton.setOnAction(event -> {
            // do nothing
        });
    }

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(emptyButton);
        }
    }
}
