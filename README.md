# study-planner-design-patterns

# Study Planner — Object-Oriented Design Patterns

A Java-based Study Planner and Assignment Tracker demonstrating the practical application of four object-oriented design patterns.

The application allows users to organise university work into projects and tasks, assign priorities and deadlines, track completion status, and view overall progress through a graphical user interface.

The project was developed to demonstrate how design patterns can improve the flexibility, maintainability and scalability of an object-oriented application.

## Overview

The Study Planner organises work using a hierarchical structure of projects and tasks.

Users can:

- Create projects
- Create tasks within projects
- Delete projects and tasks
- Set task priorities
- Set task deadlines
- Set estimated completion hours
- Mark tasks as completed
- View detailed information about selected tasks
- Monitor total estimated hours
- Monitor total and completed task counts
- See the interface automatically update when the underlying data changes

## Graphical User Interface

The application provides a Java GUI consisting of several main components:

### Project and Task Tree

A hierarchical tree displays projects and their associated tasks.

```text
Study Planner
│
├── OODP Assignment
│   ├── Report
│   ├── UML Diagrams
│   └── Java Code
│
├── Networking
│   └── IPv6 Lab
│
└── Rich Web Apps
    └── Database Setup

Task Details

Selecting a task displays its available information, including properties such as:

Description
Priority
Due date
Estimated hours
Category
Completion status
Toolbar

The toolbar provides controls for modifying the planner, including adding and deleting projects and tasks and marking tasks as completed.

Status Panel

The status panel provides an overview of the planner, including:

Total estimated hours
Total number of tasks
Number of completed tasks
Design Patterns

Four design patterns were implemented in the application.

1. Builder Pattern

Category: Creational

The Builder pattern is used to construct Task objects.

Tasks contain multiple properties including:

Title
Description
Due date
Priority
Estimated hours
Category
Completion status

Instead of requiring a large constructor containing many parameters, the Builder allows tasks to be constructed incrementally.


The Builder pattern was chosen over a traditional constructor because it provides clearer object creation and makes optional task properties easier to manage.

2. Composite Pattern

Category: Structural

The Composite pattern is used to represent the hierarchical relationship between projects and tasks.

The main components are:

PlannerComponent — common component abstraction
TaskLeaf — represents an individual task
ProjectComposite — represents a project that can contain multiple components

This allows individual tasks and projects to be treated uniformly.

The Composite pattern was chosen because the planner naturally represents a tree structure in which projects contain tasks.

3. Observer Pattern

Category: Behavioural

The Observer pattern is used to keep the graphical interface synchronized with the planner's data.

The PlannerModel acts as the subject, while components such as TaskTreePanel and StatusPanel act as observers.

When the underlying model changes, observers are notified and update their displays.

This means the interface does not need to manually refresh every component after each operation.

The Observer pattern was chosen to separate the application's data model from its user interface and ensure that changes are automatically reflected throughout the application.

4. Decorator Pattern

Category: Structural

The Decorator pattern is used to dynamically modify the visual representation of tasks.

The main components include:

TaskView — base component interface
BasicTaskView — default implementation
TaskViewDecorator — decorator base
HighPriorityDecorator — adds high-priority visual behaviour
CompletedDecorator — adds completed-task visual behaviour
OverdueDecorator — adds overdue-task visual behaviour

Decorators can wrap the base task view and add additional behaviour without modifying the original class.

This approach allows multiple visual features to be combined without creating a separate subclass for every possible combination.

Technologies
Java
Object-Oriented Programming
Design Patterns
Java GUI development
UML
Composite data structures
Observer-based UI updates

Documentation

The docs directory contains the full project report, including:

Application overview
Functional requirements
User interface description
Design pattern explanations
UML diagrams
Application screenshots
Design decisions and pattern justifications
Academic Project

This project was developed as part of the Object-Oriented Design Patterns module.

The assignment required the implementation of at least four design patterns, including at least one creational, structural and behavioural pattern, together with a comprehensive graphical user interface and supporting UML documentation.
