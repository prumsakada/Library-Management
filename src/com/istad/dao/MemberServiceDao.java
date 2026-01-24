package com.istad.dao;

import com.istad.model.Book;
import com.istad.model.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MemberServiceDao {
    boolean existByCode(String memberCode) throws SQLException;

    int deleteByCode(String memberCode) throws SQLException;

    int save(Member member) throws SQLException;

    int updateByCode(String memberCode, Member member) throws SQLException;

    List<Member> findAll() throws SQLException;

    Optional<Member> findByCode(String memberCode) throws SQLException;
}
