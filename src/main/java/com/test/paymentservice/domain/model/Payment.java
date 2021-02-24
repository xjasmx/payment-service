package com.test.paymentservice.domain.model;

import com.test.paymentservice.domain.enums.PaymentStatus;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payments")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_generator")
    @SequenceGenerator(name="payment_generator", sequenceName = "payments_id_seq", allocationSize=1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account_source", nullable = false)
    private Account accountSource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account_destination", nullable = false)
    private Account accountDestination;

    @Column(name = "payment_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "payment_reason", nullable = false)
    private String reason;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    @Column(name = "payment_status", columnDefinition = "payment_status", nullable = false)
    private PaymentStatus status;

    @Column(nullable = false)
    private LocalDateTime paymentOn;

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Payment)) {
            return false;
        }

        return id != null && id.equals(((Payment) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", paymentOn=" + paymentOn +
                '}';
    }
}
