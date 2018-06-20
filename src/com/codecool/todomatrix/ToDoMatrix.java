import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ToDoMatrix {
    public Map<String, ToDoQuarter> toDoQuarters = new HashMap<>();

    public ToDoMatrix() {
        this.toDoQuarters.put("IU", new ToDoQuarter());
        this.toDoQuarters.put("IN", new ToDoQuarter());
        this.toDoQuarters.put("NU", new ToDoQuarter());
        this.toDoQuarters.put("NN", new ToDoQuarter());
    }

    public ToDoQuarter getQuarter(String status) {

        return this.toDoQuarters.get(status);
    }

    private Map<String, ToDoQuarter> getToDoQuarters() {
        return this.toDoQuarters;
    }

    public void addItem(String title, LocalDate localDate, boolean isImportant) {
        if (isImportant) {
            if (localDate.isBefore(LocalDate.now().plusDays(3))) {
                toDoQuarters.get("IU").addItem(title, localDate);
            } else {
                toDoQuarters.get("IN").addItem(title, localDate);
            }
        } else {
            if (localDate.isBefore(LocalDate.now().plusDays(3))) {
                toDoQuarters.get("NU").addItem(title, localDate);
            } else {
                toDoQuarters.get("NN").addItem(title, localDate);
            }
        }
    }

    public void addItem(String title, LocalDate localDate) {
        if (localDate.isBefore(LocalDate.now().plusDays(3))) {
            toDoQuarters.get("NU").addItem(title, localDate);
        } else {
            toDoQuarters.get("NN").addItem(title, localDate);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        toDoQuarters.forEach((k, v) -> {
            str.append(k + "\n");
            str.append(v.toString() + "\n");
        });
        return str.toString();
    }

    public void archiveItems() {
        this.toDoQuarters.forEach((k, v) -> {
            v.archiveItems();
        });

    }

    public void saveItemsToFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.write(quarterString("IU"));
            writer.write(quarterString("IN"));
            writer.write(quarterString("NU"));
            writer.write(quarterString("NN"));

            writer.close();
        } catch (IOException e) {
            System.out.println("File doesn't exists!");
        }
    }

    public String quarterString(String key) {
        String quarterString = "";

        for (int i = 0; i < toDoQuarters.get(key).toDoItemList.size(); i++) {
            String isImportant;
            if (key.equals("IN") || key.equals("IU")) {
                isImportant = "important";
            } else {
                isImportant = "";
            }
            quarterString += toDoQuarters.get(key).getItem(i).getTitle() + "|"
                    + toDoQuarters.get(key).getItem(i).getDeadline().getDayOfMonth() + "-"
                    + toDoQuarters.get(key).getItem(i).getDeadline().getMonthValue() + "|"
                    + isImportant + "\n";
        }
        return quarterString;
    }

    public void addItemsFromFile(String filename){
        try{
            Scanner sc = new Scanner(new File(filename));
            ArrayList<String> lines = new ArrayList<>();
            String title;


            while(sc.hasNextLine()){
                lines.add(sc.nextLine());
            }

            for(int i = 0; i < lines.size(); i++){
                String[] separatedLines = lines.get(i).split("\\|");

                title = separatedLines[0];
                String[] separatedDates = separatedLines[1].split("-");
                int day = Integer.parseInt(separatedDates[0]);
                int month = Integer.parseInt(separatedDates[1]);
                LocalDate deadline = LocalDate.of(2018, month, day);

                boolean isImportant;
                if(separatedLines.length == 3){
                    isImportant = true;
                }
                else isImportant = false;
                addItem(title, deadline, isImportant);

            }


        }catch(IOException e){
            System.out.println("File doesn't exists!");
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Data read error!");
        }
    }
}


