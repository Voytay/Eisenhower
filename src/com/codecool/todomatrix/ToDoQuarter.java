import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ToDoQuarter {
    List<ToDoItem> toDoItemList;

    public ToDoQuarter() {
        this.toDoItemList = new ArrayList<>();
    }

    public void addItem(String title, LocalDate deadline) {
        ToDoItem newItem = new ToDoItem(title, deadline);
        toDoItemList.add(newItem);
    }

    public void removeItem(int index) {
        toDoItemList.remove(index);
    }

    public void archiveItems() {
        for (int i = toDoItemList.size() - 1; i >= 0; i--) {
            if (toDoItemList.get(i).getIsDone() == true) toDoItemList.remove(i);
        }

    }

    public ToDoItem getItem(int index) {
        return toDoItemList.get(index);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int counter = 1;
        for (ToDoItem tdi : toDoItemList) {
            str.append(counter + ". ");
            str.append(tdi.toString() + "\n");
            counter++;
        }
        str.append("======================\n");
        return str.toString();
    }
}
