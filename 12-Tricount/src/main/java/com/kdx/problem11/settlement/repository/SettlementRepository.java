package com.kdx.problem11.settlement.repository;

import com.kdx.problem11.settlement.domain.Settlement;
import com.kdx.problem11.settlement.dto.SettlementBalanceDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;

@Repository
public class SettlementRepository {
    private final JdbcTemplate jdbcTemplate;

    public SettlementRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Settlement save(Settlement settlement) {
        String sql = "INSERT INTO settlement(name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"});
            pstmt.setString(1, settlement.getName());
            return pstmt;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();
        settlement.setId(key);
        return settlement;
    }

    public List<Settlement> findAllByMemberId(Long memberId) {
        String sql = "SELECT s.id AS s_id, s.name as s_name " +
                "FROM settlement s " +
                "JOIN member_settlement ms ON s.id = ms.settlement_id " +
                "WHERE ms.member_id = ?;";
        return jdbcTemplate.query(sql, settlementMapper(), memberId);
    }

    private RowMapper<Settlement> settlementMapper() {
        return ((rs, rowNum) ->
                Settlement.builder().id(rs.getLong("s_id")).name(rs.getString("s_name")).build());
    }

    public Optional<Settlement> findById(Long settlementId) {
        // todo: 해당 settlement id에 속한 멤버 목록 보여주기
        String sql = "SELECT s.id as s_id, s.name as s_name, m.id as m_id, m.name as m_name, m.nickname as m_nickname " +
                "FROM settlement s " +
                "JOIN member_settlement ms ON s.id = ms.settlement_id " +
                "JOIN member m ON ms.member_id = m.id " +
                "WHERE s.id = ?";
        return Optional.ofNullable(jdbcTemplate.query(sql, new SettlementWithMembersResultSetExtractor(), settlementId));
    }

    public void delete(Long id) {
        String settlementSql = "DELETE FROM settlement WHERE id = ?";
        jdbcTemplate.update(settlementSql, id);
    }

    public boolean existById(Long settlementId) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(id) as cnt FROM member_settlement ms WHERE ms.settlement_id = ?", new Object[]{settlementId}, Integer.class);
        return count > 0;
    }
}
/*

A 30
B 10
C 23
AVG 21

A 9
B -11
C 2

A 10
B 11
C 12
D 13
AVG 11.5

A -1.5
B -0.5
C 0.5
D 1.5

 */