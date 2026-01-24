package com.istad.service;

import com.istad.model.Book;
import com.istad.model.Member;

import java.util.List;

public interface MemberService {

    void deleteByCode(String memberCode);

    void save(Member member);

    void updateByCode(String memberCode , Member member);

    List<Member> findAll();
}
