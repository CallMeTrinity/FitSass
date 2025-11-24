# API Testing Guide

## Prerequisites

1. Install Maven if not already installed
2. Ensure Java 22 is installed and configured

## Starting the API

```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

## Testing the API

### 1. Generate a Workout Plan

**Request:**
```bash
curl -X POST http://localhost:8080/api/workout-plans \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "experienceLevel": 2,
    "weeklyWorkoutFrequency": 4,
    "goal": "GAIN_MUSCLE",
    "exercisePreference": "WEIGHTLIFTING",
    "physicalLimitations": ["KNEE_PAIN"],
    "currentWeight": 75,
    "height": 1.80
  }'
```

**Available Values:**
- `goal`: LOSE_WEIGHT, GAIN_MUSCLE, MAINGAIN, ENDURANCE, RUN_FASTER, RUN_LONGER, FLEXIBILITY
- `exercisePreference`: WEIGHTLIFTING, CARDIO, CALISTHENICS
- `physicalLimitations`: KNEE_PAIN, SHOULDER_ISSUES, LOWER_BACK_PAIN, ANKLE_INJURY, ELBOW_PAIN, WRIST_PAIN, HIP_PAIN, JOINT_ISSUES

### 2. Get a Specific Workout Plan

```bash
curl http://localhost:8080/api/workout-plans/1
```

### 3. Get All Workout Plans

```bash
curl http://localhost:8080/api/workout-plans
```

### 4. Get Workout Plans for a Specific User

```bash
curl http://localhost:8080/api/workout-plans/user/John%20Doe
```

## Using Postman

1. Import the following collection into Postman
2. Create a new request:
   - Method: POST
   - URL: `http://localhost:8080/api/workout-plans`
   - Headers: `Content-Type: application/json`
   - Body (raw JSON):
   ```json
   {
     "name": "Jane Smith",
     "experienceLevel": 5,
     "weeklyWorkoutFrequency": 5,
     "goal": "LOSE_WEIGHT",
     "exercisePreference": "WEIGHTLIFTING",
     "physicalLimitations": [],
     "currentWeight": 68,
     "height": 1.65
   }
   ```

## H2 Database Console

Access the H2 console at `http://localhost:8080/h2-console`

**Connection details:**
- JDBC URL: `jdbc:h2:file:./data/fitsass`
- Username: `sa`
- Password: (leave empty)

## Example Response

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
      "exercises": [
        {
          "name": "Bench Press",
          "sets": 4,
          "reps": 8,
          "duration": null,
          "difficulty": 1,
          "specificity": "UPPER_CHEST",
          "intensity": 0.8
        }
      ]
    }
  ]
}
```

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Database Errors
If you encounter database errors, delete the `./data` directory and restart the application.
