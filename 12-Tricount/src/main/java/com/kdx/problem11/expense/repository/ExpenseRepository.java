package com.kdx.problem11.expense.repository;

import com.kdx.problem11.expense.domain.Expense;
import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.settlement.dao.BalanceDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class ExpenseRepository {
    private final JdbcTemplate jdbcTemplate;

    public ExpenseRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Expense> findAllExpenseBySettlementId(Long settlementId) {
        String sql = "SELECT e.id as e_id, e.name as e_name, e.amount as e_amount, e.created_at as e_created_at, m.id as m_id, m.name as m_name, m.nickname as m_nickname " + "FROM expense e " + "JOIN member m ON e.member_id = m.id " + "WHERE e.settlement_id = ?";
        return jdbcTemplate.query(sql, expenseAndMemberMapper(), settlementId);
    }

    private RowMapper<Expense> expenseAndMemberMapper() {
        return (rs, rowNum) -> {
            Member member = Member.builder().id(rs.getLong("m_id")).name(rs.getString("m_name")).nickname(rs.getString("m_nickname")).build();

            return Expense.builder().id(rs.getLong("e_id")).name(rs.getString("e_name")).amount(rs.getBigDecimal("e_amount")).createdAt(rs.getTimestamp("e_created_at")).member(member).build();
        };
    }

    public Expense save(Long settlementId, Expense expense) {
        String sql = "INSERT INTO expense(name, member_id, settlement_id, amount) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"id"});
            pstmt.setString(1, expense.getName());
            pstmt.setLong(2, settlementId);
            pstmt.setLong(3, expense.getMember().getId());
            pstmt.setBigDecimal(4, expense.getAmount());
            return pstmt;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        expense.setId(id);
        return expense;
    }

    public void delete(Long expenseId) {
        String sql = "DELETE FROM expense e WHERE e.id = ?";
        jdbcTemplate.update(sql, expenseId);
    }

    public boolean existByIdAndMemberId(Long expenseId, Long memberId) {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(id) as cnt FROM expense e WHERE e.id = ? and e.member_id = ?", new Object[]{expenseId, memberId}, Integer.class);
        return count > 0;
    }

    public List<BalanceDao> findAllExpenseBySettlementIdAndGroupByMemberId(Long settlementId) {
        String sql =
                "WITH total_expense AS ( SELECT SUM(e2.amount) as total_amount, COUNT(DISTINCT e2.member_id) as member_count FROM expense e2 WHERE e2.settlement_id = ? ) " +
                        "SELECT m.id as m_id, m.name as m_name, SUM(e.amount) as e_amount, t.total_amount / t.member_count as avg_amount, SUM(e.amount) - (t.total_amount / t.member_count) as diff_from_avg " +
                        "FROM expense e JOIN member m ON e.member_id = m.id JOIN total_expense t " +
                        "WHERE e.settlement_id = ? " +
                        "GROUP BY m.id, m.name, t.total_amount, t.member_count";
        return jdbcTemplate.query(sql, expenseSumMapper(), settlementId, settlementId);

    }

    private RowMapper<BalanceDao> expenseSumMapper() {
        return (rs, rowNum) -> new BalanceDao(rs.getLong("m_id"), rs.getString("m_name"), rs.getBigDecimal("diff_from_avg"));
    }
}
