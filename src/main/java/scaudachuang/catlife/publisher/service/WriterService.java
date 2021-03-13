package scaudachuang.catlife.publisher.service;

/**
 *
 * 同一数据库写服务接口
 *
 * 由于读写分离的思想，
 * WriterService负责除识别任务插入之外的所有“增删改”的数据库操作。
 * 将操作对象 opO 封装成 QueueCUDMessage， 提交到 MQ。
 *
 * 具体看service.impl包下实现
 *
 * @author best lu
 * @since 2021/3/10
 */
public interface WriterService {
    /**
     * 方法参数类型为 Object ，把需要被 添加 操作的对象作为参数。
     * 对象内容需要先赋值。
     * @param o bean object will be saved
     */
    void add(Object o);

    /**
     * 方法参数类型为 Object ，把需要被 删除 操作的对象作为参数。
     * 对象的数据库主键需要先赋值。
     * @param o bean object will be deleted
     */
    void sub(Object o);

    /**
     * 方法参数类型为 Object ，把需要被 修改 操作的对象作为参数。
     * 对象的删除主键需要先赋值。
     * @param o bean object will be modified
     */
    void mod(Object o);
}
