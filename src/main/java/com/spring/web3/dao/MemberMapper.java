package com.spring.web3.dao;

import java.util.ArrayList;

import com.spring.web3.vo.Member;

public interface MemberMapper {
	public int insert(Member member);
	public Member selectOne(String id);
	public ArrayList<Member> select();
	public int delete(String id);
}
