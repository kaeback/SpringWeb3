package com.spring.web3;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.web3.dao.MemberMapper;
import com.spring.web3.vo.Member;


/**
 * 회원 가입, 회원 목록 보기 컨트롤러
 */
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	// 빈 으로 등록되 것을 가져옴
	@Autowired
	SqlSession sqlSession;
	
	// 가입 폼으로 이동
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String viewJoinForm() {
		return "joinForm";
	}
	
	// 가입 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(Member member) {
		logger.info("전달된 param : {}", member);
		
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		int result = 0;
		try {
			result = mapper.insert(member);
		} catch (Exception e) {
			e.printStackTrace();
			return "joinForm";
		}
		
		if (result != 1) {
			return "joinForm";
		}
		
		return "redirect:/";
	}
	
	// 회원 정보 보기
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(String id, Model model) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		Member member = mapper.selectOne(id);
		model.addAttribute("member", member);
		
		return "info";
	}
	
	// 회원 목록 보기
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		ArrayList<Member> list = mapper.select();
		model.addAttribute("list", list);
		
		return "list";
	}
	
	// 회원 정보 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String id) {
		MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
		int result = mapper.delete(id);
		
		return "redirect:list";
	}
}
