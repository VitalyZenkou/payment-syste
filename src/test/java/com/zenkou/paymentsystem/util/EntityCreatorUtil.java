package com.zenkou.paymentsystem.util;


import com.zenkou.paymentsystem.database.entity.CreditCardType;
import com.zenkou.paymentsystem.database.entity.Currency;
import com.zenkou.paymentsystem.database.entity.UserRoleType;
import com.zenkou.paymentsystem.database.entity.db.Address;
import com.zenkou.paymentsystem.database.entity.db.BankAccount;
import com.zenkou.paymentsystem.database.entity.db.CreditCard;
import com.zenkou.paymentsystem.database.entity.db.Payment;
import com.zenkou.paymentsystem.database.entity.db.Role;
import com.zenkou.paymentsystem.database.entity.db.User;
import com.zenkou.paymentsystem.database.util.CreditCardDataGenerator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class EntityCreatorUtil {

    private final User vasia = User.builder()
            .login("vasia")
            .name("Vasia")
            .password("1234")
            .surname("Pupkin")
            .birthDate(LocalDate.of(1986, 10, 10))
            .build();

    private final User vasia2 = User.builder()
            .login("vasia2")
            .name("Vasia")
            .password("1234")
            .surname("Pupkin")
            .birthDate(LocalDate.of(1988, 10, 10))
            .build();

    private final User vasia3 = User.builder()
            .login("vasia3")
            .name("Vasia")
            .password("1234")
            .surname("Pupkin")
            .birthDate(LocalDate.of(1988, 10, 10))
            .build();

    private final User vasia4 = User.builder()
            .login("vasia4")
            .name("Vasia")
            .password("1234")
            .surname("Pupkin")
            .birthDate(LocalDate.of(1988, 10, 10))
            .build();

    private final User petia = User.builder()
            .login("petia")
            .name("Petia")
            .password("5678")
            .surname("Petrov")
            .birthDate(LocalDate.of(2000, 1, 12))
            .build();

    private final User inna = User.builder()
            .login("inna")
            .name("Inna")
            .password("7890")
            .surname("Ivanova")
            .birthDate(LocalDate.of(1993, 4, 2))
            .build();

    private final User inna2 = User.builder()
            .login("inna2")
            .name("Inna")
            .password("7890")
            .surname("Ivanova")
            .birthDate(LocalDate.of(1993, 4, 2))
            .build();

    private final Address adress = Address.builder()
            .user(vasia)
            .email("123123@gmail.com")
            .phoneNumber("+375293959859")
            .physicalAddress(Address.PhysicalAddress.builder()
                    .city("Minsk")
                    .street("Pushkina")
                    .houseNumber(38)
                    .flatNumber(10)
                    .build())
            .build();

    private final BankAccount bankAccount = BankAccount.builder()
            .user(vasia)
            .balance(new BigDecimal(100))
            .currency(Currency.BYR)
            .isBlocked(false)
            .build();

    private final CreditCard creditCard = CreditCard.builder()
            .creditCardType(CreditCardType.VISA)
            .bankAccount(bankAccount)
            .cvv(CreditCardDataGenerator.generateCvv())
            .isBlocked(false)
            .number(CreditCardDataGenerator.generateCreditCardNumber())
            .pinCode(CreditCardDataGenerator.generatePinCode())
            .validityDate("12/20")
            .build();

    private final Payment payment = Payment.builder()
            .bigDecimal(new BigDecimal(5))
            .creditCard(creditCard)
            .isTransaction(false)
            .organization("bla-bla-bla")
            .build();

    private final Role role = Role.builder()
            .userRoleType(UserRoleType.SUPER_ADMIN)
            .build();

    public List<User> getUsers() {
        return Arrays.asList(vasia, petia, inna);
    }

    public User getUser() {
        return vasia;
    }

    public Address getAddress() {
        return adress;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public Payment getPayment() {
        return payment;
    }

    public Role getRole() {
        return role;
    }

    public List<User> getTheSameUsers() {
        return Arrays.asList(vasia, vasia2, vasia3, vasia4, inna, inna2);
    }
}
