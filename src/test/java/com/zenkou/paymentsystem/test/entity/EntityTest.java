package com.zenkou.paymentsystem.test.entity;

import com.zenkou.paymentsystem.database.entity.db.Address;
import com.zenkou.paymentsystem.database.entity.db.BankAccount;
import com.zenkou.paymentsystem.database.entity.db.BaseEntity;
import com.zenkou.paymentsystem.database.entity.db.CreditCard;
import com.zenkou.paymentsystem.database.entity.db.Payment;
import com.zenkou.paymentsystem.database.entity.db.Role;
import com.zenkou.paymentsystem.database.entity.db.User;
import com.zenkou.paymentsystem.test.BaseDataBaseTest;
import com.zenkou.paymentsystem.validator.EntityValidator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collections;

public class EntityTest extends BaseDataBaseTest {

    private static final String ADDRESS;
    private static final String BANK_ACCOUNT;
    private static final String CREDIT_CARD;
    private static final String ROLE;
    private static final String USER;
    private static final String PAYMENT;

    static {
        USER = User.class.getSimpleName();
        ADDRESS = Address.class.getSimpleName();
        BANK_ACCOUNT = BankAccount.class.getSimpleName();
        CREDIT_CARD = CreditCard.class.getSimpleName();
        PAYMENT = Payment.class.getSimpleName();
        ROLE = Role.class.getSimpleName();
    }

    private User savedUser;
    private Address savedAddress;
    private BankAccount savedBankAccount;
    private CreditCard savedCreditCard;
    private Payment savedPayment;
    private Role savedRole;
    private EntityManager entityManager;

    @BeforeMethod
    public void initTestData() {
        savedUser = creatorUtil.getUser();
        savedAddress = creatorUtil.getAddress();
        savedBankAccount = creatorUtil.getBankAccount();
        savedCreditCard = creatorUtil.getCreditCard();
        savedPayment = creatorUtil.getPayment();
        savedRole = creatorUtil.getRole();
        entityManager = managerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @AfterMethod
    public void cleanDataBase() {
        databaseHelper.cleanDatabase();
        entityManager.close();
    }

    @Test
    public void userSaveTest() {
        entityManager.persist(savedUser);
        Long userId = savedUser.getId();
        checkIdCommitAndClear(userId);
        User user = getEntity(userId, USER);
        refreshEntity(savedUser);
        EntityValidator.validateUser(user);
    }

    @Test(dependsOnMethods = "userSaveTest")
    public void addressSaveTest() {
        entityManager.persist(savedUser);
        entityManager.persist(savedAddress);
        Long userId = savedUser.getId();
        Long addressId = savedAddress.getId();
        checkIdCommitAndClear(userId, addressId);
        User user = getEntity(userId, USER);
        Address address = getEntity(addressId, ADDRESS);
        refreshEntity(savedUser, savedAddress);
        EntityValidator.validateAddress(address, user);
    }

    @Test(dependsOnMethods = "userSaveTest")
    public void bankAccountSaveTest() {
        entityManager.persist(savedUser);
        entityManager.persist(savedBankAccount);
        Long userId = savedUser.getId();
        Long bankAccountId = savedBankAccount.getId();
        checkIdCommitAndClear(userId, bankAccountId);
        User user = getEntity(userId, USER);
        BankAccount bankAccount = getEntity(bankAccountId, BANK_ACCOUNT);
        refreshEntity(savedUser, savedBankAccount);
        EntityValidator.validateBankAccount(bankAccount, user);
    }

    @Test(dependsOnMethods = "bankAccountSaveTest")
    public void creditCardSaveTest() {
        entityManager.persist(savedUser);
        entityManager.persist(savedBankAccount);
        entityManager.persist(savedCreditCard);
        Long userId = savedUser.getId();
        Long bankAccountId = savedBankAccount.getId();
        Long creditCardId = savedCreditCard.getId();
        checkIdCommitAndClear(userId, bankAccountId, creditCardId);
        BankAccount bankAccount = getEntity(bankAccountId, BANK_ACCOUNT);
        CreditCard creditCard = getEntity(creditCardId, CREDIT_CARD);
        refreshEntity(savedUser, savedBankAccount, savedCreditCard);
        EntityValidator.validateCreditCard(creditCard, bankAccount);
    }

    @Test(dependsOnMethods = {"userSaveTest", "bankAccountSaveTest", "creditCardSaveTest"})
    public void paymentSaveTest() {
        entityManager.persist(savedUser);
        entityManager.persist(savedBankAccount);
        entityManager.persist(savedCreditCard);
        entityManager.persist(savedPayment);
        Long userId = savedUser.getId();
        Long bankAccountId = savedBankAccount.getId();
        Long creditCardId = savedCreditCard.getId();
        Long paymentId = savedPayment.getId();
        checkIdCommitAndClear(userId, bankAccountId, creditCardId, paymentId);
        User user = getEntity(userId, USER);
        CreditCard creditCard = getEntity(creditCardId, CREDIT_CARD);
        Payment payment = getEntity(paymentId, PAYMENT);
        refreshEntity(savedUser, savedBankAccount, savedCreditCard, savedPayment);
        EntityValidator.validatePayment(payment, user, creditCard);
    }

    @Test(dependsOnMethods = "userSaveTest")
    public void roleSaveTest() {
        savedUser = savedUser.toBuilder()
                .roles(Collections.singletonList(savedRole))
                .build();
        entityManager.persist(savedUser);
        Long userId = savedUser.getId();
        checkIdCommitAndClear(userId);
        User user = getEntity(userId, USER);
        Long roleId = user.getRoles().get(0).getId();
        Role role = getEntity(roleId, ROLE);
        refreshEntity(savedUser, savedRole);
        EntityValidator.validateUserRole(role, user);
    }

    private void checkIdCommitAndClear(Long... ids) {
        Arrays.stream(ids).forEach(Assert::assertNotNull);
        entityManager.getTransaction().commit();
        entityManager.clear();
    }

    @SuppressWarnings("unchecked")
    private void refreshEntity(BaseEntity... entities) {
        Arrays.stream(entities).forEach(entity -> entity.setId(null));
    }

    @SuppressWarnings("unchecked")
    private <T extends BaseEntity> T getEntity(Long id, String className) {
        return (T) entityManager.createQuery(String.format("select e from %s e where e.id=:id", className))
                .setParameter("id", id)
                .getSingleResult();
    }
}
