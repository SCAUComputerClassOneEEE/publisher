package scaudachuang.catlife.publisher.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class TaskGetConfig {
    @Value("${detect.max-retry-times}")
    public int MAX_RETRY_TIME;
    @Value("${detect.max-task-cache}")
    public int MAX_TASK_CACHE;
    @Value("${detect.max-img-size}")
    public int MAX_IMG_SIZE;
}
