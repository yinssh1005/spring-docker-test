package com.example.springdockertest.persistence.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "foo", schema = "test_schema", catalog = "ethan")
public class FooEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "seq")
    private long id;

    private String name;
    private Long version;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "version", nullable = true)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FooEntity fooEntity = (FooEntity) o;

        if (id != fooEntity.id) return false;
        if (name != null ? !name.equals(fooEntity.name) : fooEntity.name != null)
            return false;
        if (version != null ? !version.equals(fooEntity.version) : fooEntity.version != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
