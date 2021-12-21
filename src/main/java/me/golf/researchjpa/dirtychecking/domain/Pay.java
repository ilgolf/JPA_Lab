package me.golf.researchjpa.dirtychecking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@DynamicUpdate // 변경한 필드만 대응
public class Pay {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tradeNo;
    private long amount;

    @OneToMany(mappedBy = "pay", cascade = CascadeType.ALL)
    private List<PayDetail> details = new ArrayList<>();

    public Pay(String tradeNo, long amount) {
        this.tradeNo = tradeNo;
        this.amount = amount;
    }

    public void addDetail(PayDetail detail) {
        this.details.add(detail);
        detail.setPay(this);
    }

    public void changeTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
