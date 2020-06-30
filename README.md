# Sensyne Health
Code test for Sensyne Health


# Some considerations

### Offline support
To provide offline support we use the app local storage as data source. When the user enter in a section we download all the necessary data to the database and then we read this data from there. Also this could be used as cache in the future to reduce the network traffic.

### Experimental coroutines
As this is a test app I used some experimental coroutines.

### Tests
For now the android test need a real server to run. In the future we could add a mock for the api service or a mock server.

# Run the app

To run the app you can follow this steps: [How to run an Android app](https://github.com/MicrosoftDocs/windows-uwp/blob/bac28c894585e874d8dc4544e548f0c3478aa856/hub/android/emulator.md)