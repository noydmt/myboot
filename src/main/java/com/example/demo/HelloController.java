package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	String[] names = {"tanaka","sato","kato","suzuki","simizu"};
	String[] emails = {"tanaka@email.com","sato@email.com","kato@email.com","suzuki@email.com","simizu@email.com"};

	@RequestMapping("/")
	public String index() {
		return "Welcome to MyBootGradleApplication!";
	}
	@RequestMapping("/{num}")
	public String indexWithParam(@PathVariable int num) {
		int res = 0;
		for(int i = 0; i <= num; i++) {
			res += i;
		}
		String strRes = String.valueOf(res);
		return "合計は" + strRes;
	}
	@RequestMapping("/id/{id}")
	public obj getId(@PathVariable int id) {
		return new obj(id, names[id], emails[id]);
	}

	class obj {
		private int id;
		private String name;
		private String email;

		public obj(int id, String name, String email) {
			// super();
			this.setId(id);
			this.setName(name);
			this.setEmail(email);
		}

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
	}
}
