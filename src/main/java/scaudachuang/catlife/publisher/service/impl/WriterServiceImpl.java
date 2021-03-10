package scaudachuang.catlife.publisher.service.impl;

import org.springframework.stereotype.Service;
import scaudachuang.catlife.publisher.entity.*;
import scaudachuang.catlife.publisher.service.WriterService;
import scaudachuang.catlife.publisher.service.impl.provider.CatLifeQueueProvider;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Service
public class WriterServiceImpl implements WriterService {

    private static final Set<Class<?>> TABLE_SET;

    static {
        TABLE_SET = new HashSet<>();
        TABLE_SET.add(Cat.class);
        TABLE_SET.add(CatLifeRecord.class);
        TABLE_SET.add(CatOwner.class);
        TABLE_SET.add(DetectCatTask.class);
        TABLE_SET.add(Comments.class);
        TABLE_SET.add(Follow.class);
        TABLE_SET.add(HaveCat.class);
        TABLE_SET.add(Memorandum.class);
        TABLE_SET.add(Moments.class);
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
