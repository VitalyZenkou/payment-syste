package com.zenkou.paymentsystem.test;

import com.zenkou.paymentsystem.PaymentSystemApplicationTests;
import com.zenkou.paymentsystem.util.DatabaseHelper;
import com.zenkou.paymentsystem.util.EntityCreatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;

@Transactional
public class BaseDataBaseTest extends PaymentSystemApplicationTests {

    @Autowired
    protected EntityManagerFactory managerFactory;

    @Autowired
    protected EntityCreatorUtil creatorUtil;

    @Autowired
    protected DatabaseHelper databaseHelper;
}
