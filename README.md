# ProgrammingSchool

Let's model how a programming school works, that's always fun, right? :)
In this programming school students should complete four modules (Programming Basics, Web, OOP, Advanced in this order). After they've finished the Advanced module, they finish the school.
Mentors teach in the modules. For every module a type of knowledge is required for a mentor to be able to teach that module:
``
Programming Basics -> Python language
Web module -> Web Languages knowledge
OOP module -> Java SE
Advanced module -> Java EE
``
Sales Persons are also working for the school. They offer jobs for the students when they finish school.

We would like to store some information about the students and mentors. We would like to know the name, birth date and phone number of everyone. 
We need to store which module the student is currently in and his/her progression in that module. Progression is a number (0..100) where 100 means the module is completed. When a module is completed, he/she advances to the next module. When he/she finished the Advanced module, he/she finished the school.
We need to store which languages a mentor knows from the ones described above. Also we would like to store which module a mentor is currently teaching. If a mentor doesn't know a language required for a module he/she can't teach in that module so we can't assign that mentor to that module.
We should store the salary of Mentors and Sales people as well.

We would like to at least be able to hire mentors, accept students, assign a mentor to a module and know if it was unsuccessful and list all the students who are in a specific module.
