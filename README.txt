# StudyPal - Interactive Study Tool

## Important Note
- **Media File Paths**: Before running the application, please ensure to reinput the file paths of media files 
(audio clips) in the `StudyBuddy` class. Failure to do so may result in media playback issues.
 
-------------------------------------------------------------------------------------------------------------------------

## Overview
StudyPal is an interactive study tool designed to help CISC 230 students prepare for exams by presenting them with study 
questions and providing instant feedback on their answers. The application allows users to upload question files, answer 
questions, skip or delay questions, and receive a summary of their performance at the end of the session.

-------------------------------------------------------------------------------------------------------------------------

## Object-Oriented Concepts Applied
StudyPal employs several object-oriented concepts to structure and manage its functionality:

1. **Encapsulation**:
   - Each class encapsulates its functionality within its own scope, ensuring data and behavior are properly encapsulated 
   and accessible only as needed.

2. **Inheritance**:
   - The `Application` class is extended by the `StudyBuddy` class, demonstrating inheritance. This allows StudyBuddy to 
   inherit the basic functionality of a JavaFX application while adding its own specific features.

3. **Polymorphism**:
   - Polymorphism is exhibited through method overriding. For example, the `start` method in the `StudyBuddy` class 
   overrides the `start` method of the `Application` class to define the UI and behavior of the application.

4. **Abstraction**:
   - Abstraction is used to hide the complex implementation details of the application's components. Users interact with 
   a simplified interface while the underlying code handles the intricacies of question loading, processing, and feedback.

5. **Composition**:
   - Composition is utilized to create complex objects by combining simpler objects. For example, the `StudyBuddy` class
   contains various components such as buttons, text fields, and scroll panes, which are composed together to create the 
   user interface.

6. **Association**:
   - Association represents relationships between classes. For instance, the `StudyBuddy` class interacts with the 
   `Question` and `QuestionMaker` classes to load and process study questions from files.

7. **Dependency Injection**:
   - Dependency injection is employed to inject dependencies, such as event handlers, into the `StudyBuddy` class. 
   This allows for better modularity and testability of the code.

8. **Other Concept**:
   - FileChooser
   - ScrollPane
   - ScrollBarPolicy
   - PauseTransition
   - Duration

-------------------------------------------------------------------------------------------------------------------------

## Classes and Functionality

### StudyBuddy
- **Purpose**: Represents the main interactive study tool that helps users answer questions.
- **Functionality**:
  - Allows users to upload question files.
  - Presents study questions to the user one by one.
  - Accepts user input for answering questions and provides feedback.
  - Supports skipping or delaying or random questions.
  - Generates a summary of the user's performance at the end of the session.

### Question
- **Purpose**: Represents a study question.
- **Functionality**:
  - Stores the text of the question, possible answers, and correct answer.
  - Provides methods for checking if a user's answer is correct and retrieving the correct answer.

### QuestionTF
- **Purpose**: Represents a true/false type question.
- **Functionality**:
  - Inherits from the `Question` class.
  - Provides methods specific to true/false questions.

### QuestionMC
- **Purpose**: Represents a multiple-choice type question.
- **Functionality**:
  - Inherits from the `Question` class.
  - Provides methods specific to multiple-choice questions.

### QuestionFB
- **Purpose**: Represents a fill-in-the-blank type question.
- **Functionality**:
  - Inherits from the `Question` class.
  - Provides methods specific to fill-in-the-blank questions.

### QuestionMaker
- **Purpose**: Utility class for parsing question files and creating Question objects.
- **Functionality**:
  - Reads question files and extracts questions along with their answers.
  - Creates Question objects based on the parsed data.

-------------------------------------------------------------------------------------------------------------------------

## Contributors
- Anh Bui
- Emely Cortez Hernandez
- Uyen Thy Duong

-------------------------------------------------------------------------------------------------------------------------

## Number of Hours
We spent about 25 hours for this project.

-------------------------------------------------------------------------------------------------------------------------