# Q&Ai

---

The project is split into two folders

- java-backend
- react-frontend

## Setup

If your setup already has some of the required tools installed, please adjust accordingly.

1. Both frontends need Node to run. Install newest Node from here https://nodejs.org/en/ (currently 20.11.1 LTS)
2. Run `npm install -g npm@10.5.0`
3. Run `npm install -g @angular/cli@17.2.3`
4. Verify your versions with `ng version`:  
   Angular CLI: **17.2.3** \
   Node: **20.11.1** \
   Package Manager: npm **10.5.0**
5. Install a new version of IntelliJ or VSCode
6. Install the npm dependencies with `cd angular-frontend` followed by `npm install`
7. Install the npm dependencies with `cd react-frontend` followed by `npm install`

## Run

### Start java backend

1. Start the java backend either by
    1. By selecting the `Java Backend` run configuration and running it
    2. Or by executing `./gradlew java-backend:bootRun`

You should be able to open the Swagger endpoint documentation at `http://localhost:8080/swagger-ui/index.html`.

### Start React frontend

1. By selecting the `React Frontend` and running it
2. Or by executing `cd react-frontend` followed by `npm run dev`

You should now be able to open `http://localhost:5137` to access the frontend.
