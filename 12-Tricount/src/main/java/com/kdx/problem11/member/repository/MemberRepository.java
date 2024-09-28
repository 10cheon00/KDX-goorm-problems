package com.kdx.problem11.member.repository;

import com.kdx.problem11.member.domain.Member;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Member save(Member member) {
        String sql = "INSERT INTO member (name, password, nickname) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            // 자동 증가 키
            PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"id"});
            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getNickname());
            return pstmt;
        },keyHolder);

        long key = keyHolder.getKey().longValue();
        member.setId(key);

        return member;
    }

    public Optional<Member> findByName(String name) {
        String sql = "SELECT * FROM member WHERE name = ?";
        try {
            Member member = jdbcTemplate.queryForObject(sql, memberMapper(), name);
            return Optional.of(member);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Member> memberMapper() {
        return ((rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            member.setNickname(rs.getString("nickname"));
            member.setPassword(rs.getString("password"));
            return member;
        });
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM member";
        return jdbcTemplate.query(sql, memberMapper());
    }

    public List<Member> findAllById(List<Long> ids) {
        // todo: 지금은 가짜로 구현함
        return findAll();
    }
}
