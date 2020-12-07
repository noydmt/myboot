package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repositories.MyData;
import com.example.demo.service.MyDataService;

//@RestController JSON形式のデータを返す => Restfulなサービス作成
@Controller
public class HelloController {

	@Autowired
	MyDataService service;

	// 初期画面(一覧画面)
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav, @ModelAttribute("formModel") MyData myData) {
		List<MyData> list = service.selectAll();
		mav.addObject("datalist", list);
		mav.setViewName("index");
		return mav;
	}

	// 新規登録
	@RequestMapping(value="/", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView create(@ModelAttribute("formModel") @Validated MyData myData, BindingResult result,
			RedirectAttributes redirectAttributes, ModelAndView mav) {
		if(!result.hasErrors()) {
			service.create(myData);
			redirectAttributes.addFlashAttribute("msg", "登録完了");
			/*
			 * フラッシュスコープを使用することでリダイレクト先にparamを渡せる。
			 * Request < Flash < Session : session を使うとメモリの消費につながる。またsessionを使うと明示的にremoveする必要性がでる。
			 */
			return new ModelAndView("redirect:/");
		}
		List<MyData> list = service.selectAll();
		mav.addObject("datalist", list);
		mav.setViewName("index");
		return mav;
	}

	// 検索画面へ遷移
	@RequestMapping(value="/find", method=RequestMethod.GET)
	public ModelAndView find(ModelAndView mav) {
		List<MyData> list = service.selectAll();
		mav.addObject("datalist", list);
		mav.setViewName("find");
		return mav;
	}

	//検索処理
	@RequestMapping(value="/find", method=RequestMethod.POST)
	public ModelAndView search(ModelAndView mav, HttpServletRequest req) {
		String keyWord = req.getParameter("keyWord");
		List<MyData>list = service.search(keyWord);
		int size = list.size();
		mav.addObject("datalist", list);
		mav.addObject("value", keyWord);
		mav.addObject("countMsg", "検索結果： " + String.valueOf(size) + " 件");
		mav.setViewName("find");
		return mav;
	}


	// 編集画面
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView create(ModelAndView mav, @PathVariable int id) {
		MyData data = service.findById((long) id);
		mav.addObject("dataObject", data);
		mav.setViewName("edit");
		return mav;
	}

	// 更新
	@RequestMapping(value="/edit", method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(@ModelAttribute("dataObject") @Validated MyData myData, BindingResult result,
			RedirectAttributes redirectAttributes, ModelAndView mav) {
		if(!result.hasErrors()) {
			if(service.exist(myData)) {
				service.update(myData);
				redirectAttributes.addFlashAttribute("msg", "更新完了");
				return new ModelAndView("redirect:/");
			}
			redirectAttributes.addFlashAttribute("msg", "存在しないデータです。");
			return new ModelAndView("redirect:/");
		}
		mav.addObject("dataObject", myData);
		mav.setViewName("edit");
		return mav;
	}

	// 削除
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
