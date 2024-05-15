package org.tud.sse.storage;

import org.tud.sse.model.Group;
import org.tud.sse.model.Student;

import java.util.Set;

public interface GroupStorage {

    void storeStudents(Set<Student> students);

    void storeStudent(Student student);

    void storeGroups(Set<Group> groups);

    void storeGroup(Group group);

    Set<Group> getAllGroupsFor(Student s);

    boolean hasGroupForLecture(Student s, String lecture);

    Set<Student> getStudentsWithoutGroups();

    Set<Student> getAllStudents();

    Set<Group> getAllGroups();

    Student getStudent(int studentId);

    boolean hasGroup(String gName);

}
