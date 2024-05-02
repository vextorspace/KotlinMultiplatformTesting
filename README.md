This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…


## Necessary preconditions to run the project in intellij
1. Install the android sdk command line tools
2. Install the android sdk
3. accept the licenses
4. create a local.properties file in the root of the project and add the following line:
```sdk.dir=/path/to/your/android/sdk``` or
```sdk.dir=C\:\\Users\\username\\AppData\\Local\\Android\\Sdk```
5. rebuild the project

## Running the androidInstrumentedTests
1. edit configurations
2. add a new configuration
3. select Android Instrumented Tests
4. select the module ...composeApp.androidTest
5. run tests. Now right-click on the test folder and select run tests should also work.