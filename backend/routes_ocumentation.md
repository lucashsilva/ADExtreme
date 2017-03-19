Route for authentication:
route: "api/auth/login"

Actions for the verb:
	POST -> Authentication
		RequestBody: email : String(not null and not empty), 
					 password : String(not null and not empty)

		ResponseBody: On sucess: Returns user information and authentication token.
					  On fail: Return error with mail address that can not be authenticated.

Routes for managing the user registry:	
route: "api/users"

Actions for the verb:
	POST -> Register a new user
		RequestBody: firstName : String(not null and not empty),
					 lastName : String(not null and not empty), 
					 password : String(not null and not empty), 
					 userRole : String(not null and not empty); Accepted values[NATURAL_PERSON, LEGAL_PERSON]

		ResponseBody: On sucess: Returns status 200.
					  On conflic: (User with the same email has already been registered). Returns status 409.
					  On server error: (User with invalid information). Returns status 500.

	GET -> Basic information of a user
		in route: "/{id}"

		RequestBody: none.
		PathVariable: id : Number(not null)

		ResponseBody: On sucess: ///////////////////////////////////
				      On fail: (User id invalid). Return status 404		