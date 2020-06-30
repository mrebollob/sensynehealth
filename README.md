# dhis
Code test for dhis


# Some considerations

### Offline support
To provide offline support we use the app local storage as data source. When the user enter in a section we download all the necessary data to the database and then we read this data from there. Also this could be used as cache in the future to reduce the network traffic.

### Apps and comments
The app comments are in a different entity outside the app data class. This will allow us to implement pagination easily some moment.

### Experimental coroutines
As this is a test app I used some experimental coroutines.

### Tests
For now the android test need a real server to run. In the future we could add a mock for the api service or a mock server.
