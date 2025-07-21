# MovieApp

MovieApp is an Android application built with Kotlin and Jetpack Compose that allows users to search for movies and view detailed information using the OMDb API.

---

## Features

* **Search Movies**: Look up movies by title.
* **Movie Details**: View poster, release year, genre, director, plot summary, and ratings.
* **Modern Architecture**:Clean Architecture with Hilt for dependency injection.
* **Networking**: Retrofit + OkHttp + Gson for API communication.
* **Jetpack Compose UI**: Material 3 components and theming.
* **Coroutines**: Asynchronous calls for smooth UI experience.

---

## Screenshots

![Search Screen](docs/screenshots/search.png)


---

## Getting Started

### Prerequisites

* Android Studio Arctic Fox or later
* Android SDK 24 or higher
* Java 11

### OMDb API Key

This project uses the [OMDb API](https://www.omdbapi.com/) for movie data. To obtain an API key:

1. Visit [OMDb API Key Request](https://www.omdbapi.com/apikey.aspx).
2. Fill out the form and submit.
3. Copy your unique API key.

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/<your-username>/MovieApp.git
   cd MovieApp
   ```

2. **Configure project properties**
   Create `gradle/config/config.properties` in the project root:

   ```properties
   applicationId=com.example.moviesapp
   minSdk=24
   targetSdk=35
   versionCode=1
   versionName=1.0.0
   ```

3. **Add your OMDb API key**
   In the `app` module, create a file `key.properties` (add to `.gitignore`):

   ```properties
  keyAlias=[Key]
  keyPassword=[password]
  storeFile=keystore/my-release-key.jks
  storePassword=yourStorePasswordHere

   ```

   Then reference it in `app/build.gradle.kts`:

   ```kotlin
   val keystoreProperties = Properties()
   val keystorePropertiesFile = rootProject.file("keystore/key.properties")
  if (keystorePropertiesFile.exists()) {
      keystoreProperties.load(keystorePropertiesFile.inputStream())
  }
   ```

4. **Sync and run**
   Open the project in Android Studio, let Gradle sync, then build and run on an emulator or device.

---

## Usage

1. Launch the app on your device or emulator.
2. Enter a movie title in the search bar and submit.
3. Tap any result to view detailed movie information.

---

## Architecture & Libraries

* **Language**: Kotlin
* **UI**: Jetpack Compose (Material 3)
* **DI**: Hilt
* **Networking**: Retrofit, OkHttp, Gson
* **Concurrency**: Kotlin Coroutines
* **Build**: Gradle Kotlin DSL

---

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for enhancements or bug fixes.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

* **Maintainer**: WANZILA RONNY
* **Email**: wanzilro@gmail.com(mailto:wanzilro@gmail.com)
* **Repo**: https://github.com/WANZILA/MoviesApp
