package com.test.paymentservice.domain.model;

import lombok.*;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_generator")
    @SequenceGenerator(name="client_generator", sequenceName = "clients_id_seq", allocationSize=1)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_client")
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        this.accounts.add(account);
        account.setClient(this);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client)) {
            return false;
        }

        return id != null && id.equals(((Client) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
