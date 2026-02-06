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
    public boolean activateMember(String memberCode) {
        try {
            return memberServiceDao.setStatus(memberCode,"ACTIVE");
        } catch (SQLException e) {
            ViewUtil.printHeader("SQL errored: "+e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean deactivateMember(String memberCode) {
        try{
            return  memberServiceDao.setStatus(memberCode,"INACTIVE");
        } catch (SQLException e) {
            ViewUtil.printHeader("SQL errored: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean toggleMemberStatus(String memberCode, String currentStatus) {
        try{
            String newStatus = currentStatus.equalsIgnoreCase("ACTIVE") ? "INACTIVE" : "ACTIVE";
            return memberServiceDao.setStatus(memberCode, newStatus);
        } catch (SQLException e) {
            ViewUtil.printHeader("SQL errored: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Member> searchMember(String key) {
        try{
            return memberServiceDao.searchMember(key);
        } catch (SQLException e) {
            ViewUtil.printHeader("SQL errored: "+e.getMessage());
            throw new RuntimeException(e);
        }
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

    public void updateByCode(String memberCode, Member member) {
        try {
            Member foundMember = memberServiceDao.findByCode(memberCode)
                    .orElseThrow(() -> new RuntimeException("Member code not found...!"));

            if (member.getFullName() != null && !member.getFullName().isBlank())
                foundMember.setFullName(member.getFullName());

            if (member.getGender() != null && !member.getGender().isBlank())
                foundMember.setGender(member.getGender());

            if (member.getPhone() != null && !member.getPhone().isBlank())
                foundMember.setPhone(member.getPhone());

            if (member.getEmail() != null && !member.getEmail().isBlank())
                foundMember.setEmail(member.getEmail());

            if (member.getAddress() != null && !member.getAddress().isBlank())
                foundMember.setAddress(member.getAddress());

            if (member.getJoinDate() != null)
                foundMember.setJoinDate(member.getJoinDate());

            if (member.getStatus() != null && !member.getStatus().isBlank())
                foundMember.setStatus(member.getStatus());

            int affectedRow = memberServiceDao.updateByCode(memberCode, foundMember);

            if (affectedRow < 1)
                throw new RuntimeException("Update operation failed...");

        } catch (SQLException e) {
            ViewUtil.printHeader("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
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
