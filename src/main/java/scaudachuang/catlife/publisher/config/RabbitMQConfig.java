package scaudachuang.catlife.publisher.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String Detect_Cat_Task_Queue = "DetectCatTaskQueue";
    public static final String Detect_Cat_Task_Routing = "DetectCatTaskRouting";
    public static final String Cat_Life_Record_Queue = "CatLifeRecordQueue";
    public static final String Cat_Life_Record_Routing = "CatLifeRecordRouting";
    public static final String Cat_Life_Delete_Queue = "CatLifeDeleteQueue";
    public static final String Cat_Life_Delete_Routing = "CatLifeDeleteRouting";
    public static final String Cat_Life_Exchange = "CatLifeExchange";

    @Bean
    public Queue detectCatTaskQueue() { return new Queue(Detect_Cat_Task_Queue,true); }
    @Bean
    public Queue catLifeRecordQueue() {
        return new Queue(Cat_Life_Record_Queue, true);
    }
    @Bean
    public Queue catLifeDeleteQueue() { return new Queue(Cat_Life_Delete_Queue, true); }
    @Bean
    public DirectExchange catLifeExchange() {
        return new DirectExchange(Cat_Life_Exchange);
    }

    @Bean
    public Binding detectCatTaskRouting() {
        return BindingBuilder
                .bind(detectCatTaskQueue())
                .to(catLifeExchange())
                .with(Detect_Cat_Task_Routing);
    }

    @Bean
    public Binding catLifeRecordRouting() {
        return BindingBuilder
                .bind(catLifeRecordQueue())
                .to(catLifeExchange())
                .with(Cat_Life_Record_Routing);
    }
    @Bean
    public Binding catLifeDeleteRouting() {
        return BindingBuilder
                .bind(catLifeDeleteQueue())
                .to(catLifeExchange())
                .with(Cat_Life_Delete_Routing);
    }
}
