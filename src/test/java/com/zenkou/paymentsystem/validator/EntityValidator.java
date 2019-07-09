package com.zenkou.paymentsystem.validator;


import com.zenkou.paymentsystem.database.entity.db.Address;
import com.zenkou.paymentsystem.database.entity.db.BankAccount;
import com.zenkou.paymentsystem.database.entity.db.CreditCard;
import com.zenkou.paymentsystem.database.entity.db.Payment;
import com.zenkou.paymentsystem.database.entity.db.Role;
import com.zenkou.paymentsystem.database.entity.db.User;
import lombok.experimental.UtilityClass;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

@UtilityClass
public class EntityValidator {

    public static void validateUser(User user) {
        assertNotNull(user, sendMessageForEntity("User"));
        assertNotNull(user.getBankAccounts(), sendMessageForField("List bank accounts"));
        assertNotNull(user.getAddresses(), sendMessageForField("Address"));
        assertNotNull(user.getName(), sendMessageForField("Name"));
        assertNotNull(user.getBirthDate(), sendMessageForField("Birth date"));
        assertNotNull(user.getLogin(), sendMessageForField("Login"));
        assertNotNull(user.getPassword(), sendMessageForField("Password"));
        assertNotNull(user.getSurname(), sendMessageForField("Surname"));
        assertNotNull(user.getPayments(), sendMessageForField("List payments "));
        assertNotNull(user.getRoles(), sendMessageForField("List roles"));
    }

    public static void validateAddress(Address address, User user) {
        assertNotNull(address, sendMessageForEntity("Address"));
        assertEquals(user.getAddresses().size(), 1, sendMessage("User", "addresses"));
        Address.PhysicalAddress physicalAddress = address.getPhysicalAddress();
        assertNotNull(physicalAddress, sendMessageForField("Physical address"));
        assertNotNull(physicalAddress.getCity(), sendMessageForField("City"));
        assertNotNull(physicalAddress.getStreet(), sendMessageForField("Street"));
        assertNotEquals(physicalAddress.getHouseNumber(), 0, sendMessageForField("House number"));
        assertNotNull(address.getEmail(), "Email");
        assertNotNull(address.getPhoneNumber(), "Phone number");
        assertNotNull(address.getUser(), "User");
    }

    public static void validateBankAccount(BankAccount bankAccount, User user) {
        assertNotNull(bankAccount, sendMessageForEntity("Bank account"));
        assertEquals(user.getBankAccounts().size(), 1, sendMessage("User", " bank accounts"));
        assertNotNull(bankAccount.getBalance(), sendMessageForField("Balance "));
        assertNotNull(bankAccount.getCreditCards(), sendMessageForField("List credit cards"));
        assertNotNull(bankAccount.getCurrency(), sendMessageForField("Currency"));
        assertNotNull(bankAccount.getUser(), sendMessageForField("User"));
    }

    public static void validateCreditCard(CreditCard creditCard, BankAccount bankAccount) {
        assertNotNull(creditCard, sendMessageForEntity("Credit card"));
        assertEquals(bankAccount.getCreditCards().size(), 1, sendMessage("Bank account", "credit card"));
        assertNotNull(creditCard.getBankAccount(), sendMessageForField("Bank account"));
        assertNotNull(creditCard.getCreditCardType(), sendMessageForField("Credit card type"));
//        assertEquals(creditCard.getCvv(), 0, sendMessageForField("Cvv"));
        assertNotNull(creditCard.getPayments(), sendMessageForField("List payments"));
        assertNotNull(creditCard.getNumber(), sendMessageForField("Credit card number"));
        assertNotNull(creditCard.getValidityDate(), sendMessageForField("Validity date"));
//        assertEquals(creditCard.getPinCode(), 0, sendMessageForField("Pin code"));
    }

    public static void validatePayment(Payment payment, User user, CreditCard creditCard) {
        assertNotNull(payment, sendMessageForEntity("Payment"));
        assertEquals(user.getPayments().size(), 1, sendMessage("User", "payments"));
        assertEquals(creditCard.getPayments().size(), 1, sendMessage("Credit card", "payments"));
        assertNotNull(payment.getBigDecimal(), sendMessageForField("Cost"));
        assertNotNull(payment.getCreditCard(), sendMessageForField("Credit card"));
        assertNotNull(payment.getUser(), sendMessageForField("User"));
    }

    public static void validateUserRole(Role role, User user) {
        assertNotNull(role, sendMessageForEntity("Role"));
        assertEquals(user.getRoles().size(), 1, sendMessage("User", "role"));
        assertNotNull(role.getUserRoleType(), "User role");
    }

    private static String sendMessageForField(String message) {
        return String.format("%s wasn't initialized", message);
    }

    private static String sendMessageForEntity(String message) {
        return String.format("%s wasn't written to data base", message);
    }

    private static String sendMessage(String to, String from) {
        return String.format("%s has't any %s", to, from);
    }
}
