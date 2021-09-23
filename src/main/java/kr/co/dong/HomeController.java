package kr.co.dong;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	   public String index(Model model) {
	      
		model.addAttribute("str", "hello ");
		return "test01";
	   }
	
	@RequestMapping(value="/loginForm", method = RequestMethod.GET)
	public String loginForm() {
		
		logger.info("로그인 폼으로 이동합니다.");
		return "loginForm";
	}
	
	/*
	@RequestMapping(value="/loginForm", method = RequestMethod.GET)
	public void loginForm() {
		
		logger.info("로그인 폼으로 이동합니다.");
		//return "loginForm";
	}
	*/
	//
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String userid = request.getParameter("userid");
		String pw = request.getParameter("pw");		
		
		logger.info("로그인 결과처리 : " + userid + " , " + pw);
		
		if(userid.equals("admin") & pw.equals("12345")) {
			//userid.=="admin" & pw=="12345" 은 작동 안함
			logger.info("로그인 성공");
			//Session 변수 : 서버가 보관하는 변수
			return "result";
		}
		
		logger.info("로그인 실패");
		return "loginForm";
	}
}
