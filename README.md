# Q&Ai

---

The project is split into four folders

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
6. Clone the master branch with `git clone https://github.com/Cyberdog52/hackathon.git`
7. (optional) Open the project with IntelliJ and let gradle build the project. Skip this step if you don't intend to work with java during the hackathon.
8. (optional) If prompted, install java (17.0.5). Skip this step if you don't intend to work with java during the hackathon.
9. Install the npm dependencies with `cd angular-frontend` followed by `npm install`
10. Install the npm dependencies with `cd react-frontend` followed by `npm install`

During the hackathon your team can decide to use a custom branch on this GitHub repository or to fork it to your own
private repository.

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
