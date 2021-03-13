package scaudachuang.catlife.publisher.service.impl.provider;

import lombok.Data;

@Data
public class QueueCUDMessage {
    private Class<?> beanCl;
    private CUDType type;
    private Object element;

    public QueueCUDMessage(Class<?> beanCl, CUDType type, Object element) {
        this.beanCl = beanCl;
        this.type = type;
        this.element = element;
    }

    public enum CUDType {
        C_TYPE, U_TYPE, D_TYPE
    }
}
