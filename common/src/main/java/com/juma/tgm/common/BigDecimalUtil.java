package com.juma.tgm.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

/***
 * @author  huangxing
 *
 *
 * */
public class BigDecimalUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger( BigDecimalUtil.class );

    /***
     * 该方法 将所有的 类对象里面的 BigDecimal 类型 如果是null 则赋值为 0
     *
     *但是 这个 field 必须具有 get set 方法
     * */
    public static void nullToZero( Object object ){

        // 对象不能为空
        if( object != null ) {
            Field [] fields = object.getClass().getDeclaredFields();
            if( fields  != null && fields.length > 0 ) {
                for( Field field : fields ) {
                    // 如果不是一个 BigDecimal 那就忽略
                    if( !field.getType().equals( BigDecimal.class)) {
                        continue;
                    }
                    PropertyDescriptor propertyDescriptor = null;
                    try {
                        propertyDescriptor= new PropertyDescriptor(field.getName(), object.getClass() );
                    } catch (IntrospectionException e) {
                        // 不可能没有
                    }
                    Method readMethod = propertyDescriptor.getReadMethod();
                    try {
                       BigDecimal bigDecimalValue = (BigDecimal) readMethod.invoke( object , new Object [] {} );
                       if( bigDecimalValue == null ) {
                           Method writeMethod = propertyDescriptor.getWriteMethod();
                           writeMethod.invoke( object , BigDecimal.ZERO );
                       }
                    } catch (IllegalAccessException| InvocationTargetException e) {
                        LOGGER.error( "错误的获取,或者设置（"+field.getName()+"）属性值:" + e.getMessage() , e );
                    }
                }
            }
        }
    }

    /***
     * 该方法 将所有的 类对象里面的 BigDecimal 类型 如果是null 则赋值为 0
     *
     *但是 这个 field 必须具有 get set 方法
     * */
    public static void nullToZero( Object... objects){

        for( Object object : objects ) {
            nullToZero( object);
        }
    }
}
