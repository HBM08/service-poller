# service-poller
The application gives you the possibility to run a set of CRUD operations over service reference domain model and also emits service status SSE to the UI client.

## How to run it
1. First and foremost clone the repo :)
2. Open a terminal window in the **service-poller-server** folder.
3. Start the server by running the following command: `./gradlew bootRun`
4. Open a new terminal window in the **service-poller-client** folder.
5. Start the UI client (here we need to install the app and then run the development server) with following commands:
```
npm i
npm start
```
6. When the application is started a new browser tab should be open in your default browser; if that is not the case then just open a browser and go to [http://localhost:3000/]
7. Congrats your app is up and ready!!!  
