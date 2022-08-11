## Usage instructions

1.	Navigate to home page with url generated in test step
2.  Application is loaded with seed data for Disaster , PatientStatus , Facility, UserAccount. Specifics are listed in [acme/src/main/resources/import.sql](acme/src/main/resources/import.sql)
3.  Perform a POST to the /auth/login endpoint to a JWT for the user type you want to test against. Types are listed in the Swagger File and appear in the UI description. The token is returned in the API response. The last part of the username corresponds to the role the user has. The JWT is valid for 30 minutes.
4. Click the `Authorize` button towards the top of the Swagger UI to set the value of ACME_API_JWT_TOKEN using the token returned from the previous step
5. To create a new PatientDisasterUpdate, perform steps 3 and 4 with the EHR role. Only EHR role users have permission to create a PatientDisasterStatus record. Other users are unauthorized.
6. Invoke POST /patientStatus/ with sample parameters as below
- *facilityNpi* must match a seeded facility as well as the EHR user claim making the call
- *patientIdFromFacility* any string, will need later for lookup
- *disasterId* seeded disaster
- *date* date the update happened in format 'YYYY-MM-ddThh:mm:ss.SSSZ'
- *statusId* seeded status id
```json
{
  "facilityNpi": 1003906488,
  "patientIdFromFacility": "<some_value>",
  "disasterId": 1001,
  "date": "2022-08-11T18:36:39.778Z",
  "statusId": 102
}
```
7. The payload should return a status 201 with the id. If the call is unauthorized, review steps 3-5.
8. To retrieve a Patient Status Update perform steps 3 and 4 with the EHR or GOVT role. Both EHR, GOVT roles users have permissions to get a record. Other users are unauthorized.
9. Invoke GET /patientStatus/ using request parameters that match those used in the POST request
*Note:* the facilityNpi and patientId must match what was passed on the post
```json
{
   "facilityNpi": 1003906488,
   "patientIdFromFacility": "<some_value>"
}
```