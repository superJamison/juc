package com.jms.testTransferValue;

/**
 * @author Jamison
 * @version 1.0
 * @date 2021/4/3 14:06
 */

public class Person {
    private String id;
    private String personName;

    public Person(String personName){
        this.personName = personName;
    }

    public Person() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
