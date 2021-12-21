package me.golf.researchjpa.dirtychecking;

import me.golf.researchjpa.dirtychecking.domain.Pay;
import me.golf.researchjpa.dirtychecking.domain.PayDetailRepository;
import me.golf.researchjpa.dirtychecking.domain.PayRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PayServiceTest {

    @Autowired PayRepository payRepository;
    @Autowired PayService payService;

    @AfterEach
    public void tearDown() {
        payRepository.deleteAll();
    }

    @Test
    @DisplayName("엔티티매니저로_확인")
    void check_EntityManager() {
        // given
        Pay pay = payRepository.save(new Pay("test1", 10));

        // when
        String updateTradeNo = "test2";
        payService.updateNative(pay.getId(), updateTradeNo);

        // then
        Pay saved = payRepository.findAll().get(0);
        assertThat(saved.getTradeNo()).isEqualTo(updateTradeNo);
    }

    @Test
    @DisplayName("스프링데이터JPA_사용")
    public void SpringDataJPA() {
        // given
        Pay pay = payRepository.save(new Pay("test1", 100));

        // when
        String updateTradeNo = "test2";
        payService.update(pay.getId(), updateTradeNo);

        // then
        Pay saved = payRepository.findAll().get(0);
        assertThat(saved.getTradeNo()).isEqualTo(updateTradeNo);
    }
}