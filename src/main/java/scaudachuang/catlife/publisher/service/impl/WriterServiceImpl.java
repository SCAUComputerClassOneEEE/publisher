package scaudachuang.catlife.publisher.service.impl;

import org.springframework.stereotype.Service;
import scaudachuang.catlife.publisher.service.WriterService;
import scaudachuang.catlife.publisher.service.impl.provider.CatLifeQueueProvider;

import javax.annotation.Resource;

@Service
public class WriterServiceImpl implements WriterService {

    @Resource
    private CatLifeQueueProvider provider;

    @Override
    public void add(Object o) {
        provider.provideInsertMessage(o);
    }

    @Override
    public void sub(Object o) {
        provider.provideDeleteMessage(o);
    }

    @Override
    public void mod(Object o) {
        provider.provideUpdateMessage(o);
    }

}
