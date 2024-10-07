package com.kdx.problem11.membersettlement.repository;

import com.kdx.problem11.membersettlement.domain.MemberSettlement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

@Repository
public class MemberSettlementRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberSettlementRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(Long memberId, Long settlementId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO member_settlement(member_id, settlement_id) VALUES(?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"});
            pstmt.setLong(1, memberId);
            pstmt.setLong(2, settlementId);
            return pstmt;
        }, keyHolder);
    }

    public boolean existByIdAndMemberId(Long memberId, Long settlementId) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(id) as cnt FROM member_settlement ms WHERE ms.member_id = ? and ms.settlement_id = ?", new Object[] { memberId, settlementId }, Integer.class);
        return count > 0;
    }
}
