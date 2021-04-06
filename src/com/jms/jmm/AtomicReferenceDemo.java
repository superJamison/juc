package com.jms.jmm;

import java.util.concurrent.atomic.AtomicReference;


class User{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/5 15:04
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user = new User("Jamison", 12);
        User user1 = new User("zms", 21);

        AtomicReference<User> reference = new AtomicReference<>();
        reference.set(user);
        System.out.println(reference.get()); //User{name='Jamison', age=12}
        System.out.println(reference.compareAndSet(user, user1) + "\t" + reference.get());//true	User{name='zms', age=21}
        System.out.println(reference.compareAndSet(user, user1) + "\t" + reference.get());//false	User{name='zms', age=21}

    }
}
