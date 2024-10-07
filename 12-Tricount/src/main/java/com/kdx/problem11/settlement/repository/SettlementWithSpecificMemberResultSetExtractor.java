package com.kdx.problem11.settlement.repository;

import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.settlement.domain.Settlement;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettlementWithSpecificMemberResultSetExtractor implements ResultSetExtractor<List<Settlement>> {
    @Override
    public List<Settlement> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Long id = null;
        String name = null;
        List<Settlement> settlements = new ArrayList<>();
//        List<Member> members = new ArrayList<>();
        while(rs.next()) {
            if (id == null) {
                id = rs.getLong("s_id");
                name = rs.getString("s_name");
            }
            Long currentId = rs.getLong("s_id");
            // todo: 초기화 단계에서는 빈 배열 삽입 못하도록 해야함
            if (!currentId.equals(id)) {
                settlements.add(generateSettlement(id, name));
//                members = new ArrayList<>();
                // to create new settlement;
                id = rs.getLong("s_id");
                name = rs.getString("s_name");
            }
        }
        settlements.add(generateSettlement(id, name));
        return settlements;
    }

    private Settlement generateSettlement(Long id, String name) {
        return Settlement.builder().id(id).name(name).build();
    }
}
