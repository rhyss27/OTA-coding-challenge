# Note Application Rest API

## Description:
  
  A simple backend for a note-taking application using Spring-boot. The data storage solution used is thru an XML file that stores the id, title and body of the note. 

## Endpoints:
  
  - **GET**    /api/notes
  
Description: Retrieve all notes.

![image](https://github.com/rhyss27/OTA-coding-challenge/assets/35577923/d749d193-d622-44dd-ad23-1293e30d7bf5)

  - **POST**   /api/notes 

Description: Create a new note.

    Request Body Parameters:
      title - (required) Title of Note
      body - (optional) Note Contents

![image](https://github.com/rhyss27/OTA-coding-challenge/assets/35577923/be5d2a5d-9ad9-405a-bb39-0281aa2897e0)

  - **GET**    /api/notes/:id 

Description: Retrieve a specific note by ID.

    URL Parameters: 
      id - (required) Note ID

![image](https://github.com/rhyss27/OTA-coding-challenge/assets/35577923/20d3dd30-7164-4fa2-a368-38ddf9b25ac5)

  - **PUT**    /api/notes/:id 

Description: Update a specific note.

    URL Parameters: 
      id - (required) Note ID
    Request Body Parameters:
      title - (required) Title of Note
      body - (optional) Note Contents
      
![image](https://github.com/rhyss27/OTA-coding-challenge/assets/35577923/ca1ee77a-f9e4-4e5f-820e-4e864a25793b)
           
  - **DELETE** /api/notes/:id 

Description: Delete a specific note.

    URL Parameters: 
      id - (required) Note ID

![image](https://github.com/rhyss27/OTA-coding-challenge/assets/35577923/3f651a9f-f06d-4cb2-b202-e378a03aea52)

## Limitations

Data Persistence is done thru an XML file and as such this may not be advisable for large scale storage as it can impact performance. API also assumes that the XML file is already created in the resource folder, and as part of the commit, should not be deleted.
