import com.company.Task;

import java.util.ArrayList;

public class theme {
    private String name;
    private int allScore;
    private ArrayList<Task> tasks = new ArrayList<Task>();


    public theme(String name, int allScore, ArrayList<Task> tasks) {
        this.name = name;
        this.allScore = allScore;
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder(this.name + '\n');
        for (var t: tasks) {
            builder.append(t.toString() + '\n');
        }
        return builder.toString();
    }
}
