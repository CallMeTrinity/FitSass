# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

FitSass (formerly SarcasNess) is a Java-based fitness workout plan generator that creates personalized workout routines based on user preferences, fitness goals, experience level, and physical limitations. The application generates structured workout plans with exercise selections tailored to individual needs.

## Build & Development Commands

### Building the Project
```bash
mvn compile
```

### Running the Console Application
```bash
mvn exec:java -Dexec.mainClass="com.fitsass.App"
```
Or after compilation:
```bash
java -cp target/classes com.fitsass.App
```

### Running the REST API
```bash
mvn spring-boot:run
```
Or after building:
```bash
mvn clean package
java -jar target/SarcasNess-v0.4.0-alpha.jar
```

The API will start on `http://localhost:8080`

### Running Tests
Run all tests:
```bash
mvn test
```

Run a specific test class:
```bash
mvn test -Dtest=WorkoutPlanTest
```

Run a specific test method:
```bash
mvn test -Dtest=WorkoutPlanTest#testGenerateCustomSplit
```

### Cleaning Build Artifacts
```bash
mvn clean
```

## Architecture

### Core Flow
1. **App.java** - Entry point that instantiates FitnessService
2. **FitnessService** - Orchestrates the workflow: collects user input via UserPreference, creates WorkoutPlan, and generates the plan
3. **UserPreference** - Interactive scanner-based questionnaire collecting user data (name, experience, goals, frequency, workout type, limitations, physical stats)
4. **WorkoutPlan** - Main generation logic that:
   - Selects or generates a Split based on frequency and workout type
   - Creates WorkoutSessions for each day
   - Populates sessions with appropriate exercises
5. **WorkoutSession** - Represents a single day's workout with exercises filtered by muscle groups, difficulty, limitations, and workout type

### Data Loading System
- **ExerciseLoader** - Loads exercises from `src/main/resources/exercises.json` using Gson
- **SplitLoader** - Loads workout split templates from `src/main/resources/split.json`
- Both loaders use Gson's TypeToken for generic type deserialization

### Key Models
- **Exercise** - Contains exercise metadata (name, difficulty, muscle specificity, workout type, limitations, importance ranking)
- **Split** - Defines muscle group distribution across workout days (e.g., Push-Pull-Legs, Upper-Lower)
- **WorkoutSession** - Generates exercise selection algorithm that:
  - Groups exercises by muscle specificity
  - Filters by difficulty level (derived from user experience)
  - Excludes exercises conflicting with user's physical limitations
  - Sorts by importance and shuffles within each muscle category
  - Distributes exercises evenly across target muscles

### Enums
All enums are located in `com.fitsass.enums`:
- **FitnessGoal** - LOSE_WEIGHT, GAIN_MUSCLE, MAINGAIN, ENDURANCE, RUN_FASTER, RUN_LONGER, FLEXIBILITY
- **WorkoutType** - WEIGHTLIFTING, CARDIO, CALISTHENICS, etc.
- **MuscleGroup** - CHEST, BACK, LEGS, SHOULDERS, ARMS, CORE, FULL_BODY (each contains constituent Muscle enums)
- **Muscle** - Specific muscle targeting (QUADS, LATS, UPPER_CHEST, etc.)
- **PhysicalLimitations** - KNEE_PAIN, SHOULDER_ISSUES, LOWER_BACK_PAIN, etc.

## Important Implementation Details

### Exercise Selection Algorithm
The WorkoutSession.generateSession() method implements a sophisticated selection algorithm:
1. Maps exercises to specific muscles within each target muscle group
2. Filters by difficulty level (0=Beginner, 1=Intermediate, 2=Advanced)
3. Excludes exercises that conflict with user's physical limitations
4. For FULL_BODY workouts: selects one exercise per muscle, sorted by importance
5. For split workouts: cycles through muscles to evenly distribute exercises, respecting the target count per muscle group

### Custom Split Generation
When no predefined split matches the user's frequency and workout type, WorkoutPlan.generateCustomSplit() creates a custom FULL_BODY split for all workout days.

### Sets/Reps/Intensity Logic
Exercise parameters are dynamically assigned in WorkoutSession.generateSession() based on FitnessGoal:
- LOSE_WEIGHT: 12 reps, 3 sets, 0.6 intensity
- GAIN_MUSCLE: 8 reps, 4 sets, 0.8 intensity
- MAINGAIN: 10 reps, 3 sets, 0.7 intensity
- ENDURANCE: 15 reps, 2 sets, 0.5 intensity
- RUN_FASTER/RUN_LONGER: 20 reps, 2 sets, 0.4 intensity
- FLEXIBILITY: 12 reps, 2 sets, 0.5 intensity

### User Input Validation
UserPreference uses helper methods (getValidIntInput, getValidDoubleInput, etc.) that loop until valid input is received. Physical limitations can be selected as comma-separated indices.

## Resource Files

### exercises.json
JSON array of exercise objects with fields: name, difficulty (0-2), type (WorkoutType enum), mainMuscleGroup, specificity (Muscle enum), importance (lower = higher priority), limitations (array of PhysicalLimitations).

### split.json
JSON array of split configurations with fields: name, description, days (workout frequency), workoutType, muscleGroupsPerDay (map of day number to MuscleGroup list).

## Testing Approach

Tests use JUnit 5 with ByteArrayInputStream to simulate user input via Scanner. See WorkoutPlanTest for the pattern:
```java
String simulatedInput = "John\n2\n1\n2\n0\n5\n75\n1.80\n1\n";
ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
Scanner scanner = new Scanner(in);
```

Test classes mirror the models directory structure in `src/test/java/com/fitsass/models/`.

## REST API

The project includes a Spring Boot REST API alongside the console application.

### API Endpoints

#### Generate Workout Plan
**POST** `/api/workout-plans`

Request body:
```json
{
  "name": "John Doe",
  "experienceLevel": 2,
  "weeklyWorkoutFrequency": 4,
  "goal": "GAIN_MUSCLE",
  "exercisePreference": "WEIGHTLIFTING",
  "physicalLimitations": ["KNEE_PAIN"],
  "currentWeight": 75,
  "height": 1.80
}
```

Response (201 Created):
```json
{
  "id": 1,
  "userName": "John Doe",
  "weeklyWorkoutFrequency": 4,
  "splitName": "Push-Pull-Legs-Upper",
  "bmi": 23.15,
  "sessions": [
    {
      "name": "Day 1",
      "muscleGroups": ["CHEST", "SHOULDERS"],
      "exercises": [...]
    }
  ]
}
```

#### Get Workout Plan by ID
**GET** `/api/workout-plans/{id}`

Response: WorkoutPlanResponseDTO or 404 if not found

#### Get All Workout Plans
**GET** `/api/workout-plans`

Response: Array of WorkoutPlanResponseDTO

#### Get User's Workout Plans
**GET** `/api/workout-plans/user/{userName}`

Response: Array of WorkoutPlanResponseDTO ordered by creation date (newest first)

### Database

The API uses H2 in-memory database (persisted to file at `./data/fitsass`). Access the H2 console at `http://localhost:8080/h2-console` with:
- JDBC URL: `jdbc:h2:file:./data/fitsass`
- Username: `sa`
- Password: (empty)

### Architecture - API Layer

The API follows a layered architecture:

1. **Controller Layer** (`com.fitsass.controller`):
   - `WorkoutPlanController` - REST endpoints with validation

2. **Service Layer** (`com.fitsass.services`):
   - `WorkoutPlanService` - Business logic orchestration
   - Converts between DTOs, domain models, and entities
   - Uses existing workout generation logic (WorkoutPlan, WorkoutSession)

3. **Repository Layer** (`com.fitsass.repository`):
   - `WorkoutPlanRepository` - Spring Data JPA repository
   - Custom query: `findByIdWithSessions` for eager loading

4. **Entity Layer** (`com.fitsass.entity`):
   - `WorkoutPlanEntity` - Persisted workout plan
   - `WorkoutSessionEntity` - Persisted workout session
   - `ExerciseEntity` - Persisted exercise

5. **DTO Layer** (`com.fitsass.dto`):
   - `WorkoutPlanRequestDTO` - API request with validation annotations
   - `WorkoutPlanResponseDTO` - API response
   - `WorkoutSessionDTO`, `ExerciseDTO` - Nested response DTOs

### Separation of Concerns

- `UserPreferenceData` - Pure business logic (BMI calculation, exercise count calculation) without UI dependencies
- `UserPreference` - Console UI with Scanner input, can convert to UserPreferenceData via `toUserPreferenceData()`
- Both console and API use the same core workout generation logic

## Java Version
Project targets Java 22 (configured in pom.xml maven-compiler-plugin).
