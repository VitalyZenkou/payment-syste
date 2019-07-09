package com.zenkou.paymentsystem.database.entity.db;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "address", schema = "payment_system_storage",
        uniqueConstraints = {@UniqueConstraint(
                columnNames = {"city", "street", "house_number", "phone_number", "user_id"})})
public class Address extends BaseEntity<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride(name = "city", column = @Column(name = "city", nullable = false, length = 50)),
            @AttributeOverride(name = "street", column = @Column(name = "street", nullable = false, length = 128)),
            @AttributeOverride(name = "houseNumber", column = @Column(name = "house_number", nullable = false)),
            @AttributeOverride(name = "flatNumber", column = @Column(name = "flat_number"))
    })
    private PhysicalAddress physicalAddress;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Data
    @Builder(toBuilder = true)
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class PhysicalAddress {
        private String city;
        private String street;
        private int houseNumber;
        private int flatNumber;
    }
}
