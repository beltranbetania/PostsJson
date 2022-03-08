# Post Json
## About
On this repository you will find a Project with Clean Architecture, MVVM, Retrofit, Coroutines, Dagger Hilt, which:
* Show a list of all posts and their details (user & comments) from JSONPlaceholder. 
* Allows save posts as favorite
* Show a list of all favorite posts 
* Allows delete all post from the list, or just one with swipe gesture on item
* Implement unit testing for a use case

### Requirements
* Android Studio 2 v3.0.1 or higher
* Java 8
* Gradle v4.1.3

## Arquitecture
This project implements MVVM with Clean Architecture using Retrofit and Coroutines, Dagger Hilt Dependency Injection, Data Persistence with ROOM.
Implementing MVVM with Room and LiveData provides several advantages such as:
* Allows separation of user interface logic from business logic.
* Helps keep UI code simple
* The UI will always display the most up-to-date data. LiveData is still a vitaminized Observable.
* No  bugs or defensive code to avoid idle activities/fragments. 
* Avoid memory leaks. Observers are associated with a lifecycle object.


