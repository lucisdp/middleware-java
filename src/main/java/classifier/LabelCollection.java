package classifier;

import java.util.ArrayList;
import java.util.List;

public class LabelCollection {
    private List<Label> labels = new ArrayList<>();

    public int size(){
        return labels.size();
    }

    public void add(Label label){
        labels.add(label);
    }

    public Label get(int index){
        return labels.get(index);
    }
}
