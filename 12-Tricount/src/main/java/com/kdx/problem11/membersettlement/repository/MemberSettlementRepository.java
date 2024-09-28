package com.kdx.problem11.membersettlement.repository;

import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.membersettlement.domain.MemberSettlement;
import com.kdx.problem11.settlement.domain.Settlement;
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

    public MemberSettlement save(Member member, Settlement settlement) {
        MemberSettlement memberSettlement = MemberSettlement.builder()
                .member(member)
                .settlement(settlement)
                .build();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO member_settlement(member_id, settlement_id) VALUES(?, ?)";

        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"});
            pstmt.setLong(1, member.getId());
            pstmt.setLong(2, settlement.getId());
            return pstmt;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();
        memberSettlement.setId(key);
        return memberSettlement;
    }
}
