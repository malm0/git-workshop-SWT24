package org.tud.sse.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Group {

    private final String gName;
    private final String lName;

    private final Set<Student> students = new HashSet<>();

    public Group(String groupName, String lectureName){
        this.gName = groupName;
        this.lName = lectureName;
    }

    public Group(String groupName, String lectureName, Student... members){
        this(groupName, lectureName);
        this.students.addAll(Arrays.asList(members));
    }

    public Set<Student> getMembers() {
        return this.students;
    }

    public String getName(){
        return this.gName;
    }

    public String getLecture(){
        return this.lName;
    }

    @Override
    public String toString() {
        return gName + " (Lecture " + lName + ")";
    }
}
