package org.noear.solon.core.wrap;

import org.noear.solon.core.AopContext;
import org.noear.solon.core.VarHolder;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.ParameterizedType;

/**
 * 字段变量容器 临时对象
 *
 * 为了稳藏 FieldWrap 的一些特性，并统一对外接口
 *
 * @author noear
 * @since 1.0
 * */
public class VarHolderOfField implements VarHolder {
    protected final FieldWrap fw;
    protected final Object obj;
    protected final AopContext ctx;

    public VarHolderOfField(AopContext ctx, FieldWrap fw, Object obj) {
        this.ctx = ctx;
        this.fw = fw;
        this.obj = obj;
    }

    @Override
    public AopContext context() {
        return ctx;
    }

    @Override
    public ParameterizedType getGenericType() {
        return fw.genericType;
    }

    @Override
    public boolean isField() {
        return true;
    }

    /**
     * 获取字段类型
     * */
    @Override
    public Class<?> getType(){
        return fw.type;
    }

    /**
     * 获取所有注解
     * */
    @Override
    public Annotation[] getAnnoS(){
        return fw.annoS;
    }

    @Override
    public String getFullName() {
        return fw.entityClz.getName() + "::" + fw.field.getName();
    }


    /**
     * 设置字段的值
     */
    @Override
    public void setValue(Object val) {
        fw.setValue(obj, val, true);
    }
}
