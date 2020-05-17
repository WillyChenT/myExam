package com.java.exam.data;

import io.swagger.annotations.ApiModelProperty;

public class Clients {
	
    @ApiModelProperty(position=1 ,value = "Clients", example="[\r\n" + 
    		" {\r\n" + 
    		"	\"company_id\": \"1\",\r\n" + 
    		"    \"name\": \"陳天才\",\r\n" + 
    		"    \"email\": \"godisme@gmail.com\",	\r\n" + 
    		"    \"phone\": \"0943943943\"\r\n" + 
    		" },\r\n" + 
    		" {\r\n" + 
    		"	\"company_id\": \"3\",\r\n" + 
    		"	\"name\": \"王水來\",\r\n" + 
    		"	\"email\": \"happy9@gmail.com\",	\r\n" + 
    		"	\"phone\": \"6636636\"\r\n" + 
    		" }	\r\n" + 
    		"]")
	private Client[] clients;

	public Client[] getClients() {
		return clients;
	}

	public void setClients(Client[] clients) {
		this.clients = clients;
	}
	
	

}

