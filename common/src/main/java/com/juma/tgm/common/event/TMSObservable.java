package com.juma.tgm.common.event;


import java.util.List;
import java.util.Observable;
import java.util.Observer;


/***
 *
 * 定义一个观察者 接口， 用于注册 移除 和事件触发, 这里直接继承 java 的 Observable  如果 以后存在 扩展
 *
 * 咳一扩展这个类
 *
 *
 * @author huangxing
 *
 * @date 2018.08.28  下午 15 点 50 分
 *
 * */
public class TMSObservable extends Observable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //增加一个批量添加方便配置
    public void setObservers(List<Observer> observers) {
        if( observers != null && !observers.isEmpty()) {
            for( Observer observer : observers ) {
                super.addObserver( observer );
            }
        }
    }

    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();// 每一次通知都认为是有变化的
        super.notifyObservers(arg);
    }


    /******************************************在这里 增加了 一个name 属性 更好的表达 一个观察者容器*****************************************************/
}
