package scaudachuang.catlife.publisher.service.impl;

import org.springframework.stereotype.Service;
import scaudachuang.catlife.publisher.entity.*;
import scaudachuang.catlife.publisher.service.CatLifeService;
import scaudachuang.catlife.publisher.service.impl.provider.CatLifeQueueProvider;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Service
public class CatLifeServiceImpl implements CatLifeService {

    private static final Set<Class<?>> TABLE_SET;

    static {
        TABLE_SET = new HashSet<>();
        try {
            TABLE_SET.add(Cat.class);
            TABLE_SET.add(CatLifeRecord.class);
            TABLE_SET.add(CatOwner.class);
            TABLE_SET.add(DetectCatTask.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Resource
    private CatLifeQueueProvider provider;

    @Override
    public void add(Object o) {
        provider.provideInsertMessage(o.getClass().getSimpleName(), o);
    }

    @Override
    public void sub(Object o) {
        final PK[] pk = new PK[1];
        TABLE_SET.forEach((cl) ->{
            if (cl == o.getClass()) {
                try {
                    pk[0] = (PK)cl.getMethod("getPK").invoke(o);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(pk[0]);
        provider.provideDeleteMessage(o.getClass().getSimpleName(), pk[0]);
    }
}
