package com.kdx.problem11.settlement.controller;

import com.kdx.problem11.auth.config.Authentication;
import com.kdx.problem11.settlement.dto.SettlementBalanceDto;
import com.kdx.problem11.member.domain.Member;
import com.kdx.problem11.settlement.dto.SettlementRequestDto;
import com.kdx.problem11.settlement.dto.SettlementResponseDto;
import com.kdx.problem11.settlement.service.SettlementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/settlements")
public class SettlementController {
    private final SettlementService settlementService;

    public SettlementController(SettlementService settlementService) {
        this.settlementService = settlementService;
    }

    /**
     * get settlement list
     * 멤버가 가진 정산 목록을 보여줌
     * 정산에 속한 멤버나 expense를 보여주지는 않음.
     *
     * @return
     */
    @GetMapping
    public List<SettlementResponseDto> getSettlements(@Authentication Member member) {
        return settlementService.searchByMember(member);
    }

    /**
     * create and join settlement
     * 정산을 생성하고 멤버가 그 정산에 포함됨
     *
     * @param settlementRequestDto
     * @return
     */
    @PostMapping
    public SettlementResponseDto createAndJoin(@Authentication Member member, @RequestBody SettlementRequestDto settlementRequestDto) {
        return settlementService.createAndJoin(settlementRequestDto, member);
    }

    /**
     * get settlement
     * 멤버가 속한 특정 정산을 조회함
     * 속한 멤버를 보여줌.
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public SettlementResponseDto getSettlement(@PathVariable Long id) {
        return settlementService.searchById(id);
    }

    /**
     * delete settlement
     * 멤버가 속한 특정 정산을 삭제함
     *
     * @param id
     */
    @DeleteMapping("/{id}")
    public void deleteSettlement(@Authentication Member member, @PathVariable Long id) {
        settlementService.remove(member.getId(), id);
    }

    /**
     * join settlement
     * 멤버가 속하지 않은 특정 정산에 가입?함
     *
     * @param id
     * @param settlementRequestDto
     * @return
     */
    @PostMapping("/{id}")
    public void joinSettlement(@Authentication Member member, @PathVariable Long id) {
        settlementService.join(member.getId(), id);
    }

    /**
     * get settle balance
     * 정산의 결과를 반환함
     *
     * @return
     */
    @GetMapping("/{id}/balance")
    public List<SettlementBalanceDto> getSettleBalance(@PathVariable Long id) {
        return settlementService.calculateBalance(id);
    }
}
