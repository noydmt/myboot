package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repositories.MsgDataRepository;

@Controller
public class MsgDataController {

	@Autowired
	MsgDataRepository msgDataRepository;

	@RequestMapping(value="/{id}/msgDatas", method=RequestMethod.GET)
	public ModelAndView getMsgDataList(ModelAndView mav, @PathVariable long id) {
		return null;
	}

	@RequestMapping(value="/{id}/msgDatas/{msdId}", method=RequestMethod.GET)
	public ModelAndView getMsgData(ModelAndView mav, @PathVariable long id, @PathVariable long msdId) {
		return null;
	}
}
