package com.kdx.problem11.settlement.repository;

import com.kdx.problem11.expense.domain.Expense;
import com.kdx.problem11.expense.dto.ExpenseRequestDto;
import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.membersettlement.domain.MemberSettlement;
import com.kdx.problem11.settlement.domain.Settlement;
import com.kdx.problem11.settlement.dto.SettlementResponseDto;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // todo: GET /members/{id}/settlements 이면 어떨까?
        try {
            String sql = "SELECT s.id, s.name FROM settlement s JOIN member_settlement ms ON s.id = ms.settlement_id WHERE ms.member_id = ?";
            return jdbcTemplate.query(sql, settlementMapper(), memberId);
        } catch (DataAccessException e) {
            return List.of();
        }
    }

    public Optional<Settlement> findById(Long id) {
        // todo: show expenses
        String sql = "SELECT s.id as s_id, s.name as s_name, e.id as e_id, e.name as e_name, member_id as m_id, e.name as m_name, amount as e_amount, created_at as e_created_at " +
                "FROM settlement s " +
                "JOIN expense e ON s.id = e.settlement_id " +
                "JOIN member m ON e.member_id = m.id " +
                "WHERE s.id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.query(sql, new SettlementResultSetExtractor(), id));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private RowMapper<Settlement> settlementMapper() {
        return ((rs, rowNum) -> {
            return Settlement.builder()
                    .id(rs.getLong("s.id"))
                    .name(rs.getString("s.name"))
                    .build();
        });
    }

    private class SettlementResultSetExtractor implements ResultSetExtractor<Settlement> {

        @Override
        public Settlement extractData(ResultSet rs) throws SQLException, DataAccessException {
            Long settlementId = null;
            String settlementName = null;
            List<Expense> expenses = new ArrayList<>();
            while (rs.next()) {
                if (settlementId != null) {
                    settlementId = rs.getLong("s_id");
                    settlementName = rs.getString("s_name");
                }

                Member member = Member.builder()
                        .id(rs.getLong("m_id"))
                        .name(rs.getString("m_name"))
                        .build();

                Expense expense = Expense.builder()
                        .id(rs.getLong("e_id"))
                        .name(rs.getString("e_name"))
                        .amount(rs.getBigDecimal("e_amount"))
                        .createdAt(rs.getTimestamp("e_created_at"))
                        .member(member)
                        .build();
                expenses.add(expense);
            }
            return Settlement.builder().id(settlementId).name(settlementName).expenses(expenses).build();
        }
    }
}
