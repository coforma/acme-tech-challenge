## Usage instructions

1.	Navigate to home page with url generated in test step
2.  Application is loaded with seed data for Disaster , PatientStatus , Facility, UserAccount. Specifics are listed in [acme/src/main/resources/import.sql](acme/src/main/resources/import.sql)
3.  Create a JWT for the user type you want to test against. Types are listed in the Swagger file and the tokens will be returned in the API response. Username ends with role user has. JWT is valid for 30 minutes.
4. In swagger-ui page set ACME_API_JWT_TOKEN token returned from previous step by clicking 'Authorize button'
5. To create new PatientDisasterUpdate set authorization header of user with EHR role. Only EHR role users have permissions to create a patientdisasterstatus record. Other users will get a 403.
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
7. Payload Information returned 201 with id.
8. To retrieve patient status update set authorization header of user with EHR or GOVT role. Both EHR, GOVT roles users have permissions to get a record. Other users will get 403
9. Invoke GET /patientStatus with request params need to match from post request
*Note:* the facilityNpi and patientId must match what was passed on the post
```json
{
   "facilityNpi": 1003906488,
   "patientIdFromFacility": "<some_value>"
}
```