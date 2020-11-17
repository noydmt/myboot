package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.MyDataService;

//@RestController JSON形式のデータを返す => Restfulなサービス作成
@Controller
public class HelloController {

	@Autowired
	MyDataService service;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		List<MyData> list = service.selectAll();
		mav.addObject("datalist", list);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value="/", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String create(@ModelAttribute("formModel") MyData myData, RedirectAttributes redirectAttributes) {
		service.create(myData);
		redirectAttributes.addFlashAttribute("msg", "登録完了");
		/*
		 * フラッシュスコープを使用することでリダイレクト先にparamを渡せる。
		 * Request < Flash < Session : session を使うとメモリの消費につながる。またsessionを使うと明示的にremoveする必要性がでる。
		 */
		return "redirect:/";
	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView create(ModelAndView mav, @PathVariable int id) {
		MyData data = service.findById((long) id);
		mav.addObject("dataObject", data);
		mav.setViewName("edit");
		return mav;
	}

	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String update(@ModelAttribute("dataObject") MyData myData,
			RedirectAttributes redirectAttributes) {
		if(service.exist(myData)) {
			service.update(myData);
			redirectAttributes.addFlashAttribute("msg", "更新完了");
		} else {
			redirectAttributes.addFlashAttribute("msg", "存在しないデータです。");
		}
		return "redirect:/";
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
		if(service.exist(id)) {
			service.delete(id);
			redirectAttributes.addFlashAttribute("msg", "削除しました。");
		} else {
			redirectAttributes.addFlashAttribute("msg", "存在しないデータです。");
		}
		return "redirect:/";
	}
}
