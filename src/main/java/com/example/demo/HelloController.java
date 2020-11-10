package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
@Controller
public class HelloController {
	String[] names = {"tanaka","sato","kato","suzuki","simizu"};
	String[] emails = {"tanaka@email.com","sato@email.com","kato@email.com","suzuki@email.com","simizu@email.com"};

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("msg", "フォームを送信してください");
		return "index";
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String indexPost(
			Model model,
			@RequestParam("fullName")String fullName,
			@RequestParam("adress")String adress,
			@RequestParam("memo")String memo,
			@RequestParam(value="sex",required=false)String sex,
			@RequestParam(value="os",required=false)String os,
			@RequestParam(value="apply",required=false)boolean isApply
			) {
		model.addAttribute("postedMsg", "送信内容");
		model.addAttribute("postedFullName", fullName);
		model.addAttribute("postedAdress", adress);
		model.addAttribute("postedSex", sex);
		model.addAttribute("postedOs", os);
		model.addAttribute("postedIsApply", isApply);
		model.addAttribute("postedMemo", memo);
		return "index";
	}
	@RequestMapping(value="/{num}")
	public String indexWithParam(@PathVariable int num) {
		int res = 0;
		for(int i = 0; i <= num; i++) {
			res += i;
		}
		String strRes = String.valueOf(res);
		return "合計は" + strRes;
	}
	@RequestMapping(value="/id/{id}")
	public obj getId(@PathVariable int id) {
		return new obj(id, names[id], emails[id]);
	}

	class obj {
		private int id;
		private String name;
		private String email;

		public obj(int id, String name, String email) {
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