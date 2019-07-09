package com.zenkou.paymentsystem.database.entity.db;

import com.zenkou.paymentsystem.database.entity.CreditCardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "payments")
@Entity
@Table(name = "credit_card", schema = "payment_system_storage")
public class CreditCard extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount bankAccount;

    @Column(name = "number", nullable = false, unique = true, length = 50)
    private String number;

    @Column(name = "validity_date", nullable = false, length = 50)
    private String validityDate;

    @Column(name = "pin_code", nullable = false)
    private int pinCode;

    @Column(name = "cvv", nullable = false)
    private int cvv;

    @Column(name = "is_blocked", nullable = false)
    private boolean isBlocked;

    @Column(name = "credit_card_type", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private CreditCardType creditCardType;

    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
