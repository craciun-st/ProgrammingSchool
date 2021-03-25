package com.codecool.programmingschool;

import com.codecool.programmingschool.factories.PersonFactory;

import java.util.*;
import java.util.stream.Collectors;

public class School {
    public final double BASE_SALARY = 1234.5;
    private Set<Staff> employees;
    private Set<Student> students;

    public School() {
        this.employees = new HashSet<>();
        this.students = new HashSet<>();
    }

    public void addStudent(Student someStudent) {
        if (someStudent != null) {
            students.add(someStudent);
        }
    }

    public boolean removeStudent(Student someStudent) {
        if (someStudent != null) {
            return students.remove(someStudent);
        } else {
            return false;
        }
    }

    public void hireEmployee(Staff someEmployee) {
        if (someEmployee != null) {
            if (someEmployee.getSalary() == null) {
                double newSalary = decideSalaryForNewEmployee(someEmployee);
                someEmployee.setSalary(newSalary);
            }
            employees.add(someEmployee);
        }
    }

    public double decideSalaryForNewEmployee(Staff someEmployee) {

        // could have more complex logic here based on fields
        // of specific subclasses (Mentors, SalesPersons, etc...)

        return BASE_SALARY;
    }

    public Set<Mentor> getMentors() {
        Set<Mentor> mentors = new HashSet<>();
        for (Staff employee : employees) {
            if (employee instanceof Mentor) {
                Mentor currentMentor = (Mentor) employee;
                mentors.add(currentMentor);
            }
        }

        return mentors;
    }

    public Set<Student> getStudentsOnJobHunt() {
        Set<Student> doneStudents;
        doneStudents = students.stream()
                .filter(Student::isOnJobHunt)
                .collect(Collectors.toSet());
        return doneStudents;
    }

    public Set<Student> getStudents() {
        return Set.copyOf(students);
    }

    public Set<SalesPerson> getSalesPersons() {
        Set<SalesPerson> salesPeople = new HashSet<>();
        for (Staff employee : employees) {
            if (employee instanceof SalesPerson) {
                SalesPerson currentSalesPerson = (SalesPerson) employee;
                salesPeople.add(currentSalesPerson);
            }
        }

        return salesPeople;
    }

    public void listStudentsInModule(Module someModule) {
        Comparator<Student> progressComparator = Comparator
                .comparing(Student::getProgress);

        System.out.println("Students in "+someModule.toString()+": ");
        students.stream()
                .filter(student -> student.getCurrentModule().equals(someModule))
                .sorted(progressComparator)
                .map(Student::toShortenedString)
                .forEach(System.out::println);

        System.out.println();   // empty row separator

    }

    public void listMentorsInModule(Module someModule) {
        Set<Mentor> mentors = this.getMentors();

        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Mentors assigned to "+someModule.toString()+": ");
        mentors.stream()
                .filter(mentor -> (mentor.getModule() != null))
                .filter(mentor -> mentor.getModule().equals(someModule))
                .map(Mentor::toShortenedString)
                .forEach(string -> stringBuilder.append(string).append("; "));

        System.out.println(stringBuilder.toString()+System.lineSeparator());

    }

    public Map<Module, Integer> getNrStudentsPerModule() {
        Map<Module, Integer> resultMap = new HashMap<>();
        int currentNrStudents;
        EnumSet<Module> allModules = EnumSet.allOf(Module.class);
        for (Module module : allModules) {
            currentNrStudents = Math.toIntExact(students.stream()
                    .filter(student -> student.getCurrentModule().equals(module))
                    .count());
            resultMap.put(module, currentNrStudents);
        }
        return resultMap;
    }

    public Map<Module, Integer> getNrMentorsPerModule() {
        Map<Module, Integer> resultMap = new HashMap<>();
        int currentNrMentors;
        Set<Mentor> mentors = this.getMentors();
        EnumSet<Module> allModules = EnumSet.allOf(Module.class);
        for (Module module : allModules) {
            currentNrMentors = Math.toIntExact(mentors.stream()
                    .filter(mentor -> (mentor.getModule() != null))
                    .filter(mentor -> mentor.getModule().equals(module))
                    .count());
            resultMap.put(module, currentNrMentors);
        }
        return resultMap;
    }

    public void reassignMentors() {
        // the basic logic for assignment is done in this order:
        // 1. try to have one (1) mentor for each module
        // 2. loop over remaining mentors and assign for modules sorted by number of students
        //  2a. until there is at least 1 mentor for 5 students
        //  2b. or there are no more mentors left to assign for this module
        // 3. assign any remaining mentors to the first possible among the sorted modules

        Set<Mentor> allMentors = this.getMentors();
        Set<Mentor> remainingMentors = new HashSet<>(Set.copyOf(allMentors));
        Map<Module, Integer> nrStudentsPerModule = this.getNrStudentsPerModule();
        Map<Module, Integer> nrMentorsPerModule = new HashMap<>();
        for (Module module : nrStudentsPerModule.keySet()) {
            nrMentorsPerModule.put(module, 0);
        }
        Module[] sortedModules = this.getModulesSortedByPriority();


        // Step 1.
        for (Module module : sortedModules) {
            for (Mentor mentor : allMentors) {
                if (remainingMentors.contains(mentor)) {
                    if (mentor.assignToModule(module)) {
                        nrMentorsPerModule.put(module, 1);
                        remainingMentors.remove(mentor);
                        break;
                    }
                }
            }
        }

        // Step 2.
        for (Module module : sortedModules) {
            for (Mentor mentor : allMentors) {

                int currentMentorsForThisModule = nrMentorsPerModule.get(module);
                int idealNrMentorsForThisModule = (nrStudentsPerModule.get(module) / 5) + 1;  // 2a.

                if (currentMentorsForThisModule < idealNrMentorsForThisModule) {
                    if (remainingMentors.contains(mentor)) {
                        if (mentor.assignToModule(module)) {
                            nrMentorsPerModule.put(module, currentMentorsForThisModule+1);
                            remainingMentors.remove(mentor);
                        }
                    }
                }
            }

        }

        // Step 3.
        if (remainingMentors.size() > 0) {
            for (Mentor mentor : remainingMentors) {
                for (Module module : sortedModules) {
                    int currentMentorsForThisModule = nrMentorsPerModule.get(module);
                    if (mentor.assignToModule(module)) {
                        nrMentorsPerModule.put(module, currentMentorsForThisModule + 1);
                        break;
                    }
                }
            }
        }

    }

    public Module[] getModulesSortedByPriority() {
        Map<Module, Integer> nrStudentsPerModule = this.getNrStudentsPerModule();
        List<Module> sortedModules;
        sortedModules = nrStudentsPerModule.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())   // sort <Key, Value> pairs in ASC order by comparing values
                .map(entry -> entry.getKey())  // map <Key, Value> pairs to Keys (here, Module enum types)
                .collect(Collectors.toList());
        Collections.reverse(sortedModules);     // reversing gives us DESC order

        return sortedModules.toArray(Module[]::new);
    }

    /**
     * Updates the sets of employees and students dynamically, as a function of time. Uses methods from
     * PersonFactory to create new Persons. Calls update on each Person.
     * The logic is as follows:
     * 1 SalesPerson is hired every 10 units of time if less than ideal nr (1 SalesPerson per 25 students)
     * 1 Mentor is hired every 2 units of time if less than ideal nr (1 Mentor per 5 students in a Module)
     * Priority is given to modules with more students in them at a given time (Hired mentors know at least
     * the language required for said module)
     * 0-7 (number is drawn uniformly) students are joining the School every 1 unit of time.
     *
     * Mentors are reassigned every 3 units of time
     * Persons are updated every 1 unit of time
     * Everybody gets to keep their job :)
     * Students that are done (onJobHunt = true) are removed every 3 units of time
     * (offset of 1 time-unit relative to mentor reassignment)
     *
     * @param time an integer value serving as a counter for the units of time
     */
    public void update(int time) {
        // New students join every unit of time
        int nrNewStudents = new Random().nextInt(8);
        for (int i = 0; i < nrNewStudents; i++) {
            this.addStudent(PersonFactory.createRandomStudent());
        }

        // Finished students leave every 3 units of time (offset of 1)
        final int OFFSET = 1;
        if (time % 3 == OFFSET) {
            for (Student student : this.getStudentsOnJobHunt()) {
                this.removeStudent(student);
            }
        }
        // Hire SalesPerson
        if (time % 10 == 0) {
            int idealNrSalesPersons = (students.size() / 25) + 1;
            if (this.getSalesPersons().size() < idealNrSalesPersons) {
                this.hireEmployee(PersonFactory.createRandomSalesPerson());
            }
        }

        // Hire Mentor for top-priority module every 2 units of time
        if (time % 2 == 0) {
           Module[] sortedModules = this.getModulesSortedByPriority();
            Map<Module, Integer> nrStudentsPerModule = this.getNrStudentsPerModule();
            Map<Module, Integer> nrMentorsPerModule = this.getNrMentorsPerModule();
            for (Module module : sortedModules) {
                int idealNrMentorsForThisModule = (nrStudentsPerModule.get(module) / 5) + 1;
                int nrMentorsInThisModule = nrMentorsPerModule.get(module);
                if (nrMentorsInThisModule < idealNrMentorsForThisModule) {
                    ProgrammingLanguage neededLanguage = module.language;
                    Mentor newTeacher = PersonFactory.createRandomMentorWhichKnowsLanguage(
                            neededLanguage
                    );
                    this.hireEmployee(newTeacher);
                    break;  // no need to hire Mentors for remaining modules
                }
            }
        }

        // Reassign Mentors every 3 units of time
        if (time % 3 == 0) {
            this.reassignMentors();
        }

        // Update Persons (employees + students) every unit of time
        for (Staff employee : employees) {
            employee.update();
        }
        for (Student student : students) {
            student.update();
        }


    }

}
