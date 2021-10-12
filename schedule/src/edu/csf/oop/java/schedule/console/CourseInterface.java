package edu.csf.oop.java.schedule.console;

import edu.csf.oop.java.schedule.logic.CourseSchedule;
import edu.csf.oop.java.schedule.logic.GroupSchedule;

import java.util.Scanner;

public class CourseInterface {
    private static final String line = "==========================================================================";
    protected static void start(CourseSchedule courseSchedule){
        while (true){
            try {
                System.out.println(line);
                System.out.println("""
                        Please select function of schedule by entering num of function:
                         1. Add schedule for a group
                         2. Remove schedule for a group
                         3. Edit group schedule
                         4. Clear schedule for a group
                         5. Clear all groups schedules
                         6. Print number of groups with number of students
                         7. Leave""");
                System.out.println(line);

                Scanner scanner = new Scanner(System.in);
                int chosen = scanner.nextInt();
                switch (chosen) {
                    case 1 -> {
                        System.out.print("Enter group number: ");
                        int groupNum = scanner.nextInt();
                        courseSchedule.addGroup(groupNum);
                    }
                    case 2 -> {
                        System.out.print("Enter group number: ");
                        int groupNum = scanner.nextInt();
                        courseSchedule.removeGroup(groupNum);
                    }
                    case 3 -> {
                        System.out.print("Enter number of group: ");
                        int groupNum = scanner.nextInt();
                        GroupInterface.start(courseSchedule.getScheduleForGroup(groupNum));
                    }
                    case 4 -> {
                        System.out.print("Enter number of group: ");
                        int groupNum = scanner.nextInt();
                        courseSchedule.clearGroupSchedule(groupNum);
                    }
                    case 5 -> courseSchedule.clearCourseSchedule();
                    case 6 -> {
                        for (GroupSchedule groupSchedule:
                             courseSchedule.getGroupScheduleList()) {
                            System.out.printf("Group number %d, number of students: %d\n", groupSchedule.getGroupNumber(),
                                    groupSchedule.getNumberOfStudents());
                        }
                    }
                    case 7 -> {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
