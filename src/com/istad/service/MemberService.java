package com.istad.service;

import com.istad.model.Book;
import com.istad.model.Member;

import java.util.List;

public interface MemberService {

    boolean activateMember(String memberCode);
    boolean deactivateMember(String memberId);
    boolean toggleMemberStatus(String memberCode, String currentStatus);

    List<Member> searchMember(String key);

    void deleteByCode(String memberCode);

    void save(Member member);

    void updateByCode(String memberCode , Member member);

    List<Member> findAll();
}
