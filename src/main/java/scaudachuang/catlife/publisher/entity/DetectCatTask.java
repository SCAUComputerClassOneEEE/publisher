package scaudachuang.catlife.publisher.entity;

import lombok.Data;

import java.util.Date;

@Data
public class DetectCatTask {
    private String taskId;
    private String resultClass;
    private Date consumeTime;
    private boolean isDone;

    public DetectCatTask() {

    }

    public DetectCatTask(String id) {
        this.taskId = id;
    }

    /**
     *
     * for hashMap counter key
     * */
    @Override
    public int hashCode() {
        return taskId.hashCode();
    }

    /**
     *
     *
     * */
    @Override
    public String toString() {
        return null;
    }
}
