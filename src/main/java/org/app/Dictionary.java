package org.app;

import java.util.HashMap;


public class Dictionary {
    HashMap<String, String> dict;

    // Constructor for the Dictionary class, initializes the required variables
    public Dictionary(){
        dict = new HashMap<>();

        // key-value pairs for different topics
        dict.put("Python","Python \n" +
                "Week 1:\n" +
                "- Introduction to Python: Installing Python, Understanding the Python interpreter, Data Types, Variables, Operators, and Basic I/O operations\n" +
                "- Control Statements: If-else statements, loops, and break/continue statements\n" +
                "\n" +
                "Week 2:\n" +
                "- Functions: Writing and Calling Functions, Function arguments, Global vs Local variables, and Lambda functions\n" +
                "- Data Structures: Lists, Tuples, and Dictionaries\n" +
                "\n" +
                "Week 3:\n" +
                "- File Handling: Opening and Closing Files, Reading and Writing Files, and Error Handling\n" +
                "- Modules and Packages: Importing modules, Creating modules, and Using built-in modules\n" +
                "\n" +
                "Week 4:\n" +
                "- Object-Oriented Programming: Classes and Objects, Methods, Inheritance, Polymorphism, and Encapsulation\n" +
                "- Debugging: Debugging Techniques, PDB debugger, and Debugging tools\n" +
                "\n" +
                "Week 5:\n" +
                "- Regular Expressions: Syntax, Special characters, Pattern matching, and Searching and replacing\n" +
                "- Web Scraping: Introduction to web scraping, Requests module, and BeautifulSoup library\n" +
                "\n" +
                "Week 6:\n" +
                "- GUI Programming: Introduction to GUI programming with Python, Tkinter library, and building a simple GUI application\n" +
                "- Final Project: Creating a small project with the skills learned in the previous weeks.\n");
        dict.put("Java","Java \n" +
                        "Week 1:\n" +
                        "- Introduction to Java: Installing Java, Java Development Kit, Java Virtual Machine, Java runtime environment, Basic syntax, and variables\n" +
                        "- Control Statements: If-else statements, loops, and break/continue statements\n" +
                        "\n" +
                        "Week 2:\n" +
                        "- Object-Oriented Programming: Classes and Objects, Methods, Inheritance, Polymorphism, and Encapsulation\n" +
                        "- Data Structures: Arrays, Lists, and ArrayLists\n" +
                        "\n" +
                        "Week 3:\n" +
                        "- Exception Handling: try-catch block, finally block, throwing exceptions, and creating custom exceptions\n" +
                        "- File Handling: Reading and writing files, buffered reader, and scanner classes\n" +
                        "\n" +
                        "Week 4:\n" +
                        "- Multithreading: Thread class, Runnable interface, Synchronization, and Deadlocks\n" +
                        "- Networking: TCP/IP, UDP, Sockets, and URL handling\n" +
                        "\n" +
                        "Week 5:\n" +
                        "- JDBC: Introduction to databases, JDBC architecture, Connecting to databases, SQL queries and statements, and ResultSet class\n" +
                        "- Swing: Introduction to Swing, Components, Events, Layouts, and Swing utilities\n" +
                        "\n" +
                        "Week 6:\n" +
                        "- Web Applications: Servlets, JSP, HTML, and CSS\n" +
                        "- Final Project: Creating a small project with the skills learned in the previous weeks.\n"
                );
        dict.put("C language", "C language \n" +
                "Week 1:\n" +
                "- Introduction to C: History of C, Compilers, Data Types, Variables, Constants, Operators, and Basic I/O operations\n" +
                "- Control Statements: If-else statements, loops, and break/continue statements\n" +
                "\n" +
                "Week 2:\n" +
                "- Functions: Writing and Calling Functions, Function arguments, Global vs Local variables, and Recursion\n" +
                "- Arrays: Single-Dimensional Arrays, Multi-Dimensional Arrays, Strings\n" +
                "\n" +
                "Week 3:\n" +
                "- Pointers: Pointers and Addresses, Pointer Arithmetic, Arrays and Pointers, Pointers and Functions\n" +
                "- Structures: Structures and Functions, Arrays of Structures, and Pointers to Structures\n" +
                "\n" +
                "Week 4:\n" +
                "- File Handling: Opening and Closing Files, Reading and Writing Files, and Error Handling\n" +
                "- Memory Management: Dynamic Memory Allocation, malloc() and free() functions, and Memory Leaks\n" +
                "\n" +
                "Week 5:\n" +
                "- Preprocessor: #include, #define, #if, #ifdef, and #ifndef directives\n" +
                "- Advanced Topics: Enumerations, Bitwise Operators, Typedef, and Union\n" +
                "\n" +
                "Week 6:\n" +
                "- Final Project: Creating a small project with the skills learned in the previous weeks.\n");

    }

    public String getLearningPath(String word){
        return dict.get(word);
    }
}
