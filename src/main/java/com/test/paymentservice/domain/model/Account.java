package com.test.paymentservice.domain.model;

import com.test.paymentservice.domain.enums.AccountType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_generator")
    @SequenceGenerator(name="account_generator", sequenceName = "accounts_id_seq", allocationSize=1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

    @Column(name = "account_number", nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "account_type", columnDefinition = "account_type", nullable = false)
    private AccountType type;

    @Column(nullable = false)
    private BigDecimal balance;

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account)) {
            return false;
        }

        return id != null && id.equals(((Account) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                '}';
    }
}
