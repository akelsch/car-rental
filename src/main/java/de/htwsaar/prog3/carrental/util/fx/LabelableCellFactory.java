package de.htwsaar.prog3.carrental.util.fx;

import de.htwsaar.prog3.carrental.model.BaseEntity;
import de.htwsaar.prog3.carrental.util.MessageUtils;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import lombok.RequiredArgsConstructor;
import org.springframework.context.NoSuchMessageException;

@RequiredArgsConstructor
public class LabelableCellFactory<T extends BaseEntity> implements Callback<TableColumn<T, Labelable>, TableCell<T, Labelable>> {

    private final MessageUtils messageUtils;

    /**
     * @see TableColumn#DEFAULT_CELL_FACTORY
     */
    @Override
    public TableCell<T, Labelable> call(TableColumn<T, Labelable> param) {
        return new TableCell<>() {
            @Override
            protected void updateItem(Labelable item, boolean empty) {
                if (item == getItem()) return;

                super.updateItem(item, empty);

                if (item == null) {
                    super.setText(null);
                    super.setGraphic(null);
                } else {
                    try {
                        super.setText(messageUtils.getMessage(item.getLabel()));
                    } catch (NoSuchMessageException e) {
                        super.setText(item.toString());
                    }
                    super.setGraphic(null);
                }
            }
        };
    }
}
