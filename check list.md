# Service management system

### Misc

- use jdbc to connect to db
- take parameters as body

----------------
----------------

## On board service center

### Misc

### GET

1. Input
   - center ID


2. Tasks
   - get all the centers with the given id


3. Output
   - list of center id, location,


4. exceptions
   - center id doesn't exists
   - center status is true
   - check if center id is given

### POST

1. Input
   - center ID
   - location
   - start time
   - stop time


2. Tasks
   - add service center to db
      - add to service center table


3. Output
   - http response

4. exceptions
   - check if the center already exists in db
   - validate if all the fields are non-null

### PUT

1. Input
   - center ID
   - location
   - start time
   - stop time

2. Tasks
   - update service center to db
      - update to service center table
   - check if the center doesn't exist in db


3. Output
   - http response

4. exceptions
   - validate if all the fields are non-null
   - update only if the id is already present in the table

### DELETE

1. Input
   - center id


2. Tasks
   - delete service center from db
      - update status in service center table to false
   - check if the center is already inactive


3. Output
   - http response


4. exceptions
   - validate if all the fields are non-null
   - check the center id is present in the db to delete

----------------

## on board technician

### Misc

### GET

1. Input
   - tech ID
   - center ID


2. Tasks
   - search db for the technicians


3. Output
   - technician id, location

4. exceptions
   - validate if all the fields are non-null
   - display if the tech id is present in the db
   - only display the tech whose status is true

### POST

1. Input
   - tech ID
   - center ID


2. Tasks
   - add tech to db
      - add row in technician list
   - check if the tech is already exists


3. Output
   - http response

4. exceptions
   - validate if all the fields are non-null
   - check if the tech id is present in the db

### PUT

1. Input
   - tech ID
   - center ID


2. Tasks
   - update tech to db
      - update row in technician list
   - check if the tech doesn't exist


3. Output
   - http response

4. exceptions
   - validate if all the fields are non-null

### DELETE

1. Input
   - tech id

2. Tasks
   - delete in db
      - update technician list status to false
   - check if the tech is already inactive

3. Output
   - http response

4. exceptions
   - validate if all the fields are non-null

----------------

## Assign technician

### Misc

### GET

1. Input
   - center id
   - date
   - start time


2. Tasks
   - search in db
      - search with conditions in center schedule
   - check if the center id exists


3. Output
   - list of technics, and the available time

4. exceptions
   - validate if all the fields are non-null

### POST

1. Input
   - tech id
   - center id
   - date
   - start time
   - end time


2. Tasks
   - add task into db
      - add task into center schedule
   - check if center already exist


3. Output
   - http response

4. exceptions
   - validate if all the fields are non-null

### PUT

1. Input
   - tech id
   - center id
   - date
   - start time
   - end time


2. Tasks
   - update task into db
      - update task into center schedule
   - check if center doesn't exist

3. Output
   - http response

4. exceptions
   - validate if all the fields are non-null

### DELETE

1. Input
   - tech id
   - center id
   - date


2. Tasks
   - delete task in db
      - update status in center schedule to false
   - check if the task doesn't exist or status is set to false


3. Output
   - http response


4. exceptions
   - validate if all the fields are non-null

----------------

## Search technician

### Misc

### GET

1. Input
   - center id
   - date
   - start time


2. Tasks
   - search in db
      - search with conditions in center schedule
   - check if the center id exists


3. Output
   - list of technics, and the available time


4. exceptions
   - validate if all the fields are non-null

----------------
----------------

## Database

### 1. Center schedule

- id
- center id
- tech id
- date
- start time
- stop time
- status

### 2. Service center

- center id
- location
- start time
- stop time
- status

### 3. Technician List

- technician id
- center id
- status

