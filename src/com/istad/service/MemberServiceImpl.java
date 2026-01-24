package com.istad.service;

import com.istad.dao.MemberServiceDao;
import com.istad.dao.MemberServiceDaoImpl;
import com.istad.model.Book;
import com.istad.model.Member;
import com.istad.util.ViewUtil;

import java.sql.SQLException;
import java.util.List;

public class MemberServiceImpl implements MemberService{

    private final MemberServiceDao memberServiceDao;

    public MemberServiceImpl(){
        memberServiceDao = new MemberServiceDaoImpl();
    }

    @Override
    public void deleteByCode(String memberCode) {
        try{
            if (!memberServiceDao.existByCode(memberCode))
                throw new RuntimeException("Member code do not exist...!");
            int affactedRow = memberServiceDao.deleteByCode(memberCode);
            if (affactedRow < 1)
                throw new RuntimeException("Delete Operation failed...");

        } catch (SQLException e) {
            System.out.println("SQL error : "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateByCode(String memberCode, Member member) {
        try {
            Member foundBook = memberServiceDao.findByCode(memberCode)
                    .orElseThrow(()-> new RuntimeException("Member code not found...!"));
            if (!member.getFullName().isBlank())
                foundBook.setFullName(member.getFullName());
            if (!member.getGender().isBlank())
                foundBook.setGender(member.getGender());
            if (!member.getPhone().isBlank())
                foundBook.setPhone(member.getPhone());
            if (!member.getEmail().isBlank())
                foundBook.setEmail(member.getEmail());
            if (!member.getAddress().isBlank())
                foundBook.setAddress(member.getAddress());
            if (member.getJoinDate() != null)
                foundBook.setJoinDate(member.getJoinDate());
            if (!member.getStatus().isBlank())
                foundBook.setStatus(member.getStatus());

            int affactedRow = memberServiceDao.updateByCode(memberCode,foundBook);
            if (affactedRow < 1)
                throw  new RuntimeException("Update operation failed...");

        } catch (SQLException e) {
            ViewUtil.printHeader("SQL error: "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(Member member) {
        try{
            int affectedRow = memberServiceDao.save(member);
            if (affectedRow < 1){
                throw new RuntimeException("Insert new record failed");
            }
        }catch (SQLException e){
            System.out.println("SQL errored: "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Member> findAll() {
        try{
            return memberServiceDao.findAll();
        }catch (SQLException e){
            System.out.println("SQL errored: "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
