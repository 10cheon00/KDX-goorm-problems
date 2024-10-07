package com.kdx.problem11.settlement.repository;

import com.kdx.problem11.expense.domain.Expense;
import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.settlement.domain.Settlement;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettlementWithMembersResultSetExtractor implements ResultSetExtractor<Settlement> {

    @Override
    public Settlement extractData(ResultSet rs) throws SQLException, DataAccessException {
        Long settlementId = null;
        String settlementName = null;
        List<Member> members = new ArrayList<>();
        while (rs.next()) {
            if (settlementId == null) {
                settlementId = rs.getLong("s_id");
                settlementName = rs.getString("s_name");
            }

            members.add(Member.builder()
                    .id(rs.getLong("m_id"))
                    .name(rs.getString("m_name"))
                    .build());
        }
        return Settlement.builder().id(settlementId).name(settlementName).members(members).build();
    }
}