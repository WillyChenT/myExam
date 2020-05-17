package com.java.exam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {ExamApplication.class})
@AutoConfigureMockMvc
public class ExamTest {
		static String permission = "1"; //Super User=1 Manager=2 Operator=3
	
		@Autowired
		private MockMvc mockMvc;
		@Autowired
		private MockHttpSession session;
		
		public MockHttpSession sessionInit(String permission) {
			Map<String, Object> params = new LinkedHashMap<>();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			
			switch(permission) {
				case "1":
					session.setAttribute("API_KEY", "0fa7517e631837ba2d12a8ff0eeaf9491ee4aefaca3ad1a6bc557fabaf5a9af8");
					session.setAttribute("0fa7517e631837ba2d12a8ff0eeaf9491ee4aefaca3ad1a6bc557fabaf5a9af8", params);
					params.put("NAME_ID", "1");
					params.put("NAME", "Adam");
					params.put("API_KEY", "0fa7517e631837ba2d12a8ff0eeaf9491ee4aefaca3ad1a6bc557fabaf5a9af8");
					params.put("PERMISSION_ID", "1");
					params.put("UPDATED_TIME", dtf.format(LocalDateTime.now()));
//					params.put("UPDATED_TIME", "2020-05-16 15:14:07.0"); //開啟時，製造登入逾時情境
					break;
				case "2":
					session.setAttribute("API_KEY", "9b8a1e4752ac0c375bc9d6df08859425b1449035e9dd99afc48d8d0a06e8a4fb");
					session.setAttribute("9b8a1e4752ac0c375bc9d6df08859425b1449035e9dd99afc48d8d0a06e8a4fb", params);
					params.put("NAME_ID", "7");
					params.put("NAME", "工藤新一");
					params.put("API_KEY", "9b8a1e4752ac0c375bc9d6df08859425b1449035e9dd99afc48d8d0a06e8a4fb");
					params.put("PERMISSION_ID", "2");
					params.put("UPDATED_TIME", dtf.format(LocalDateTime.now()));
//					params.put("UPDATED_TIME", "2020-05-16 15:14:07.0"); //開啟時，製造登入逾時情境
					break;
				case "3":
					session.setAttribute("API_KEY", "0fa7517e631837ba2d12a8ff0eeaf9491ee4aefaca3ad1a6bc557fabaf5a9af8");
					session.setAttribute("0fa7517e631837ba2d12a8ff0eeaf9491ee4aefaca3ad1a6bc557fabaf5a9af8", params);
					params.put("NAME_ID", "5");
					params.put("NAME", "陳小美");
					params.put("API_KEY", "0fa7517e631837ba2d12a8ff0eeaf9491ee4aefaca3ad1a6bc557fabaf5a9af8");
					params.put("PERMISSION_ID", "3");
					params.put("UPDATED_TIME", dtf.format(LocalDateTime.now()));
//					params.put("UPDATED_TIME", "2020-05-16 15:14:07.0"); //開啟時，製造登入逾時情境
					break;
			}
			return session;
		}
		
		
		@Test
		public void loginTest() throws Exception {
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/login")
					.header("password", "12345")
					.param("name", "Adam")
					.session(session))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		}
		
		@Test
		public void createCompanyTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			
			HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.add("Content-Type", "application/json");
			JSONObject body = new JSONObject();
			body.put("name", "Starbucks");
			body.put("address", "台北市信義區基隆路一段200號5樓");
			
			//Super User && Operator
			if("1".equals(this.permission) || "3".equals(this.permission)) {
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/createCompany")
						.headers(httpHeaders)
						.content(body.toString())
						.session(session))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			//Manager
			}else if("2".equals(this.permission)) {
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/createCompany")
						.headers(httpHeaders)
						.content(body.toString())
						.session(session))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
			}

		}
		
		@Test
		public void updateCompanyTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			
			//Super User && Manager
			if("1".equals(this.permission) || "2".equals(this.permission)) {
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateCompany")
					.header("id", "2")
					.param("name", "全家便利商店股份有限公司")
					.param("address", "台北市中山區中山北路二段61號7樓")
					.session(session))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			//Operator
			}else if("3".equals(this.permission)){
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateCompany")
						.header("id", "2")
						.param("name", "全家便利商店股份有限公司")
						.param("address", "台北市中山區中山北路二段61號7樓")
						.session(session))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
			}

		}
		
		@Test
		public void getCompanyTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getCompany")
					.param("name", "萊爾富國際股份有限公司")
					.param("id", "3")
					.session(session))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		}
		
		@Test
		public void deleteCompanyTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			
			//Super User && Manager
			if("1".equals(this.permission) || "2".equals(this.permission)) {
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/delCompany")
						.header("id", "2")
						.param("name", "全家便利商店股份有限公司")
						.param("address", "台北市中山區中山北路二段61號7樓")
						.session(session))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			//Operator
			}else if("3".equals(this.permission)){
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/delCompany")
						.header("id", "2")
						.param("name", "全家便利商店股份有限公司")
						.param("address", "台北市中山區中山北路二段61號7樓")
						.session(session))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
			}

		}
		
		
		@Test
		public void createClientTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			
			HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.add("Content-Type", "application/json");
			JSONObject body = new JSONObject();
			body.put("company_id", "Starbucks");
			body.put("name", "王天才");
			body.put("email", "godisme@gmail.com");
			body.put("phone", "0943943943");
			
			//Super User && Operator
			if("1".equals(this.permission) || "3".equals(this.permission)) {
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/createClient")
					.headers(httpHeaders)
					.content(body.toString())
					.session(session))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			//Manager
			}else if("2".equals(this.permission)) {
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/createClient")
						.headers(httpHeaders)
						.content(body.toString())
						.session(session))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
				String a = "a";
			}

		}
		
		@Test
		public void createMultipleClientsTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.add("Content-Type", "application/json");
		    JSONObject body = new JSONObject();
		    
			JSONObject clientA = new JSONObject();
			clientA.put("company_id", "3");
			clientA.put("name", "牛頓");
			clientA.put("email", "newton@gmail.com");
			clientA.put("phone", "3456789");
			body.accumulate("clients", clientA);
			
			JSONObject clientB = new JSONObject();
			clientB.put("company_id", "2");
			clientB.put("name", "莫札特");
			clientB.put("email", "Mozart@gmail.com");
			clientB.put("phone", "012345678");
			body.accumulate("clients", clientB);

			
			System.out.println(body.toString());
			
			//Super User && Operator
			if("1".equals(this.permission) || "3".equals(this.permission)) {
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/createMultipleClients")
					.headers(httpHeaders)
					.content(body.toString())
					.session(session))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			//Manager
			}else if("2".equals(this.permission)) {
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/createMultipleClients")
						.headers(httpHeaders)
						.content(body.toString())
						.session(session))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
			}

		}
		
		@Test
		public void updateClientTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);

			//Super User && Manager
			if("1".equals(this.permission) || "2".equals(this.permission)) {
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateClient")
						.header("id", 7)
						.param("companyId", "3")
						.param("name", "Tom")
						.param("email", "tomCat@test.com")
						.param("phone", "0954321000")
						.session(session))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			//Operator
			}else if("3".equals(this.permission)){
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/updateClient")
						.header("id", 7)
						.param("companyId", "3")
						.param("name", "Tom")
						.param("email", "tomCat@test.com")
						.param("phone", "0954321000")
						.session(session))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
			}

		}
		
		@Test
		public void getClientTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			
			MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/getClient")
					.param("name", "阿笠博士")
					.param("id", "6")
					.session(session))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		}
		
		@Test
		public void deleteClientTest() throws Exception{
			MockHttpSession session = sessionInit(this.permission);
			
			//Super User && Manager
			if("1".equals(this.permission) || "2".equals(this.permission)) {
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/delClient")
						.header("id", "5")
						.param("companyId", "2")
						.param("name", "陳小美")
						.param("email", "xiaomei@test.com")
						.param("phone", "091234567")
						.session(session))
						.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
			//Operator
			}else if("3".equals(this.permission)){
				MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/delClient")
						.header("id", "5")
						.param("companyId", "2")
						.param("name", "陳小美")
						.param("email", "xiaomei@test.com")
						.param("phone", "091234567")
						.session(session))
						.andExpect(MockMvcResultMatchers.status().is4xxClientError()).andReturn();
			}

		}
}
