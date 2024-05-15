package org.tud.sse.control;

import org.tud.sse.model.Group;
import org.tud.sse.model.Student;
import org.tud.sse.storage.GroupStorage;
import org.tud.sse.storage.InMemoryGroupStorage;

public class InputHandler {

    private final GroupStorage storage = new InMemoryGroupStorage();

    public void handleInput(String input){
        String[] parts = input.split(" ");

        if(parts.length == 0) System.err.println("Please enter a valid command");
        else if(parts[0].equalsIgnoreCase("list")){
            handleListInput(parts);
        } else if(parts[0].equalsIgnoreCase("add")){
            handleAddInput(parts);
        } else if(parts[0].equalsIgnoreCase("help")){
            System.out.println("Commands available:");
            System.out.println("list <groups|students|memberships>");
            System.out.println("add <group|student|membership>");
            System.out.println("help");
            System.out.println("exit");
        } else {
            System.err.println("Unknown command: " + parts[0]);
        }
    }

    private void handleListInput(String[] args){
        if(args.length < 2){
            System.err.println("Missing arguments for 'list'");
            System.err.println("Usage: list <groups|students|memberships>");
        } else {
            String object = args[1];
            if(object.equalsIgnoreCase("groups")){
                System.out.println("All groups:");
                for(Group g: storage.getAllGroups()){
                    System.out.println("--- " + g.toString());
                }

            } else if(object.equalsIgnoreCase("students")){
                System.out.println("All students:");
                for(Student s: storage.getAllStudents()){
                    System.out.println("--- " + s.toString());
                }
            } else if(object.equalsIgnoreCase("memberships")){
                if(args.length < 3){
                    System.err.println("Missing argument 'student-id' for 'list memberships'");
                    System.err.println("Usage: list memberships <student-id>");
                    return;
                }
                try {
                    int studentId = Integer.parseInt(args[2]);
                    Student s = storage.getStudent(studentId);
                    if(s != null){
                        System.out.println("All groups for " + s.toString() + ":");
                        for(Group g : storage.getAllGroupsFor(s)){
                            System.out.println("--- " + g.toString());
                        }
                    } else {
                        System.out.println("Student with id " + studentId + " not found.");
                    }

                } catch(NumberFormatException nfx){
                    System.err.println("Provided argument 'student-id' is not a number: " + args[2]);
                }
            } else {
                System.err.println("Invalid argument for 'list': '"+object+"' is not a valid object");
                System.err.println("Usage: list <groups|students|memberships>");
            }
        }
    }

    private void handleAddInput(String[] args){
        if(args.length < 2){
            System.err.println("Missing arguments for 'add'");
            System.err.println("Usage: add <group|student|membership>");
        } else {
            String object = args[1];
            if(object.equalsIgnoreCase("group")){
                if(args.length < 4){
                    System.err.println("Missing arguments for 'add group'");
                    System.err.println("Usage: add group <group-name> <lecture-name>");
                    return;
                }

                if(storage.hasGroup(args[2])){
                    System.err.println("Group name '" + args[2] + "' already taken");
                    return;
                }

                Group newGroup = new Group(args[2], args[3]);

                storage.storeGroup(newGroup);

                System.out.println("Successfully stored new group: " + newGroup);

            } else if(object.equalsIgnoreCase("student")){
                if(args.length < 3){
                    System.err.println("Missing arguments for 'add student'");
                    System.err.println("Usage: add group <name>");
                    return;
                }

                int idCnt = 0;
                while(storage.getStudent(idCnt) != null) { idCnt ++; }

                Student newStudent = new Student(idCnt, args[2]);

                storage.storeStudent(newStudent);

                System.out.println("Successfully stored new student: " + newStudent);
            } else if(object.equalsIgnoreCase("membership")){
                if(args.length < 4){
                    System.err.println("Missing arguments for 'add membership'");
                    System.err.println("Usage: add membership <student-id> <group-name>");
                    return;
                }
                try {
                    int studentId = Integer.parseInt(args[2]);
                    String groupName = args[3];

                    //TODO: Implement this
                    System.err.println("Not implemented!");

                } catch(NumberFormatException nfx){
                    System.err.println("Provided argument 'student-id' is not a number: " + args[2]);
                }
            } else {
                System.err.println("Invalid argument for 'add': '"+object+"' is not a valid object");
                System.err.println("Usage: add <group|student|membership>");
            }
        }
    }

}
