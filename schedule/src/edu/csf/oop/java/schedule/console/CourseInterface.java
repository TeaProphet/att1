package edu.csf.oop.java.schedule.console;

import edu.csf.oop.java.schedule.logic.CourseSchedule;
import edu.csf.oop.java.schedule.logic.GroupSchedule;
import edu.csf.oop.java.schedule.logic.exceptions.SubjectNotFound;

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
                         2. Edit group schedule
                         3. Clear schedule for a group
                         4. Clear all groups schedules
                         5. Print number of groups with number of students
                         6. Leave""");
                System.out.println(line);

                Scanner scanner = new Scanner(System.in);
                int chosen = scanner.nextInt();
                switch (chosen) {
                    case 1 -> {
                        System.out.print("Enter group number: ");
                        int groupNum = scanner.nextInt();
                        courseSchedule.addGroup(groupNum);
                        System.out.printf("SUCCESS: Schedule for group %d has been created\n",
                                groupNum);
                    }
                    case 2 -> {
                        System.out.print("\nEnter number of group: ");
                        int groupNum = scanner.nextInt();
                        GroupInterface.start(courseSchedule.getScheduleForGroup(groupNum));
                    }
                    case 3 -> {
                        System.out.print("\nEnter number of group: ");
                        int groupNum = scanner.nextInt();
                        courseSchedule.clearGroupSchedule(groupNum);
                        System.out.printf("SUCCESS: Schedule for group %d has been cleared\n", groupNum);
                    }
                    case 4 -> {
                        courseSchedule.clearCourseSchedule();
                        System.out.println("SUCCESS: Schedule for all groups of course has been cleared");
                    }
                    case 5 -> {
                        for (GroupSchedule groupSchedule:
                             courseSchedule.getGroupScheduleList()) {
                            System.out.printf("Group number %d, number of students: %d\n", groupSchedule.getGroupNumber(),
                                    groupSchedule.getNumberOfStudents());
                        }
                    }
                    case 6 -> {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
