insert into StateCode (Id, Code, Name) values ( 10, 'FL', 'Florida') ;
insert into StateCode (Id, Code, Name) values ( 11, 'GA', 'Georgia') ;
insert into StateCode (Id, Code, Name) values ( 12, 'AL', 'Alabama') ;
-- insert into StateCode (Id, Code) values (12, 'HI') ;
insert into StateCode (Id, Code, Name) values ( 13, 'ID', 'Idaho') ;
insert into StateCode (Id, Code, Name) values ( 14, 'IL', 'Illinois') ;
insert into StateCode (Id, Code, Name) values ( 15, 'IN', 'Indiana') ;
insert into StateCode (Id, Code, Name) values ( 16, 'IA', 'Iowa') ;
insert into StateCode (Id, Code, Name) values ( 17, 'KS', 'Kansas') ;
insert into StateCode (Id, Code, Name) values ( 18, 'KY', 'Kentucky') ;
insert into StateCode (Id, Code, Name) values ( 19, 'LA', 'Louisiana') ;
insert into StateCode (Id, Code, Name) values ( 20, 'ME', 'Maine') ;
insert into StateCode (Id, Code, Name) values ( 21, 'MD', 'Maryland') ;
-- insert into StateCode (Id, Code) values (22, 'AK') ;
insert into StateCode (Id, Code, Name) values ( 22, 'MA', 'Massachusetts') ;
insert into StateCode (Id, Code, Name) values ( 23, 'MI', 'Michigan') ;
insert into StateCode (Id, Code, Name) values ( 24, 'MN', 'Minnesota') ;
insert into StateCode (Id, Code, Name) values ( 25, 'MS', 'Mississippi') ;
insert into StateCode (Id, Code, Name) values ( 26, 'MO', 'Missouri') ;
insert into StateCode (Id, Code, Name) values ( 27, 'MT', 'Montana') ;
insert into StateCode (Id, Code, Name) values ( 28, 'NE', 'Nebraska') ;
insert into StateCode (Id, Code, Name) values ( 29, 'NV', 'Nevada') ;
insert into StateCode (Id, Code, Name) values ( 30, 'NH', 'New Hampshire') ;
insert into StateCode (Id, Code, Name) values ( 31, 'NJ', 'New Jersey') ;
insert into StateCode (Id, Code, Name) values ( 32, 'AZ', 'Arizona') ;
-- insert into StateCode (Id, Code) values (32, 'NM') ;
insert into StateCode (Id, Code, Name) values ( 33, 'NY', 'New York') ;
insert into StateCode (Id, Code, Name) values ( 34, 'NC', 'North Carolina') ;
insert into StateCode (Id, Code, Name) values ( 35, 'ND', 'North Dakota') ;
insert into StateCode (Id, Code, Name) values ( 36, 'OH', 'Ohio') ;
insert into StateCode (Id, Code, Name) values ( 37, 'OK', 'Oklahoma') ;
insert into StateCode (Id, Code, Name) values ( 38, 'OR', 'Oregon') ;
insert into StateCode (Id, Code, Name) values ( 39, 'PA', 'Pennsylvania') ;
insert into StateCode (Id, Code, Name) values ( 40, 'PR', 'Puerto Rico') ;
insert into StateCode (Id, Code, Name) values ( 41, 'RI', 'Rhode Island') ;
-- insert into StateCode (Id, Code, Name) values ( 42, 'AR', 'Arkansas') ;
insert into StateCode (Id, Code, Name) values ( 42, 'SC', 'South Carolina') ;
insert into StateCode (Id, Code, Name) values ( 43, 'SD', 'South Dakota') ;
insert into StateCode (Id, Code, Name) values ( 44, 'TN', 'Tennessee') ;
insert into StateCode (Id, Code, Name) values ( 45, 'TX', 'Texas') ;
insert into StateCode (Id, Code, Name) values ( 46, 'UT', 'Utah') ;
insert into StateCode (Id, Code, Name) values ( 47, 'VT', 'Vermont') ;
insert into StateCode (Id, Code, Name) values ( 48, 'VI', 'Virgin Islands') ;
insert into StateCode (Id, Code, Name) values ( 49, 'VA', 'Virginia') ;
insert into StateCode (Id, Code, Name) values ( 50, 'WA', 'Washington') ;
insert into StateCode (Id, Code, Name) values ( 51, 'WV', 'West Virginia') ;
-- insert into StateCode (Id, Code, Name) values ( 52,'CA','California') ;
insert into StateCode (Id, Code, Name) values ( 52, 'WI', 'Wisconsin') ;
insert into StateCode (Id, Code, Name) values ( 53, 'WY', 'Wyoming') ;
insert into StateCode (Id, Code, Name) values ( 55, 'CA', 'California') ;
insert into StateCode (Id, Code, Name) values ( 62, 'CO', 'Colorado') ;
-- insert into StateCode (Id, Code, Name) values ( 67, 'TX', 'Texas') ;
-- insert into StateCode (Id, Code, Name) values ( 68, 'FL', 'Florida') ;
insert into StateCode (Id, Code, Name) values ( 72, 'CT', 'Connecticut') ;
-- insert into StateCode (Id, Code, Name) values ( 74, 'TX', 'Texas') ;
insert into StateCode (Id, Code, Name) values ( 82, 'DE', 'Delaware') ;
-- insert into StateCode (Id, Code, Name) values ( 85, 'GA', 'Georgia') ;
insert into StateCode (Id, Code, Name) values ( 92, 'DC', 'District of Columbia') ;

-- manually re-assign an Id for AR, NM, AK, HI
-- their orignial Id were duplicates, overlapping with other states
--
insert into StateCode (Id, Code, Name) values ( 93, 'HI', 'Hawaii') ;
insert into StateCode (Id, Code, Name) values ( 94, 'AK', 'Alaska') ;
insert into StateCode (Id, Code, Name) values ( 95, 'NM', 'New Mexico') ;
insert into StateCode (Id, Code, Name) values ( 96, 'AR', 'Arkansas') ;


insert into Disaster (Id, StartDate, Name, EndDate) values (1001, '2022-06-11', 'South Dakota Severe Storm, Straight-line Winds, Tornadoes, and Flooding (DR-4664-SD)', '2022-06-14') ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1002, '2022-07-26', 'Kentucky Severe Storms, Flooding, Landslides, and Mudslides (DR-4663-KY)', null) ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1003, '2022-05-12', 'Nebraska Severe Storms and Straight-line Winds (DR-4662-NE)', '2022-05-12') ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1004, '2022-05-07', 'Alaska Landslide (DR-4661-AK)', '2022-05-07') ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1005, '2022-07-22', 'California Oak Fire (FM-5445-CA)',null) ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1006, '2022-07-18', 'Texas Chalk Mountain Fire (FM-5444-TX)', null) ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1007, '2022-04-22', 'North Dakota Severe Winter Storm and Flooding (DR-4660-ND)', '2022-05-25') ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1008, '2022-04-22', 'Minnesota Severe Storms, Straight-line Winds, and Flooding (DR-4659-MN)', '2022-06-15') ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1009, '2022-06-12', 'Arizona Pipeline Fire (FM-5441-AZ)', null) ;
insert into Disaster (Id, StartDate, Name, EndDate) values (1010, '2022-01-28', 'Rhode Island Severe Winter Storm and Snowstorm (DR-4653-RI)', '2022-01-29') ;


insert into Facility (Id, FacilityName, IsOpen, NPI, StateCode) values (1001, 'UPMC Northwest', true, '1003906488', 42) ;
insert into Facility (Id, FacilityName, IsOpen, NPI, StateCode) values (1002, 'Banner University Medical Center', true, '1417101098', 29) ;
insert into Facility (Id, FacilityName, IsOpen, NPI, StateCode) values (1003, 'University of Alaska Anchorage General Hospital', false, '1053866467', 94) ;

insert into PatientStatus (id, status) values (101, 'unaffected');
insert into PatientStatus (id, status) values (102, 'injured');
insert into PatientStatus (id, status) values (103, 'ill in facility');
insert into PatientStatus (id, status) values (104, 'ill but not in facility');
insert into PatientStatus (id, status) values (105, 'deceased');
insert into PatientStatus (id, status) values (106, 'isolated');

insert into User (name, password, roles, facilityNpi) values ('userEhr', 'abcd1234', 'EHR', '1003906488');
insert into User (name, password, roles, facilityNpi) values ('userGovt', 'abcd1234', 'GOVT', '1417101098');
insert into User (name, password, roles, facilityNpi) values ('userFsa', 'abcd1234', 'FSA', '1053866467');
