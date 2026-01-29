package com.istad.dao;

import com.istad.config.Database;
import com.istad.model.Book;
import com.istad.model.Member;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberServiceDaoImpl implements MemberServiceDao{

    private final Connection conn;

    public MemberServiceDaoImpl(){
        conn = Database.getConn();
    }


    @Override
    public List<Member> searchMember(String key) throws SQLException {
        List<Member> members = new ArrayList<>();

        String sql = """
                SELECT *
                FROM member
                WHERE
                member_code ILIKE ?
                OR full_name ILIKE ?
                OR phone ILIKE ?
                OR email ILIKE ?
                OR address ILIKE ?
                ORDER BY full_name;
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);

        String keyword = "%" + key + "%";

        pstmt.setString(1,keyword);
        pstmt.setString(2,keyword);
        pstmt.setString(3,keyword);
        pstmt.setString(4,keyword);
        pstmt.setString(5,keyword);

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Member member = new Member();
            member.setMemberId(rs.getInt("member_id"));
            member.setMemberCode(rs.getString("member_code"));
            member.setFullName(rs.getString("full_name"));
            member.setGender(rs.getString("gender"));
            member.setPhone(rs.getString("phone"));
            member.setEmail(rs.getString("email"));
            member.setAddress(rs.getString("address"));
            member.setJoinDate(rs.getDate("join_date").toLocalDate());
            member.setStatus(rs.getString("status"));

            members.add(member);
        }
        return members;
    }

    @Override
    public boolean existByCode(String memberCode) throws SQLException {
        String sql = """
                SELECT EXISTS(
                SELECT *
                FROM member
                WHERE member_code = ?
                )
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, memberCode);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
            return rs.getBoolean("exists");

        return false;
    }

    @Override
    public int deleteByCode(String memberCode) throws SQLException {
        String sql = """
                DELETE 
                FROM member
                WHERE member_code = ?
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, memberCode);

        return pstmt.executeUpdate();
    }

    @Override
    public int updateByCode(String memberCode, Member member) throws SQLException {
        String sql = """
                UPDATE member
                SET full_name = ?, gender = ? , phone = ? , email = ? 
                  , address = ? , join_date = ? , status = ?
                WHERE member_code = ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getFullName());
        pstmt.setString(2, member.getGender());
        pstmt.setString(3, member.getPhone());
        pstmt.setString(4, member.getEmail());
        pstmt.setString(5, member.getAddress());
        pstmt.setDate(6, Date.valueOf(member.getJoinDate()));
        pstmt.setString(7, member.getStatus());
        pstmt.setString(8, member.getMemberCode());

        return pstmt.executeUpdate();
    }

    @Override
    public int save(Member member) throws SQLException {
        String sql = """
                INSERT INTO books (member_code,full_name,gender,phone,email
                ,address,join_date, status)
                VALUES(?,?,?,?,?,?,?,?)
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, member.getMemberCode());
        pstmt.setString(2, member.getFullName());
        pstmt.setString(3, member.getGender());
        pstmt.setString(4, member.getPhone());
        pstmt.setString(5, member.getEmail());
        pstmt.setString(6, member.getAddress());
        pstmt.setDate(7, Date.valueOf(member.getJoinDate()));
        pstmt.setString(8, member.getStatus());


        return pstmt.executeUpdate();
    }

    @Override
    public Optional<Member> findByCode(String memberCode) throws SQLException {
        String sql = """
                SELECT * 
                FROM member
                WHERE member_code = ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, memberCode);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Member member = new Member();
            member.setMemberId(rs.getInt("member_id"));
            member.setMemberCode(rs.getString("book_code"));
            member.setFullName(rs.getString("full_name"));
            member.setGender(rs.getString("gender"));
            member.setPhone(rs.getString("phone"));
            member.setEmail(rs.getString("email"));
            member.setAddress(rs.getString("address"));
            member.setJoinDate(rs.getDate("join_date").toLocalDate());
            member.setStatus(rs.getString("status"));

            return Optional.of(member);
        }

        return Optional.empty();
    }

    @Override
    public List<Member> findAll() throws SQLException {
        Statement stmt = conn.createStatement();

        String sql = """
                SELECT *
                FROM member
                """;
        ResultSet rs = stmt.executeQuery(sql);
        List<Member> members = new ArrayList<>();
        while (rs.next()) {
            Member member = new Member();
            member.setMemberId(rs.getInt("member_id"));
            member.setMemberCode(rs.getString("member_code"));
            member.setFullName(rs.getString("full_name"));
            member.setGender(rs.getString("gender"));
            member.setPhone(rs.getString("phone"));
            member.setEmail(rs.getString("email"));
            member.setAddress(rs.getString("address"));
            member.setJoinDate(rs.getDate("join_date").toLocalDate());
            member.setStatus(rs.getString("status"));

            members.add(member);
        }
        return members;
    }
}
