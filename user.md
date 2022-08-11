## API User Usage instructions

The OpenAPI documentation home page url is generated in the test step.

The API is pre-seeded with data for Disaster, PatientStatus, Facility, and UserAccount. Specifics are listed at the end of this document for quicker reference, or you can view [acme/src/main/resources/import.sql](acme/src/main/resources/import.sql) for the seeding code. **You will need to use the seeded IDs when interacting with the API.**

### Authenticating as a specific user role

The only endpoint immediately available to you will be `/auth/login`. You will use this to generate a JWT for the user type you want to test against. This JWT will be valid for 30 minutes before you will need to generate a new one.

Available users:

|Username|Role|Corresponding `facilityNpi`|
|---|---|---|
|`userEhr`|EHR Maintainer|`1003906488`|
|`userEhrAlaska`|EHR Maintainer|`1053866467`|
|`userGovt`|Government Systems Care Coordinator|`1417101098`|
|`userFsa`|Facility Systems Administrator|`1053866467`|

These user's usernames are also shared in the OpenAPI documentation, and appear in the UI description. The corresponding `facilityNpi` is important for some API calls, and is listed here for convenience.

1. Expand the auth-controller section, and then the POST /auth/login section.
2. Use the "Try it out" feature in the documentation and replace the "string" in the sample request body with the username of the role you wish to impersonate. 
3. Execute the query.
4. Copy the token string from the response body.
5. Return to the top of the page, and click the green "Authorize" button.
6. In the dialogue box that appears, paste the token string into the value box and click the "Authorize" button.

To log in as a different user, you will need to reopen the Authorize dialogue box and click "Logout" before repeating for a new user.

### Using the disaster-controller

#### GET /disaster/

Returns a count of patient impact by status, with the ability to filter using parameters for which disaster, which facility, which state, the timeframe, and patient status.

#### Sample request parameters

|GET Parameter|Value|Optional?|
|---|---|---|
|`disasterId`|The ID of the disaster. (ex: `1001`)|OPTIONAL|
|`facilityNpi`|The NPI of the facility. (ex: `44`)|OPTIONAL|
|`stateId`|The State ID correlating to facility locations (ex: `12`)|OPTIONAL|
|`timeframe`|Time to retrieve results up until. (format: `yyyymmdd`)|OPTIONAL|
|`statusId`|The ID of the status. (ex: `101` for "unaffected")|OPTIONAL|

#### Sample response body

A successful request will respond with a `200` HTTP status code and the following payload format:

```
{
  "unaffected": 0,
  "injured": 0,
  "illInFacility": 0,
  "illNotInFacility": 0,
  "deceased": 0,
  "isolated": 0
}
```

If the response returns an error, make sure you are logged in with an unexpired JWT token.

### Using the patient-disaster-status-controller

#### GET /patientStatus/

Returns a patient's ID (not the facility's ID), and information about their most recently recorded update.

**Only EHR Maintainers with a matching facilityNpi and Government Care System Coordinators have permission to receive information from this endpoint.**

#### Sample request parameters

|GET Parameter|Value|Optional?|
|---|---|---|
|`facilityNpi`|The facility's NPI (ex: `1003906488`)|REQUIRED|
|`patientIdFromFacility`|The ID attached to a patient at the facility. (ex: `myCustomFacilityId123`)|REQUIRED|

#### Sample response body

A successful request will respond with a `200` HTTP status code and the following payload format:

```
{
  "date": "2022-08-11T21:59:47.507Z",
  "patientId": "string",
  "disasterName": "string",
  "patientStatus": "string",
  "facilityLocation": "string"
}
```

where the `patientId` is the API's internally-generated ID for the patient.

If the response returns an error, make sure you are logged in as either a Government Care Systems Coordinator or an EHR Maintainer with a matching `facilityNpi` that corresponds with the user.

#### POST /patientStatus/

Accepts an update for a patient. Requires the facility's NPI, the facility's patient ID, the ID of the disaster impacting the patient, the ID of their current status, and the date that this update was recorded on. On success, the endpoint will return the API's generated ID for the patient.

**Only EHR role users have permission to create a new patient status. Other users are unauthorized.**

##### Sample request parameters

|Parameter|Value|Optional?|
|---|---|---|
|`facilityNpi`|The facility's NPI, should match the `facilityNpi` of the authenticated user (ex: `1003906488`)|REQUIRED|
|`patientIdFromFacility`|The ID attached to a patient at the facility. (ex: `myCustomFacilityId123`)|REQUIRED|
|`disasterId`|The disaster ID. (ex: `1001`)|REQUIRED|
|`date`|The date the patient was impacted by the disaster (format: `YYYY-MM-ddThh:mm:ss.SSSZ`)|REQUIRED|
|`statusId`|The status ID corresponding to the patient's current status (ex: `101`)|REQUIRED|

The `facilityNpi`, `disasterId`, and `statusId` should correspond to an existing ID seeded in the database. The `patientIdFromFacility` can be any string that the facility would use. The `date` conforms to ISO 8601, and should follow the format.

##### Sample response body

A successful request will respond with a `201` HTTP status code and the following payload format:

```
{
  "id": 0
}
```

where the `id` is the API's internally-generated ID for the patient.

If the response returns an error, make sure you are logged in as an EHR Maintainer and that the `facilityNpi` corresponds with the user.

## Seeded ID Reference

Below are the items that are preseeded into the database during the install steps.

### Disaster IDs

If a disaster does not have an end date listed, that means that the disaster is still ongoing. We sampled a recent selection of disasters from FEMA's disaster website to generate these.

|Disaster ID|Name|StartDate|EndDate|
|---|---|---|---|
|`1001`|South Dakota Severe Storm, Straight-line Winds, Tornadoes, and Flooding (DR-4664-SD)|`2022-06-11`|`2022-06-14`|
|`1002`|Kentucky Severe Storms, Flooding, Landslides, and Mudslides (DR-4663-KY)|`2022-07-26`|`null`|
|`1003`|Nebraska Severe Storms and Straight-line Winds (DR-4662-NE)|`2022-05-12`|`2022-05-12`|
|`1004`|Alaska Landslide (DR-4661-AK)|`2022-05-07`|`2022-05-07`|
|`1005`|California Oak Fire (FM-5445-CA)|`2022-07-22`|`null`|
|`1006`|Texas Chalk Mountain Fire (FM-5444-TX)|`2022-07-18`|`null`|
|`1007`|North Dakota Severe Winter Storm and Flooding (DR-4660-ND)|`2022-04-22`|`2022-05-25`|
|`1008`|Minnesota Severe Storms, Straight-line Winds, and Flooding (DR-4659-MN)|`2022-04-22`|`2022-06-15`|
|`1009`|Arizona Pipeline Fire (FM-5441-AZ)|`2022-06-12`|`null`|
|`1010`|Rhode Island Severe Winter Storm and Snowstorm (DR-4653-RI)|`2022-01-28`|`2022-01-29`|

### Facility IDs

When interacting with facilities in the API, you will most often need the Facility NPI, **not the Facility ID**.

|Facility ID|Facility NPI|Facility Name|Facility State|Open?|
|---|---|---|---|---|
|1001|`1003906488`|UPMC Northwest|South Carolina (id: `42`)|`true`|
|1002|`1417101098`|Banner University Medical Center|Nevada (id: `29`)|`true`|
|1003|`1053866467`|University of Alaska Anchorage General Hospital|Alaska (id: `94`)|`false`|

### Patient Status IDs

|Patient Status ID|Status Name|
|---|---|
|`101`|unaffected|
|`102`|injured|
|`103`|ill in facility|
|`104`|ill but not in facility|
|`105`|deceased|
|`106`|isolated|

### State Code IDs

|`StateCodeId`|State|
|---|---|
|10|Florida|
|11|Georgia|
|12|Alabama|
|13|Idaho|
|14|Illinois|
|15|Indiana|
|16|Iowa|
|17|Kansas|
|18|Kentucky|
|19|Louisiana|
|20|Maine|
|21|Maryland|
|22|Massachusetts|
|23|Michigan|
|24|Minnesota|
|25|Mississippi|
|26|Missouri|
|27|Montana|
|28|Nebraska|
|29|Nevada|
|30|New Hampshire|
|31|New Jersey|
|32|Arizona|
|33|New York|
|34|North Carolina|
|35|North Dakota|
|36|Ohio|
|37|Oklahoma|
|38|Oregon|
|39|Pennsylvania|
|40|Puerto Rico|
|41|Rhode Island|
|42|South Carolina|
|43|South Dakota|
|44|Tennessee|
|45|Texas|
|46|Utah|
|47|Vermont|
|48|Virgin Islands|
|49|Virginia|
|50|Washington|
|51|West Virginia|
|52|Wisconsin|
|53|Wyoming|
|55|California|
|62|Colorado|
|72|Connecticut|
|82|Delaware|
|92|District of Columbia|
|93|Hawaii|
|94|Alaska|
|95|New Mexico|
|96|Arkansas|
