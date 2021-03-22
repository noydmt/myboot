package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.MyDataRestRepository;
import com.example.demo.repositories.RestData;
import com.example.demo.service.MyDataRestService;

import java.util.List;

import javax.annotation.PostConstruct;

@RestController
public class MyDataRestController {

	@Autowired
	MyDataRestRepository RestDataRepository;

	@Autowired
	MyDataRestService restService;

	@PostConstruct
	public void init() {
		RestData data1 = new RestData();
		data1.setId(1);
		data1.setRestName("Test1");
		data1.setCommunity("TestCommunity1");
		RestDataRepository.saveAndFlush(data1);

		RestData data2 = new RestData();
		data1.setId(2);
		data1.setRestName("Test2");
		data1.setCommunity("TestCommunity2");
		RestDataRepository.saveAndFlush(data2);

		RestData data3 = new RestData();
		data1.setId(3);
		data1.setRestName("Test3");
		data1.setCommunity("TestCommunity3");
		RestDataRepository.saveAndFlush(data3);
	}

	@RequestMapping(value = "/restIndex")
	public List<RestData> index(ModelAndView mav) {
		return restService.getAll();
	}

	@RequestMapping(value = "/restIndex/{num}")
	public RestData restBy(@PathVariable int num) {
		return restService.getFind(num);
	}
}
