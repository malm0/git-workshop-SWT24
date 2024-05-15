package org.tud.sse.storage;

import org.tud.sse.model.Group;
import org.tud.sse.model.Student;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryGroupStorage implements GroupStorage {

    private final Map<Integer, Student> allStudents = new HashMap<>();
    private final Map<String, Group> allGroups = new HashMap<>();
    private final Map<Student, Set<Group>> mapping = new HashMap<>();

    @Override
    public void storeStudents(Set<Student> students) {
        for(Student s : students) { storeStudent(s); }
    }

    @Override
    public void storeStudent(Student student) {
        if(!allStudents.containsKey(student.getStudentId())){
            allStudents.put(student.getStudentId(), student);
        }
    }

    @Override
    public void storeGroups(Set<Group> groups) {
        for(Group g : groups) { storeGroup(g); }
    }

    @Override
    public void storeGroup(Group group) {
        // Make sure all students are stored
        storeStudents(group.getMembers());

        // Make sure group is stored
        if(!allGroups.containsKey(group.getName())){
            allGroups.put(group.getName(), group);
        }

        // Store membership for all members of the group
        for(Student member: group.getMembers()){
            if(!mapping.containsKey(member)) mapping.put(member, new HashSet<>());

            mapping.get(member).add(group);
        }

        // Remove membership for all students that are not part of the group (anymore)
        for(Student s: mapping.keySet()){
            if(mapping.get(s).contains(group) && !group.getMembers().contains(s))
                mapping.get(s).remove(group);
        }
    }

    @Override
    public Set<Group> getAllGroupsFor(Student s) {
        return mapping.getOrDefault(s, null);
    }

    @Override
    public boolean hasGroupForLecture(Student s, String lecture) {
        Set<Group> studentGroups = getAllGroupsFor(s);

        if(studentGroups == null) return false;

        for(Group g: studentGroups){
            if(g.getLecture().equals(lecture)) return true;
        }

        return false;
    }

    @Override
    public Set<Student> getStudentsWithoutGroups() {

        Set<Student> retSet = new HashSet<>();

        for(Student s: allStudents.values()){
            if(!mapping.containsKey(s) || mapping.get(s).isEmpty()) retSet.add(s);
        }

        return retSet;
    }

    @Override
    public Set<Student> getAllStudents() {
        return new HashSet<>(allStudents.values());
    }

    @Override
    public Set<Group> getAllGroups() {
        return new HashSet<>(allGroups.values());
    }

    @Override
    public Student getStudent(int studentId) {
        return allStudents.getOrDefault(studentId, null);
    }

    @Override
    public boolean hasGroup(String gName) {
        return allGroups.containsKey(gName);
    }


}
