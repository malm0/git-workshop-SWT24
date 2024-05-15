package org.tud.sse.model;

public class Student {

    private final int mNo;
    private final String name;

    public Student(int pStudentId, String pName){
        this.mNo = pStudentId;
        this.name = pName;
    }

    public int getStudentId(){
        return this.mNo;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object other){
        if(!(other instanceof Student))
            return false;
        else return ((Student)other).getStudentId() == this.mNo;
    }

    @Override
    public int hashCode() {
        return mNo;
    }

    @Override
    public String toString() {
        return name + "(#" + mNo + ")";
    }
}
