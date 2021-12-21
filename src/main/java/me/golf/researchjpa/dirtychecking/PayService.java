package me.golf.researchjpa.dirtychecking;

import lombok.RequiredArgsConstructor;
import me.golf.researchjpa.dirtychecking.domain.Pay;
import me.golf.researchjpa.dirtychecking.domain.PayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@RequiredArgsConstructor
@Service
public class PayService {

    private final PayRepository payRepository;
    private final EntityManagerFactory factory;

    public void updateNative(Long id, String tradeNo) {
        EntityManager em = factory.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Pay pay = em.find(Pay.class, id);
        pay.changeTradeNo(tradeNo);
        tx.commit();
}

    @Transactional
    public void update(Long id, String tradeNo) {
        Pay pay = payRepository.getById(id);
        pay.changeTradeNo(tradeNo);
    }
}
