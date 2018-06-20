import java.time.LocalDate;

public class ToDoItem {
    public String title;
    public LocalDate deadline;
    public boolean isDone = false;

    public ToDoItem(String title, LocalDate deadline) {
        this.title = title;
        this.deadline = deadline;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        if (this.isDone) {
            str.append("[x] ");
        } else {
            str.append("[ ] ");
        }
        str.append(deadline.getDayOfMonth());
        str.append("-");
        str.append(deadline.getMonthValue());
        str.append(" " + title);
        return str.toString();
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getTitle() {
        return this.title;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }
}
