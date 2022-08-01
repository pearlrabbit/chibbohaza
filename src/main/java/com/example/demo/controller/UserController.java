package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UserService;
import com.example.demo.vo.User;

@RestController
public class UserController {
	
	@Autowired
	private UserService us;
	
	@GetMapping("/listUser")
	public ModelAndView findAll(Model model){
		ModelAndView mav=new ModelAndView("listUser");
		model.addAttribute("userList", us.findAll());
		for(int i=0;i<us.findAll().size();i++) {
			System.out.println(us.findAll().get(i));
		}
		return mav;
	}
	
	@GetMapping("/signUpUser")
	public ModelAndView insertForm() {
		ModelAndView mav=new ModelAndView("signUp");
		return mav;
	}
	
	@PostMapping("/signUpUser")
	public ModelAndView insertOk(User user) {
		ModelAndView mav=new ModelAndView("login");
		user.setUserNum(us.getNextUserNum());
		user.setUserRole("USER");
		us.insertUser(user);
		return mav;
	}
	
	@GetMapping("/login")
	public ModelAndView loginForm() {
		ModelAndView mav=new ModelAndView("login");
		return mav;
	}
	
	@PostMapping("/login")
	public ModelAndView loginOK(User user, Model model, HttpSession session) {
		ModelAndView mav=new ModelAndView("main");
		User loginUser=us.loginCheck(user);
		if(loginUser!=null) {
			if(loginUser.getUserPwd()==user.getUserPwd() || loginUser.getUserPwd().equals(user.getUserPwd())) {
				model.addAttribute("userInfo", loginUser);
				session.setAttribute("userInfo", loginUser);
				session.setAttribute("userNum", loginUser.getUserNum());
			}
			else {
				System.out.println("로그인 실패");
			}
		}else {
			System.out.println("로그인 실패");
		}
		return mav;
	}
	
	@GetMapping("/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView mav=new ModelAndView("main");
		session.invalidate();
		return mav;
	}
}
