# CSC207-PetPal

### Problem domain:
Our project focuses on pet adoption and pet-related social interactions. This domain involves helping people find and adopt the right pets for their lifestyles. Our chosen domain addresses pet overpopulation and animal welfare.

### Project description: 
Our team is considering developing a project that uses the Pet Finder API to create a platform for pet adoption. We will include the following functionalities:

1. Pet Adoption Matching: Users will be able to input their preferences, such as pet type (e.g., dog, cat, rabbit), breed, size, age, and activity level. The app will use this information to provide them with a list of potential pets available for adoption that match their criteria.

2. Adoption Request: Once the user has spotted a pet they are interested in adopting after viewing its detailed profile, user can send a request to the pet's welfare to adopt their pet. Then when this welfare organzation logs in to our program, which they will be automatically signed up for, they can view pending requests for their pets and approve/deny it.

Overall, our application aims to simplify the pet adoption process by providing a user-friendly interface for finding and communicating with potential adopters or pet owners! :)


### Link to Pet Finder API documentation: https://www.petfinder.com/developers/v2/docs/

### Note to API access: 
Due to disclosure restriction of our API credentials, which are necessary for accessing the API, our team has kepy a local copy of file "api/Credentials" in our code when running the program. This file contains our API client ID and client secret, and methods to retrieve them when called in "api/GetToken" to generate access token to the API each time we require access. For this reason, our code in this repository is not fully functional without these credentials to the API.

### API Screenshot (Postman):

![API Screenshot](https://github.com/Viceu/CSC207-PetMatch/assets/144386124/fc899a3f-5f6e-47c6-b49e-4e4b8fe3631e)


### Example Code (in src/api/CodeSnippet.Java) Output:
"German Shepherd Dog"
