# AndroidCoreLibrary

Android Core Library (_AC Library_) supports your application development with multiple features with less number of lines of code.

## Installation

We have deployed the versions of _AC Library_ to the public via [JitPack]("https://jitpack.io/"). Jitpack is an easy to use package repository. Follow the below steps to experience the feature in the package.

### build.gradle : Project Level

In your project-level Gradle file, add the following snippet.

```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### build.gradle : App level

In your app-level Gradle file, add the following snippet.

```gradle
dependencies {
        implementation 'com.github.britsa:AndroidCoreLibrary:1.1'
}
```

## Usage

### REST calls

REST based calls are very important when the code needs to be connected with exposed services. Implementing REST calls are tedious in Android development. This tedious process is avoided by using _AC Library_'s **RESTVolleyClass.java**. This class provides the following methods to make REST calls in different scenarios.

>Query Parameters and Path parameters of a request are not supported among the above as method arguments. Currently, these values are requested to be passed in the URL.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## Author

***Maria Irudaya Regilan J*** ([@Regilan]("https://github.com/Regilan")) developed this code with public interest. Mail him for any feedback or suggestion at **britsa.tech@gmail.com** *(Include his name in the subject of the mail)*.

## License
[MIT](https://choosealicense.com/licenses/mit/)
