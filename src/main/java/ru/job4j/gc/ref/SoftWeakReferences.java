package ru.job4j.gc.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class SoftWeakReferences {

    public static void main(String[] args) throws InterruptedException {
        String abc = String.valueOf(10001);
        SoftReference<String> soft = new SoftReference<>(abc);
        WeakReference<String> weak = new WeakReference<>(abc);
        System.out.println(soft.get());
        System.out.println(weak.get());

        String str = weak.get();
        System.out.println(str);

        /* От изменения значения строковой переменной
        безопасные и слабые ссылки ссылаются на первоначальное значение */
        abc = String.valueOf(20002);
        System.gc();
        System.out.println(soft.get());
        System.out.println(weak.get());

        /* При удалении сильной ссылки -
        безопасные и слабые ссылки ссылаются на первоначальное значение,
        так как на значение остается безопасная ссылка,
        которая удяляется только в критических ситуациях */
        abc = null;
        System.gc();
        System.out.println(soft.get());
        System.out.println(weak.get());

        /* Сильной ссылки нет. А при удалении безопасной сылки -
        тут же удаляется и слабая */
        soft.clear();
        System.out.println(soft.get());
        System.gc();
        System.out.println(weak.get());

        str = null;
        System.gc();
        System.out.println(weak.get());
    }
}
