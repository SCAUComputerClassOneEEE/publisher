package scaudachuang.catlife.publisher.service;

/**
 * 由于读写分离的思想，
 * WriterService负责除识别任务插入之外的所有“增删改”的数据库操作。
 * 将操作对象 opO 封装成 QueueCUDMessage， 提交到 MQ。
 *
 * @author best lu
 * @since 2021/3/10
 */
public interface WriterService {
    /**
     *
     * @param o bean object will be saved
     */
    void add(Object o);

    /**
     *
     * @param o bean object will be deleted
     */
    void sub(Object o);

    /**
     *
     * @param o bean object will be modified
     */
    void mod(Object o);
}
