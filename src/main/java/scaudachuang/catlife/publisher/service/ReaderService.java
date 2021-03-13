package scaudachuang.catlife.publisher.service;

import java.util.Map;

public interface ReaderService {
    /**
     * 同一数据库读取服务接口
     *
     * 一、方法参数：包含表名和主键
     * 1.表名用类 Class代表 XXX.Class
     * 2.主键用 Map<String, Object> keyString:主键名字 - valueObject:主键值。
     * 二、返回值：
     * Object对象。
     *
     * @param clTable 表名
     * @param pkMap 主键
     * @return entity包下的类对象，需要controller层向上转型。
     */
    Object get(Class<?> clTable, Map<String, Object> pkMap);
}
