Reflection API

Github link : https://github.com/sinderpl/reflectionAPI

A Java Reflection API based application allowing for the inspection of JAR files
and the included classes for their stability. 
Stability refers to the amount of classes that are referenced by a class and the amount of classes that the class is pointing to. The stability value is represented as a float value between 0.0 and 1.0 .

Instructions:
1. Open the project in Eclipse
2. Run the ReflectionRunner class.
3. Click the "Find JAR" button.
4. In the newly open file explorer find the JAR you wish to open.
5. Await for your results to appear onscreen
6. Keep an eye on the status bar, if you choose a file that is not a JAR it will notify you.

Overview of classes: 
ReflectionRunner - A runner class for the whole application.

Controller - Updates the view from the model, handles the calls to the JAR reader.

ApplicationWindow - The GUI component, is instantiated and updated through the controller.

JarReader - Reads in the specified JAR and calls on the calculating class.

ReflectionMetricCalculator - Handles the calculations, dynamically loads in the classes through the Reflection API and examines them for the usage of any custom classes.

Metric  - Object to represent the the classes in the HashMap, allows for the storage of In and Out degrees as well as the calculation of stability.
